package zingmp3.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import zingmp3.converter.model.AlbumConverter;
import zingmp3.dto.AlbumDto;
import zingmp3.exception.AlbumNotFoundException;
import zingmp3.model.Album;
import zingmp3.repository.AlbumRepository;
import zingmp3.service.AlbumService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;
    private final AlbumConverter albumConverter;

    @Value("${server.max-album-response}")
    private Integer maxAlbumResponse;

    @Override
    public List<AlbumDto> findAll() {
        return albumRepository.findAll(PageRequest.of(0, maxAlbumResponse))
                .stream()
                .map(albumConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public AlbumDto findById(UUID id) {
        return albumRepository.findById(id)
                .map(albumConverter::convert)
                .orElseThrow(() -> new AlbumNotFoundException("Album not found"));
    }

    @Override
    public List<AlbumDto> findAll(Integer pageNumber, Integer pageSize) {
        return albumRepository.findAll(PageRequest.of(pageNumber, Math.min(pageSize, maxAlbumResponse)))
                .stream()
                .map(albumConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public AlbumDto create(AlbumDto albumDto) {
        Album album = albumConverter.reverseConvert(albumDto);
        return albumConverter.convert(albumRepository.save(album));
    }

    @Override
    public AlbumDto update(UUID id, AlbumDto albumDto) {
        if (!albumRepository.existsById(id)) {
            throw new AlbumNotFoundException("Album not found");
        }
        Album album = albumConverter.reverseConvert(albumDto);
        album.setId(id);
        return albumConverter.convert(albumRepository.save(album));
    }

    @Override
    public void delete(UUID id) {
        if (!albumRepository.existsById(id)) {
            throw new AlbumNotFoundException("Album not found");
        }
        albumRepository.deleteById(id);
    }
}
