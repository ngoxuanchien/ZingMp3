package hcmus.zingmp3.thumbnail;

import com.google.protobuf.ByteString;
import hcmus.zingmp3.proto.*;
import io.grpc.Metadata;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.MetadataUtils;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

import static hcmus.zingmp3.Constants.IMAGE_META_KEY;

@Service
@Slf4j
public class ThumbnailClientService {
    @GrpcClient(value = "content-delivery-service")
    ImageUploadServiceGrpc.ImageUploadServiceStub asynchronousClient;

    @GrpcClient(value = "content-delivery-service")
    ImageUploadServiceGrpc.ImageUploadServiceBlockingStub synchronousClient;

    public ImageUploadResponse getOrCreateIfNotExist(final MultipartFile multipartFile, String object) {
        InputStream inputStream;
        try {
            inputStream = multipartFile.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException("Unable to extract file info");
        }

        final ImageUploadResponse[] result = {null};
        StringBuilder response = new StringBuilder();
        CountDownLatch countDownLatch = new CountDownLatch(1);

        Metadata metadata = new Metadata();
        metadata.put(IMAGE_META_KEY,
                ImageFileInfo.newBuilder()
                        .setObject(object)
                        .setName(multipartFile.getOriginalFilename())
                        .setSize(multipartFile.getSize())
                        .setContentType(multipartFile.getContentType())
                        .build()
                        .toByteArray());

        StreamObserver<ImageUploadRequest> imageUploadRequestStreamObserver = asynchronousClient
                .withInterceptors(MetadataUtils.newAttachHeadersInterceptor(metadata))
                .getOrCreateIfNotExist(
                    new StreamObserver<ImageUploadResponse>() {
                            @Override
                            public void onNext(ImageUploadResponse imageUploadResponse) {
                                response.append(imageUploadResponse.getUrl());
                                result[0] = imageUploadResponse;
                            }

                            @Override
                            public void onError(Throwable throwable) {
                                response.append(UploadStatus.FAILED);
                                result[0] = null;
                                throwable.printStackTrace();
                                countDownLatch.countDown();
                            }

                            @Override
                            public void onCompleted() {
                                countDownLatch.countDown();
                            }
                    }
        );

        byte[] fileKB = new byte[5120];
        int length;

        try {
            while ((length = inputStream.read(fileKB)) > 0) {
                log.info(String.format("Sending %d bytes of data", length));
                var request = ImageUploadRequest.newBuilder()
                        .setFile(File.newBuilder()
                                .setContent(ByteString.copyFrom(fileKB, 0, length))
                                .build())
                        .build();
                imageUploadRequestStreamObserver.onNext(request);
            }
            inputStream.close();
            imageUploadRequestStreamObserver.onCompleted();
            countDownLatch.await();
        } catch (IOException | InterruptedException e) {
            response.append(UploadStatus.FAILED);
            result[0] = null;
        }

        return result[0];
    }

    public ImageFileInfo getById(UUID id) {
        System.out.println(id.toString());
        ImageFileInfo request = ImageFileInfo.newBuilder().setId(id.toString()).build();
        ImageFileInfo response;
        try {
            response = synchronousClient.getById(request);
        } catch (StatusRuntimeException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return response;
    }

}
