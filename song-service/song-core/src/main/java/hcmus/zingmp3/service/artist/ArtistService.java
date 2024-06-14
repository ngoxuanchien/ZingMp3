package hcmus.zingmp3.service.artist;

import hcmus.zingmp3.ArtistResponseGrpc;
import hcmus.zingmp3.web.dto.ArtistResponse;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface ArtistService {

    ArtistResponse getById(UUID uuid);

    boolean isExist(UUID uuid);

//    ArtistDto getById(UUID uuid);

    List<ArtistResponse> getAllById(List<UUID> uuids);

    ArtistResponseGrpc getByIdGrpc(UUID uuid);

    List<ArtistResponseGrpc> getAllByIdGrpc(List<UUID> uuids);
}
