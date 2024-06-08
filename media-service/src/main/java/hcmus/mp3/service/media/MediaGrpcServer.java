package hcmus.mp3.service.media;

import hcmus.mp3.domain.exception.ResourceNotFoundException;
import hcmus.zingmp3.media.MediaRequestGrpc;
import hcmus.zingmp3.media.MediaResponseGrpc;
import hcmus.zingmp3.media.MediaServiceGrpc;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.UUID;

@GrpcService
@RequiredArgsConstructor
public class MediaGrpcServer extends MediaServiceGrpc.MediaServiceImplBase {

    private final AudioService audioService;

    @Override
    public void getById(MediaRequestGrpc request, StreamObserver<MediaResponseGrpc> responseObserver) {
        super.getById(request, responseObserver);
        UUID id = UUID.fromString(request.getId());
        try {
            var audio = audioService.getAudio(id);
            var response = MediaResponseGrpc.newBuilder()
                    .setId(audio.id().toString())
                    .setUrl(audio.url())
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (ResourceNotFoundException e) {
            Status status = Status.NOT_FOUND.withDescription(e.getMessage());
            responseObserver.onError(status.asRuntimeException());
        }
    }
}
