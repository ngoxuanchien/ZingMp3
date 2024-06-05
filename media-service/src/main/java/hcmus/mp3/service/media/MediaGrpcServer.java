package hcmus.mp3.service.media;

import hcmus.zingmp3.media.MediaGrpcRequest;
import hcmus.zingmp3.media.MediaGrpcResponse;
import hcmus.zingmp3.media.MediaGrpcServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class MediaGrpcServer extends MediaGrpcServiceGrpc.MediaGrpcServiceImplBase {
    @Override
    public void getById(MediaGrpcRequest request, StreamObserver<MediaGrpcResponse> responseObserver) {
        super.getById(request, responseObserver);
    }
}
