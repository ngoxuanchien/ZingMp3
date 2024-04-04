package zingmp3.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import zingmp3.dto.SongDto;
import zingmp3.exception.SongNotFoundException;
import zingmp3.model.Song;
import zingmp3.repository.SongRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class SongService {
    private final SongRepository songRepository;

    public Song getSong(Integer songId) {
        return songRepository.findById(songId).orElseThrow(() -> new SongNotFoundException("Song not found with id: " + songId));
    }

    public List<Song> getAllSong() {
        return songRepository.findAll();
    }

    public Song addNewSong(SongDto request) {
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
        return songRepository.save(song);

    }

    public Song updateSong(Integer songId, SongDto request) {
        Song song = songRepository.findById(songId).orElseThrow(() -> new SongNotFoundException("Song not found with id: " + songId));
        song.setSongName(request.getSongName());
        song.setDuration(request.getDuration());
        song.setSongWriter(request.getSongWriter());
        song.setThumbnail(request.getThumbnail());
        song.setProvidedBy(request.getProvidedBy());
        song.setLyric(request.getLyric());
        song.setLiked(request.getLiked());
        song.setPlayed(request.getPlayed());

        return songRepository.save(song);
    }

    public void deleteSongInService(Integer songId) {
        Song song = songRepository.findById(songId).orElseThrow(() -> new SongNotFoundException("Song not found with id: " + songId));
        songRepository.delete(song);
    }
}
