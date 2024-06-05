package hcmus.zingmp3.core.service.artist;

import hcmus.zingmp3.artist.ArtistGrpcRequest;
import hcmus.zingmp3.artist.ArtistServiceGrpc;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;

import java.util.UUID;

public class ArtistServiceImpl implements ArtistService {

    @GrpcClient("artist-service")
    ArtistServiceGrpc.ArtistServiceBlockingStub artistClient;

    @Override
    public boolean isArtistExist(UUID artistId) {

        try {
            artistClient.isExist(
                    ArtistGrpcRequest.newBuilder()
                            .setId(artistId.toString())
                            .build());
            return true;
        } catch (StatusRuntimeException sre) {
            if (sre.getStatus().getCode().equals(Status.Code.NOT_FOUND)) {
                return false;
            }
        }
    }
}
