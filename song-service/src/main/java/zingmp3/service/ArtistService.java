package zingmp3.service;

import zingmp3.dto.ArtistDto;

import java.rmi.server.UID;
import java.util.List;
import java.util.UUID;

public interface ArtistService {
    List<ArtistDto> findAll();
    ArtistDto findById(UUID id);
    ArtistDto create(ArtistDto artistDto);
    ArtistDto update(UUID id, ArtistDto artistDto);
    void delete(UUID id);
}
