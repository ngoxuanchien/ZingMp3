package zingmp3.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import zingmp3.converter.model.ComposerConverter;
import zingmp3.dto.ComposerDto;
import zingmp3.exception.ComposerNotFoundException;
import zingmp3.model.Composer;
import zingmp3.repository.ComposerRepository;
import zingmp3.service.ComposerService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComposerServiceImpl implements ComposerService {

    private final ComposerRepository composerRepository;
    private final ComposerConverter composerConverter;

    @Value("${server.max-composer-response}")
    private Integer maxComposerResponse;

    @Override
    public List<ComposerDto> findAll() {
        return composerRepository.findAll(PageRequest.of(0, maxComposerResponse))
                .stream()
                .map(composerConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<ComposerDto> findAll(Integer pageNumber, Integer pageSize) {
        return composerRepository.findAll(PageRequest.of(pageNumber, Math.min(pageSize, maxComposerResponse)))
                .stream()
                .map(composerConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public ComposerDto findById(UUID id) {
        return composerRepository.findById(id)
                .map(composerConverter::convert)
                .orElseThrow(() -> new ComposerNotFoundException("Composer not found"));
    }

    @Override
    public ComposerDto create(ComposerDto composerDto) {
        Composer newComposer = composerConverter.reverseConvert(composerDto);
        newComposer.setId(null);
        return composerConverter.convert(composerRepository.save(newComposer));
    }

    @Override
    public ComposerDto update(UUID id, ComposerDto composerDto) {
        if (!composerRepository.existsById(id)) {
            throw new ComposerNotFoundException("Composer not found");
        }

        Composer newComposer = composerConverter.reverseConvert(composerDto);
        newComposer.setId(id);
        return composerConverter.convert(composerRepository.save(newComposer));
    }

    @Override
    public void delete(UUID id) {
        if (!composerRepository.existsById(id)) {
            throw new ComposerNotFoundException("Composer not found");
        }

        composerRepository.deleteById(id);
    }
}
