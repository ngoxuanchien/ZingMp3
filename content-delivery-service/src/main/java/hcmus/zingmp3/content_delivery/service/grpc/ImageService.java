package hcmus.zingmp3.content_delivery.service.grpc;

import hcmus.zingmp3.content_delivery.exception.NotFoundException;
import hcmus.zingmp3.content_delivery.model.dto.ImageFileDataDTO;
import hcmus.zingmp3.content_delivery.model.enums.ObjectType;
import hcmus.zingmp3.content_delivery.service.ImageFileDataService;
import hcmus.zingmp3.content_delivery.service.component.UUIDValidator;
import hcmus.zingmp3.proto.ImageFileInfo;
import hcmus.zingmp3.proto.*;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static hcmus.zingmp3.content_delivery.grpc.Constants.IMAGE_META_CONTEXT;

@GrpcService
@RequiredArgsConstructor
@Slf4j
public class ImageService extends ImageUploadServiceGrpc.ImageUploadServiceImplBase {
    private final ImageFileDataService imageFileDataService;
    private final String FILE_PATH_FORMAT = "%s/%s/";

    @Override
    public StreamObserver<ImageUploadRequest> getOrCreateIfNotExist(StreamObserver<ImageUploadResponse> responseObserver) {

        ImageFileInfo imageFileInfo = IMAGE_META_CONTEXT.get();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        return new StreamObserver<>() {
            @Override
            public void onNext(hcmus.zingmp3.proto.ImageUploadRequest imageUploadRequest) {
                try {
                    imageUploadRequest.getFile().getContent().writeTo(byteArrayOutputStream);
                } catch (IOException e) {
                    responseObserver.onError(Status
                            .INTERNAL
                            .withDescription("Cannot write data due to: " + e.getMessage())
                            .asRuntimeException());
                }
            }

            @Override
            public void onError(Throwable throwable) {
                log.warn("{}", throwable.toString());
            }

            @Override
            public void onCompleted() {
                String object = imageFileInfo.getObject();
                String currentDate = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
                String filePath = String.format(FILE_PATH_FORMAT, object.toLowerCase(), currentDate);
                ImageFileDataDTO imageFileDataDTO;
                int size = byteArrayOutputStream.size();
                if (size == imageFileInfo.getSize()) {
                    byte[] bytes = byteArrayOutputStream.toByteArray();
                    MultipartFile multipartFile = new MockMultipartFile(imageFileInfo.getName(), bytes);
                    log.info("Received image: {}", multipartFile.getSize());
                    log.info("object: {}", object);
                    imageFileDataDTO = imageFileDataService
                            .getOrCreateIfNotExist(ImageFileDataDTO.builder()
                                    .object(ObjectType.valueOf(object.toUpperCase()))
                                    .name(imageFileInfo.getName())
                                    .type(imageFileInfo.getContentType())
                                    .size(imageFileInfo.getSize())
                                    .path(filePath)
                                    .file(multipartFile)
                                    .build());

                } else {
                    responseObserver.onError(Status
                            .FAILED_PRECONDITION
                            .withDescription(String.format("Expected %d but received %d", imageFileInfo.getSize(), size))
                            .asRuntimeException());
                    return;
                }

                if (imageFileDataDTO == null) {
                    responseObserver.onError(Status
                            .INTERNAL
                            .withDescription("Cannot save data")
                            .asRuntimeException());
                    return;
                }

                responseObserver.onNext(hcmus.zingmp3.proto.ImageUploadResponse
                        .newBuilder()
                        .setStatus(hcmus.zingmp3.proto.UploadStatus.SUCCESS)
                        .setUrl(imageFileDataDTO.getUrl())
                        .setId(String.valueOf(imageFileDataDTO.getId()))
                        .build());

                responseObserver.onCompleted();
            }
        };
    }



    @Override
    public void getById(ImageFileInfo request, StreamObserver<ImageFileInfo> responseObserver) {
        System.out.println(request.getId());
        log.info("Received request to get image by id: {}", request.getId());
        if (!UUIDValidator.isValidUUID(request.getId())) {
            responseObserver.onError(Status
                    .INVALID_ARGUMENT
                    .withDescription("Invalid UUID format")
                    .asRuntimeException());
            return;
        }

        ImageFileDataDTO imageFileDataDTO = null;
        try {
            imageFileDataDTO = imageFileDataService.getImageFileData(UUID.fromString(request.getId()));
        } catch (NotFoundException exception) {
            responseObserver.onError(Status
                    .NOT_FOUND
                    .withDescription(exception.getMessage())
                    .asRuntimeException());
            return;
        }

        if (imageFileDataDTO == null) {
            responseObserver.onError(Status
                    .NOT_FOUND
                    .withDescription("Image not found")
                    .asRuntimeException());
            return;
        }

        responseObserver.onNext(ImageFileInfo
                .newBuilder()
                .setId(String.valueOf(imageFileDataDTO.getId()))
                .setName(imageFileDataDTO.getName())
                .setObject(imageFileDataDTO.getObject().name())
                .setSize(imageFileDataDTO.getSize())
                .setContentType(imageFileDataDTO.getType())
                .setUrl(imageFileDataDTO.getUrl())
                .build());
        responseObserver.onCompleted();
    }
}
