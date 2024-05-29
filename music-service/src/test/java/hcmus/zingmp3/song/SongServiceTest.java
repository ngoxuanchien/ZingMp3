package hcmus.zingmp3.song;

import hcmus.zingmp3.song.model.Song;
import hcmus.zingmp3.song.model.SongRequest;
import hcmus.zingmp3.song.model.SongResponse;
import hcmus.zingmp3.song.service.SongServiceImpl;
import hcmus.zingmp3.song.model.Song;
import hcmus.zingmp3.song.model.SongRequest;
import hcmus.zingmp3.song.model.SongResponse;
import hcmus.zingmp3.song.service.SongServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static hcmus.zingmp3.song.SongDataForTest.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SongServiceTest {
    @InjectMocks
    private SongServiceImpl songService;

    @Mock
    private SongRepository songRepository;

    @Mock
    private SongMapper songMapper;

    @Test
    void SongService_CreateSong_ReturnSongResponse() {
        SongRequest songRequest = SongDataForTest.SONG_REQUESTS.getFirst();
        Song songBeforeSave = SongDataForTest.SONG_BEFORE_SAVE.getFirst();
        Song songAfterSave = SongDataForTest.SONG_AFTER_SAVE.getFirst();
        SongResponse songResponse = SongDataForTest.SONG_RESPONSES.getFirst();

        when(songMapper.toEntity(songRequest)).thenReturn(songBeforeSave);
        when(songRepository.save(songBeforeSave)).thenReturn(songAfterSave);
        when(songMapper.toDTO(songAfterSave)).thenReturn(songResponse);

        SongResponse savedSongResponse = songService.createSong(songRequest);

        assertNotNull(savedSongResponse);
        assertEquals(savedSongResponse, songResponse);

        verify(songMapper, times(1)).toEntity(songRequest);
        verify(songRepository, times(1)).save(songBeforeSave);
        verify(songMapper, times(1)).toDTO(songAfterSave);
    }

    @Test
    void SongService_GetSongById_ReturnSongDto() {
        UUID id = SongDataForTest.SONG_IDS.getFirst();
        Song song = SongDataForTest.SONG_AFTER_SAVE.getFirst();
        SongResponse songResponse = SongDataForTest.SONG_RESPONSES.getFirst();

        when(songRepository.findById(id)).thenReturn(Optional.of(song));
        when(songMapper.toDTO(song)).thenReturn(songResponse);

        SongResponse response = songService.getSongById(song.getId());

        assertNotNull(response);
        assertEquals(response, songResponse);

        verify(songRepository, times(1)).findById(id);
        verify(songMapper, times(1)).toDTO(song);
    }

    @Test
    void SongService_GetAllSongs_ReturnListSongResponse() {
        Pageable pageable = mock(Pageable.class);
        List<Song> songList = SongDataForTest.SONG_AFTER_SAVE;
        Page<Song> songPage = new PageImpl<>(songList, pageable, songList.size());
        List<SongResponse> songResponseList = SongDataForTest.SONG_RESPONSES;

        when(songRepository.findAll(pageable)).thenReturn(songPage);
        when(songMapper.toDTO(SongDataForTest.SONG_AFTER_SAVE)).thenReturn(songResponseList);

        List<SongResponse> response = songService.getAllSongs(pageable);

        assertNotNull(response);
        assertEquals(response.size(), songResponseList.size());

        verify(songRepository, times(1)).findAll(pageable);
        verify(songMapper, times(1)).toDTO(songList);
    }

    @Test
    void SongService_UpdateSong_ReturnUpdatedSong() {
        UUID id = SongDataForTest.SONG_IDS.getFirst();
        SongRequest songRequest = SongDataForTest.SONG_REQUESTS.getFirst();
        Song songBeforeSave = SongDataForTest.SONG_BEFORE_SAVE.getFirst();
        Song songAfterSave = SongDataForTest.SONG_AFTER_SAVE.getFirst();
        SongResponse songResponse = SongDataForTest.SONG_RESPONSES.getFirst();

        when(songRepository.findById(id)).thenReturn(Optional.of(songAfterSave));
        when(songMapper.toEntity(songRequest)).thenReturn(songBeforeSave);
        when(songRepository.save(songBeforeSave)).thenReturn(songAfterSave);
        when(songMapper.toDTO(songAfterSave)).thenReturn(songResponse);

        SongResponse response = songService.updateSong(id, songRequest);

        assertNotNull(response);
        assertEquals(response, songResponse);

        verify(songRepository, times(1)).findById(id);
        verify(songMapper, times(1)).toEntity(songRequest);
        verify(songRepository, times(1)).save(songBeforeSave);
        verify(songMapper, times(1)).toDTO(songAfterSave);
    }

    @Test
    void SongService_DeleteSong_ReturnVoid() {
        UUID id = SongDataForTest.SONG_IDS.getFirst();
        Song song = SongDataForTest.SONG_AFTER_SAVE.getFirst();

        when(songRepository.findById(id)).thenReturn(Optional.of(song));

        assertAll(() -> songService.deleteSong(id));

        verify(songRepository, times(1)).findById(id);
        verify(songRepository, times(1)).deleteById(id);
    }
}