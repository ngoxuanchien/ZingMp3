package zingmp3.service;

import zingmp3.dto.SongDto;
import zingmp3.model.Song;

import java.util.List;
import java.util.UUID;

public interface SongService {
    List<SongDto> findAll();
    SongDto findById(UUID id);
    SongDto create(SongDto songRecord);
    SongDto update(UUID id, SongDto songRecord);
    void delete(UUID id);

    List<SongDto> findAll(Integer pageNumber, Integer pageSize);
}
