package hcmus.zingmp3.artist;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ArtistGrpcClient {

    @GrpcClient("artist-query")
    ArtistServiceGrpc.ArtistServiceBlockingStub artistClient;

    public ArtistGrpcResponse findArtistById(UUID id) {
        ArtistGrpcResponse response;
        try {
            var request = ArtistGrpcRequest.newBuilder()
                    .setId(id.toString())
                    .build();
            response = artistClient.getArtistById(request);
        } catch (StatusRuntimeException sre) {
            if (sre.getStatus().getCode() == Status.Code.UNAVAILABLE) {
                return ArtistGrpcResponse.newBuilder()
                        .setId(id.toString())
                        .build();
            }
            throw sre;
        }

        return response;
    }
}
