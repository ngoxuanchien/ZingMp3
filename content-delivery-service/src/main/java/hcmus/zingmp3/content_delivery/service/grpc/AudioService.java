package hcmus.zingmp3.content_delivery.service.grpc;

import hcmus.zingmp3.*;
import hcmus.zingmp3.audio.AudioFileInfo;
import hcmus.zingmp3.audio.AudioRequest;
import hcmus.zingmp3.audio.AudioResponse;
import hcmus.zingmp3.audio.AudioServiceGrpc;
import hcmus.zingmp3.content_delivery.exception.NotFoundException;
import hcmus.zingmp3.content_delivery.model.dto.AudioFileDataDTO;
import hcmus.zingmp3.content_delivery.model.entity.AudioFileData;
import hcmus.zingmp3.content_delivery.model.enums.ObjectType;
import hcmus.zingmp3.content_delivery.service.AudioFileDataService;
import hcmus.zingmp3.content_delivery.service.component.AudioQualityChecker;
import hcmus.zingmp3.content_delivery.service.component.UUIDValidator;
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

import static hcmus.zingmp3.Constants.AUDIO_META_CONTEXT;

@GrpcService
@RequiredArgsConstructor
@Slf4j
public class AudioService extends AudioServiceGrpc.AudioServiceImplBase {
    private final AudioQualityChecker audioQualityChecker;
    private final AudioFileDataService audioFileDataService;
    private static final String FILE_PATH_FORMAT = "song/%d/%s/";

    @Override
    public void getById(AudioFileInfo request, StreamObserver<AudioFileInfo> responseObserver) {
        if (!UUIDValidator.isValidUUID(request.getId())) {
            responseObserver.onError(Status
                    .INVALID_ARGUMENT
                    .withDescription("Invalid UUID format")
                    .asRuntimeException()
            );
            return;
        }

        AudioFileDataDTO audioFileDataDTO;
        try {
            audioFileDataDTO = audioFileDataService.getAudioFileData(UUID.fromString(request.getId()));
        } catch (NotFoundException e) {
            responseObserver.onError(Status
                    .NOT_FOUND
                    .withDescription("Audio not found")
                    .asRuntimeException()
            );
            return;
        }

        if (audioFileDataDTO == null) {
            responseObserver.onError(Status
                    .NOT_FOUND
                    .withDescription("Audio not found")
                    .asRuntimeException()
            );
            return;
        }

        responseObserver.onNext(AudioFileInfo.newBuilder()
                .setId(audioFileDataDTO.getId().toString())
                .setName(audioFileDataDTO.getName())
                .setObject(audioFileDataDTO.getObject().name())
                .setType(audioFileDataDTO.getType())
                .setSize(audioFileDataDTO.getSize())
                .setBitrate(audioFileDataDTO.getBitrate())
                .setUrl(audioFileDataDTO.getUrl())
                .build());
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<AudioRequest> getOrCreateIfNotExists(StreamObserver<AudioResponse> responseObserver) {
        AudioFileInfo audioInfo = AUDIO_META_CONTEXT.get();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        return new StreamObserver<AudioRequest>() {
            @Override
            public void onNext(AudioRequest audioRequest) {
                try {
                    audioRequest.getFile().getContent().writeTo(byteArrayOutputStream);
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
                AudioFileDataDTO audioFileDataDTO;
                int size = byteArrayOutputStream.size();
                if (size == audioInfo.getSize()) {
                    byte[] data = byteArrayOutputStream.toByteArray();
                    MultipartFile file = new MockMultipartFile(audioInfo.getName(), audioInfo.getName(), audioInfo.getType(), data);
                    System.out.println(audioInfo.getName());
                    long bitrate = audioQualityChecker.getBitrate(file);
                    String currentDate = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
                    String filePath = String.format(FILE_PATH_FORMAT, bitrate, currentDate);
                    audioFileDataDTO = audioFileDataService
                            .getOrCreateIfNotExist(AudioFileDataDTO.builder()
                                    .object(ObjectType.AUDIO)
                                    .name(audioInfo.getName())
                                    .type(audioInfo.getType())
                                    .size(audioInfo.getSize())
                                    .file(file)
                                    .path(filePath)
                                    .bitrate(bitrate)
                                    .build());

                } else {
                    responseObserver.onError(Status
                            .FAILED_PRECONDITION
                            .withDescription(String.format("Expected %d but received %d", audioInfo.getSize(), size))
                            .asRuntimeException());
                    return;
                }

                if (audioFileDataDTO == null) {
                    responseObserver.onError(Status
                            .INTERNAL
                            .withDescription("Cannot save data")
                            .asRuntimeException());
                    return;
                }

                responseObserver.onNext(AudioResponse.newBuilder()
                        .setStatus(UploadStatus.SUCCESS)
                        .setUrl(audioFileDataDTO.getUrl())
                        .setId(audioFileDataDTO.getId().toString())
                        .build());

                responseObserver.onCompleted();
            }
        };
    }
}
