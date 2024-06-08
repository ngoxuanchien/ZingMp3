package hcmus.zingmp3.core.service.artist;

import hcmus.zingmp3.artist.ArtistResponseGrpc;

import java.util.UUID;

public interface ArtistService {

    ArtistResponseGrpc getById(UUID uuid);

    boolean isExist(UUID uuid);
}
