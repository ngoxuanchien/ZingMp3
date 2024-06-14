package hcmus.zingmp3.core.service.artist;

import hcmus.zingmp3.artist.ArtistResponseGrpc;
import hcmus.zingmp3.core.web.dto.ArtistResponse;

import java.util.Set;
import java.util.UUID;

public interface ArtistService {

    ArtistResponse getById(UUID uuid);

    boolean isExist(UUID uuid);

//    ArtistDto getById(UUID uuid);

    Set<ArtistResponse> getAllById(Set<UUID> uuids);
}
