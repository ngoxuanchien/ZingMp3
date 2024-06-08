package hcmus.zingmp3.service.artist;

import hcmus.zingmp3.artist.ArtistRequestGrpc;
import hcmus.zingmp3.artist.ArtistServiceGrpc;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ArtistServiceImpl implements ArtistService {

    @GrpcClient("artist-service")
    ArtistServiceGrpc.ArtistServiceBlockingStub artistClient;

    @Override
    public boolean isExist(UUID artistId) {
        try {
            var request = ArtistRequestGrpc.newBuilder()
                    .setId(artistId.toString())
                    .build();
            return artistClient.getArtistById(request)
                    .getId()
                    .equals(artistId.toString());
        } catch (StatusRuntimeException sre) {
            if (sre.getStatus().getCode().equals(io.grpc.Status.Code.NOT_FOUND)) {
                return false;
            }
            throw sre;
        }
    }
}
