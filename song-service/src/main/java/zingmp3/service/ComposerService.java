package zingmp3.service;

import zingmp3.dto.ComposerDto;

import java.util.List;
import java.util.UUID;

public interface ComposerService {
    List<ComposerDto> findAll();
    List<ComposerDto> findAll(Integer pageNumber, Integer pageSize);
    ComposerDto findById(UUID id);
    ComposerDto create(ComposerDto composerDto);
    ComposerDto update(UUID id, ComposerDto composerDto);
    void delete(UUID id);


}
