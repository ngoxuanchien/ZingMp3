package hcmus.zingmp3.core.service.artist;

import hcmus.zingmp3.artist.ArtistGrpcResponse;

import java.util.UUID;

public interface ArtistService {

    ArtistGrpcResponse getById(UUID uuid);

    boolean isExist(UUID uuid);
}
