package hcmus.zingmp3.song.service;

import hcmus.zingmp3.exception.NotFoundException;
import hcmus.zingmp3.song.SongMapper;
import hcmus.zingmp3.song.SongRepository;
import hcmus.zingmp3.song.model.Song;
import hcmus.zingmp3.song.model.SongRequest;
import hcmus.zingmp3.song.model.SongResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {
    private final SongMapper songMapper;
    private final SongRepository songRepository;

    @Override
    public SongResponse createSong(SongRequest songDTO) {
        Song newSong = songMapper.toEntity(songDTO);
        return songMapper.toDTO(songRepository.save(newSong));
    }

    @Override
    public SongResponse getSongById(UUID id) {
        Song song = songRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Song not found with id: " + id));
        return songMapper.toDTO(song);
    }

    @Override
    public List<SongResponse> getAllSongs(Pageable pageable) {
        return songMapper.toDTO(songRepository.findAll(pageable).toList());
    }

    @Override
    public SongResponse updateSong(UUID id, SongRequest request) {
        songRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Song not found with id: " + id));
        Song updatedSong = songMapper.toEntity(request);
        updatedSong.setId(id);
        return songMapper.toDTO(songRepository.save(updatedSong));
    }

    @Override
    public void deleteSong(UUID id) {
        songRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Song not found with id: " + id));
        songRepository.deleteById(id);
    }

    @Override
    public Song getByAlias(String alias) {
        return songRepository.findByAlias(alias).orElseThrow(() -> new NotFoundException("Song not found with alias: " + alias));
    }
}
