package hcmus.zingmp3.audio;

import com.google.protobuf.ByteString;
import hcmus.zingmp3.Constants;
import hcmus.zingmp3.music_service.*;
import io.grpc.Metadata;
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

import static hcmus.zingmp3.Constants.AUDIO_META_KEY;


@Service
@Slf4j
public class AudioClientService {
    @GrpcClient(value = "content-delivery")
    AudioServiceGrpc.AudioServiceStub asynchronousClient;

    @GrpcClient(value = "content-delivery")
    AudioServiceGrpc.AudioServiceBlockingStub synchronousClient;

    public AudioFileInfo getById(final UUID id) {
        AudioFileInfo request = AudioFileInfo.newBuilder().setId(id.toString()).build();
        AudioFileInfo response;
        try {
            response = synchronousClient.getById(request);
        } catch (Exception e) {
            log.error("Error while getting audio file info by id: {}", id, e);
            return null;
        }
        return response;
    }

    public AudioResponse getOrCreateIfNotExist(final MultipartFile file) {
        InputStream inputStream;
        try {
            inputStream = file.getInputStream();
        } catch (Exception e) {
            throw new RuntimeException("Unable to extract file info");
        }

        final AudioResponse[] result = {null};
        CountDownLatch countDownLatch = new CountDownLatch(1);
        StringBuilder response = new StringBuilder();

        Metadata metadata = new Metadata();
        metadata.put(Constants.AUDIO_META_KEY,
                     AudioFileInfo.newBuilder()
                        .setObject("audio")
                        .setName(file.getOriginalFilename())
                        .setSize(file.getSize())
                        .setType(file.getContentType())
                        .build()
                        .toByteArray());

        StreamObserver<AudioRequest> audioRequestStreamObserver = asynchronousClient
                .withInterceptors(MetadataUtils.newAttachHeadersInterceptor(metadata))
                .getOrCreateIfNotExists(
                    new StreamObserver<AudioResponse>() {
                        @Override
                        public void onNext(AudioResponse audioResponse) {
                            response.append(audioResponse.getStatus());
                            result[0] = audioResponse;
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
                var request = AudioRequest.newBuilder()
                        .setFile(File.newBuilder()
                                .setContent(ByteString.copyFrom(fileKB, 0, length))
                                .build())
                        .build();
                audioRequestStreamObserver.onNext(request);
            }
            audioRequestStreamObserver.onCompleted();
            countDownLatch.await();
        } catch (IOException | InterruptedException e) {
            log.error("Error while sending audio file: {}", file.getOriginalFilename(), e);
            result[0] = null;
        }

        return result[0];

    }

}
