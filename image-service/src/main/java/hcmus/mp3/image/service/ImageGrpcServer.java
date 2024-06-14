package hcmus.mp3.image.service;

import hcmus.mp3.image.dto.mapper.ImageProtoMapper;
import hcmus.mp3.image.exception.ResourceNotFoundException;
import hcmus.mp3.image.model.Image;
import hcmus.zingmp3.ImageRequestGrpc;
import hcmus.zingmp3.ImageResponseGrpc;
import hcmus.zingmp3.ImageServiceGrpc;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.UUID;

@GrpcService
@RequiredArgsConstructor
public class ImageGrpcServer extends ImageServiceGrpc.ImageServiceImplBase {
    private final ImageService imageService;
    private final ImageProtoMapper mapper;

    @Override
    public void getById(ImageRequestGrpc request, StreamObserver<ImageResponseGrpc> responseObserver) {
        try {
            Image image = imageService.getByID(UUID.fromString(request.getId()));
            responseObserver.onNext(mapper.toProto(image));
            responseObserver.onCompleted();
        } catch (ResourceNotFoundException e) {
            responseObserver.onError(
                    Status.NOT_FOUND
                            .withDescription(e.getMessage())
                            .asRuntimeException()
            );
        }
    }
}
