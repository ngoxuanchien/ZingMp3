package hcmus.zingmp3.artist;

import hcmus.zingmp3.exception.ArtistNotFoundException;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ArtistGrpcClient {

    @GrpcClient("artist-command")
    ArtistServiceGrpc.ArtistServiceBlockingStub artistClient;

    public ArtistGrpcResponse findArtistById(UUID uuid) {
        ArtistGrpcResponse response;
        try {
            var request = ArtistGrpcRequest.newBuilder()
                    .setId(uuid.toString())
                    .build();
            response = artistClient.getArtistById(request);
        } catch (StatusRuntimeException s) {
            if (s.getStatus().getCode() == Status.Code.NOT_FOUND) {
                throw new ArtistNotFoundException(
                        String.format("Artist with provided Id: %s is not found", uuid)
                );
            }
            throw s;
        }
        return response;
    }
}
