package hcmus.zingmp3.song;

import hcmus.zingmp3.song.model.Song;
import hcmus.zingmp3.song.model.Song;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static hcmus.zingmp3.song.SongDataForTest.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class SongRepositoryTest {

    @Autowired
    private SongRepository songRepository;

    @Test
    void SongRepository_Save_ReturnSong() {
        Song songGiven = SongDataForTest.SONG_BEFORE_SAVE.getFirst();

        Song savedSong = songRepository.save(songGiven);

        assertNotNull(savedSong);
        assertNotNull(savedSong.getId());
    }

    @Test
    void SongRepository_FindById_ReturnSong() {
        Song songGiven = SongDataForTest.SONG_AFTER_SAVE.getFirst();

        songGiven = songRepository.save(songGiven);

        Song foundSong = songRepository.findById(songGiven.getId()).orElse(null);

        assertNotNull(foundSong);
        assertEquals(foundSong, songGiven);
    }

    @Test
    void SongRepository_FindByAlias_ReturnSong() {
        Song songGiven = SongDataForTest.SONG_AFTER_SAVE.getFirst();

        songGiven = songRepository.save(songGiven);

        Song foundSong = songRepository.findByAlias(songGiven.getAlias()).orElse(null);

        assertNotNull(foundSong);
        assertEquals(foundSong, songGiven);
    }

    @Test
    void SongRepository_FindAll_ReturnMoreThanOneSong() {
        List<Song> givenSongs = SongDataForTest.SONG_BEFORE_SAVE;

        songRepository.saveAll(givenSongs);

        List<Song> songs = songRepository.findAll();

        assertNotNull(songs);
        assertEquals(songs.size(), givenSongs.size());
    }

    @Test
    void SongRepository_FindAllPageable_ReturnSongList() {
        List<Song> givenSongs = SongDataForTest.SONG_BEFORE_SAVE;
        Pageable pageable = PageRequest.of(0, givenSongs.size());

        songRepository.saveAll(givenSongs);

        List<Song> songs = songRepository.findAll(pageable).getContent();

        assertNotNull(songs);
        assertEquals(songs.size(), pageable.getPageSize());
    }

    @Test
    void SongRepository_Update_ReturnUpdatedSong() {
        Song song = SongDataForTest.SONG_BEFORE_SAVE.getFirst();

        song = songRepository.save(song);

        Song updatedSong = new Song();
        BeanUtils.copyProperties(song, updatedSong);
        updatedSong.setTitle("Updated Title");

        Song savedSong = songRepository.save(updatedSong);

        assertNotNull(savedSong);
        assertEquals(savedSong, updatedSong);

    }

    @Test
    void SongRepository_DeleteById_ReturnNull() {
        Song song = SongDataForTest.SONG_BEFORE_SAVE.getFirst();

        song = songRepository.save(song);

        songRepository.deleteById(song.getId());

        Song foundSong = songRepository.findById(song.getId()).orElse(null);

        assertNull(foundSong);
    }





}