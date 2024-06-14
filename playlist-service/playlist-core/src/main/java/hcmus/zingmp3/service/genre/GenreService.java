package hcmus.zingmp3.service.genre;

import hcmus.zingmp3.web.dto.GenreResponse;

import java.rmi.server.UID;
import java.util.Set;
import java.util.UUID;

public interface GenreService {
    GenreResponse getById(UUID id);

    Set<GenreResponse> getAllById(Set<UUID> genreIds);
}
