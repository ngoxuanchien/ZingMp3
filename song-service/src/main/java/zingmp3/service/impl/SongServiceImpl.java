package zingmp3.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import zingmp3.converter.model.SongConverter;
import zingmp3.dto.SongDto;
import zingmp3.exception.SongNotFoundException;
import zingmp3.model.Song;
import zingmp3.repository.SongRepository;
import zingmp3.service.SongService;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;
    private final SongConverter songConverter;

    @Value("${server.max-song-response}")
    private Integer maxSongResponse;

    @Override
    public List<SongDto> findAll() {
        return songRepository
                .findAll(PageRequest.of(0, maxSongResponse))
                .stream()
                .map(songConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public SongDto findById(UUID id) {
        return songRepository
                .findById(id)
                .map(songConverter::convert)
                .orElseThrow(() -> new SongNotFoundException("Song not found"));
    }

    @Override
    public SongDto create(SongDto songDto) {
        Song song = songConverter.reverseConvert(songDto);
        song.setId(null);
        return songConverter.convert(songRepository.save(song));
    }

    @Override
    public SongDto update(UUID id, SongDto songDto) {
        if (!songRepository.existsById(id)) {
            throw new SongNotFoundException("Song not found");
        }
        Song song = songConverter.reverseConvert(songDto);
        song.setId(id);
        return songConverter.convert(songRepository.save(song));
    }

    @Override
    public void delete(UUID id) {
        if (!songRepository.existsById(id)) {
            throw new SongNotFoundException("Song not found");
        }
        songRepository.deleteById(id);
    }

    @Override
    public List<SongDto> findAll(Integer pageNumber, Integer pageSize) {
//        int offset = pageSize * (pageNumber - 1);
        return songRepository.findAll(PageRequest.of(pageNumber, Math.min(maxSongResponse, pageSize)))
                .stream()
                .map(songConverter::convert)
                .collect(Collectors.toList());
    }
}
