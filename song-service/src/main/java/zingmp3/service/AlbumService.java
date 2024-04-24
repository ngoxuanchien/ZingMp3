package zingmp3.service;

import zingmp3.dto.AlbumDto;

import java.util.List;
import java.util.UUID;

public interface AlbumService {
    List<AlbumDto> findAll();
    List<AlbumDto> findAll(Integer pageNumber, Integer pageSize);

    AlbumDto findById(UUID id);

    AlbumDto create(AlbumDto albumDto);

    AlbumDto update(UUID id, AlbumDto albumDto);

    void delete(UUID id);
}
