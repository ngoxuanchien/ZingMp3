package hcmus.zingmp3.service.grpc;

import com.google.rpc.Code;
import com.google.rpc.Status;
import hcmus.zingmp3.ArtistRequestGrpc;
import hcmus.zingmp3.ArtistResponseGrpc;
import hcmus.zingmp3.common.domain.exception.ResourceNotFoundException;
import hcmus.zingmp3.common.service.artist.ArtistQueryService;
import hcmus.zingmp3.service.image.ImageService;
import io.grpc.protobuf.StatusProto;
import io.grpc.stub.StreamObserver;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import hcmus.zingmp3.ArtistServiceGrpc.ArtistServiceImplBase;

import java.util.UUID;

@GrpcService
@RequiredArgsConstructor
public class ArtistServiceGrpc extends ArtistServiceImplBase {

    private final ArtistQueryService queryService;

    private final ImageService imageService;

    @Override
    public void isExist(ArtistRequestGrpc request, StreamObserver<ArtistResponseGrpc> responseObserver) {
        try {
            var result = queryService.getById(UUID.fromString(request.getId()));
            responseObserver.onNext(
                    ArtistResponseGrpc.newBuilder()
                            .setId(String.valueOf(result.getId()))
                            .build());
            responseObserver.onCompleted();
        } catch (ResourceNotFoundException e) {
            Status status = Status.newBuilder()
                    .setCode(Code.NOT_FOUND.getNumber())
                    .setMessage(e.getMessage())
                    .build();

            responseObserver.onError(StatusProto.toStatusRuntimeException(status));
        }
    }



    @Override
    public void getById(ArtistRequestGrpc request, StreamObserver<ArtistResponseGrpc> responseObserver) {
        try {
            var result = queryService.getById(UUID.fromString(request.getId()));
            responseObserver.onNext(
                    ArtistResponseGrpc.newBuilder()
                            .setId(String.valueOf(result.getId()))
                            .setAlias(result.getAlias())
                            .setName(result.getName())
                            .setThumbnail(imageService.getImage(result.getThumbnailId()).getUrl())
                            .setRealName(result.getRealName())
                            .setStatus(result.getStatus().name())
                            .build());
            responseObserver.onCompleted();
        } catch (ResourceNotFoundException e) {
            Status status = Status.newBuilder()
                    .setCode(Code.NOT_FOUND.getNumber())
                    .setMessage(e.getMessage())
                    .build();

            responseObserver.onError(StatusProto.toStatusRuntimeException(status));
        }
    }
}
