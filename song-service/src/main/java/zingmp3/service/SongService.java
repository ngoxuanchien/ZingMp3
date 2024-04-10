package zingmp3.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zingmp3.dto.SongDTO;
import zingmp3.exception.SongNotFoundException;
import zingmp3.model.Song;
import zingmp3.repository.SongRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class SongService {
    private final SongRepository songRepository;

    public SongDTO findById(Integer songId) {
        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new SongNotFoundException("Song not found with id: " + songId));
        return mapToSongResponse(song);
    }

    private SongDTO mapToSongResponse(Song song) {
        return SongDTO.builder()
                .id(song.getId())
                .songName(song.getSongName())
                .songWriter(song.getSongWriter())
                .lyric(song.getLyric())
                .thumbnail(song.getThumbnail())
                .songImage(song.getSongImage())
                .songFile(song.getSongFile())
                .duration(song.getDuration())
                .played(song.getPlayed())
                .liked(song.getLiked())
                .providedBy(song.getProvidedBy())
                .build();
    }

    public List<SongDTO> findAll() {
        List<Song> songs = songRepository.findAll();
        return songs.stream().map(this::mapToSongResponse).toList();
    }

    public SongDTO createSong(SongDTO request) {
        Song song = Song.builder()
                .songName(request.getSongName())
                .songWriter(request.getSongWriter())
                .thumbnail(request.getThumbnail())
                .lyric(request.getLyric())
                .duration(request.getDuration())
                .providedBy(request.getProvidedBy())
                .liked(request.getLiked())
                .played(request.getPlayed())
                .build();
        songRepository.save(song);
        log.info("Song {} is saved", song.getId());
        return mapToSongResponse(song);

    }

    public SongDTO updateSong(Integer songId, SongDTO request) {
        Song song = songRepository.findById(songId).orElseThrow(() -> new SongNotFoundException("Song not found with id: " + songId));
        song.setSongName(request.getSongName());
        song.setDuration(request.getDuration());
        song.setSongWriter(request.getSongWriter());
        song.setThumbnail(request.getThumbnail());
        song.setProvidedBy(request.getProvidedBy());
        song.setLyric(request.getLyric());
        song.setLiked(request.getLiked());
        song.setPlayed(request.getPlayed());

        songRepository.save(song);
        return mapToSongResponse(song);
    }

    public void delete(Integer songId) {
        Song song = songRepository.findById(songId).orElseThrow(() -> new SongNotFoundException("Song not found with id: " + songId));
        songRepository.delete(song);
    }
}
