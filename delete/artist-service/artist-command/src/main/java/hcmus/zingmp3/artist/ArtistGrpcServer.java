package hcmus.zingmp3.artist;

import com.google.protobuf.Any;
import com.google.rpc.Code;
import com.google.rpc.Status;
import io.grpc.protobuf.StatusProto;
import io.grpc.stub.StreamObserver;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.UUID;

@GrpcService
@RequiredArgsConstructor
public class ArtistGrpcServer extends ArtistServiceGrpc.ArtistServiceImplBase {
    private final ArtistService artistService;
    private final ArtistProtoMapper mapper;

    @Override
    public void getArtistById(ArtistGrpcRequest request, StreamObserver<ArtistGrpcResponse> responseObserver) {
        var result = artistService.existsArtist(UUID.fromString(request.getId()));
        if (result == null) {
            Status status = Status.newBuilder()
                    .setCode(Code.NOT_FOUND.getNumber())
                    .setMessage(String.format("Artist with provided Id: %s is not found", request.getId()))
                    .build();
            responseObserver.onError(StatusProto.toStatusRuntimeException(status));
        } else {
            responseObserver.onNext(mapper.toProto(result));
            responseObserver.onCompleted();

        }
    }
}
