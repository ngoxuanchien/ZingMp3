package hcmus.zingmp3.artist;

import com.google.rpc.Code;
import com.google.rpc.Status;
import hcmus.zingmp3.artist.service.ArtistService;
import hcmus.zingmp3.exception.EntityNotFoundException;
import io.grpc.protobuf.StatusProto;
import io.grpc.stub.StreamObserver;
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
        try {
            var result = artistService.getArtistById(UUID.fromString(request.getId()));
            responseObserver.onNext(mapper.toProto(result));
            responseObserver.onCompleted();
        } catch (EntityNotFoundException e) {
            Status status = Status.newBuilder()
                    .setCode(Code.NOT_FOUND.getNumber())
                    .setMessage(e.getMessage())
                    .build();
            responseObserver.onError(StatusProto.toStatusRuntimeException(status));
        }
    }
}
