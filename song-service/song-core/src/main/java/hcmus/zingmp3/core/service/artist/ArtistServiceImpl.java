package hcmus.zingmp3.core.service.artist;

import hcmus.zingmp3.artist.ArtistRequestGrpc;
import hcmus.zingmp3.artist.ArtistResponseGrpc;
import hcmus.zingmp3.artist.ArtistServiceGrpc;
import hcmus.zingmp3.common.domain.exception.ResourceNotFoundException;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ArtistServiceImpl implements ArtistService {

    @GrpcClient("artist-service")
    ArtistServiceGrpc.ArtistServiceBlockingStub artistClient;

    @Override
    public ArtistResponseGrpc getById(UUID uuid) {
        try {
            var request = ArtistRequestGrpc.newBuilder().setId(uuid.toString()).build();
            return artistClient.getArtistById(request);

        } catch (StatusRuntimeException e) {
            if (e.getStatus().getCode() == Status.Code.NOT_FOUND) {
                throw new ResourceNotFoundException(e.getMessage());
            }
            throw e;
        }
    }

    @Override
    public boolean isExist(UUID uuid) {
        try {
            var request = ArtistRequestGrpc.newBuilder().setId(uuid.toString()).build();
            return artistClient.isExist(request).getId().equals(uuid.toString());

        } catch (StatusRuntimeException e) {
            if (e.getStatus().getCode() == Status.Code.NOT_FOUND) {
                return false;
            }
            throw e;
        }
    }
}
