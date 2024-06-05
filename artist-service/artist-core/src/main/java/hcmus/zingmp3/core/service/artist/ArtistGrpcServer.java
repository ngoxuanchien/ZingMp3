package hcmus.zingmp3.core.service.artist;

import com.google.rpc.Code;
import com.google.rpc.Status;
import hcmus.zingmp3.artist.ArtistGrpcRequest;
import hcmus.zingmp3.artist.ArtistGrpcResponse;
import hcmus.zingmp3.artist.ArtistServiceGrpc;
import hcmus.zingmp3.common.domain.exception.ResourceNotFoundException;
import hcmus.zingmp3.common.service.artist.ArtistQueryService;
import io.grpc.protobuf.StatusProto;
import io.grpc.stub.StreamObserver;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.UUID;

@GrpcService
@RequiredArgsConstructor
public class ArtistGrpcServer extends ArtistServiceGrpc.ArtistServiceImplBase {

    private final ArtistQueryService queryService;

    @Override
    public void isExist(ArtistGrpcRequest request, StreamObserver<ArtistGrpcResponse> responseObserver) {
//        super.isExist(request, responseObserver);
        try {
            var result = queryService.getById(UUID.fromString(request.getId()));
            responseObserver.onNext(
                    ArtistGrpcResponse.newBuilder()
                            .setId(String.valueOf(result.getId()))
                            .build());
            responseObserver.onCompleted();
        } catch (EntityNotFoundException e) {
            Status status = Status.newBuilder()
                    .setCode(Code.NOT_FOUND.getNumber())
                    .setMessage(e.getMessage())
                    .build();

            responseObserver.onError(StatusProto.toStatusRuntimeException(status));
        }
    }

    @Override
    public void getArtistById(ArtistGrpcRequest request, StreamObserver<ArtistGrpcResponse> responseObserver) {
        try {
            var result = queryService.getById(UUID.fromString(request.getId()));
            responseObserver.onNext(
                    ArtistGrpcResponse.newBuilder()
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
}
