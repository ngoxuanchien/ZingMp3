package hcmus.zingmp3.service.song;

import hcmus.zingmp3.web.dto.SongResponse;

import java.util.Set;
import java.util.UUID;

public interface SongService {
    boolean isExist(UUID songId);

    SongResponse getById(UUID songId);

    Set<SongResponse> getAllById(Set<UUID> songIds);
}
