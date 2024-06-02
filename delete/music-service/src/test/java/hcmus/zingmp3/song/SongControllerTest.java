package hcmus.zingmp3.song;

import com.fasterxml.jackson.databind.ObjectMapper;
import hcmus.zingmp3.song.model.SongRequest;
import hcmus.zingmp3.song.model.SongResponse;
import hcmus.zingmp3.song.service.SongService;
import hcmus.zingmp3.song.model.SongRequest;
import hcmus.zingmp3.song.model.SongResponse;
import hcmus.zingmp3.song.service.SongService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

import static hcmus.zingmp3.song.SongDataForTest.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = SongController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class SongControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SongService songService;

    @Test
    void SongController_CreateSong_ReturnCreated() throws Exception {
        SongRequest songRequest = SongDataForTest.SONG_REQUESTS.getFirst();
        SongResponse songResponse = SongDataForTest.SONG_RESPONSES.getFirst();
        Set<String> artists = new HashSet<>(Arrays.asList("artist-1", "artist-2"));
        Set<String> genres = new HashSet<>(Arrays.asList("genre-1", "genre-2"));
        Set<String> composers = new HashSet<>(Arrays.asList("composer-1", "composer-2"));

        MockMultipartFile song = new MockMultipartFile("song", "song.json", "application/json", objectMapper.writeValueAsBytes(songRequest));
        MockMultipartFile thumbnailFile = new MockMultipartFile("thumbnailFile", "thumbnail.jpg", "image/png", new byte[0]);
        MockMultipartFile audio = new MockMultipartFile("audioFiles", "audios.mp3", "audio/mpeg", new byte[0]);
        MockMultipartFile artist = new MockMultipartFile("artists", "artist.json", "application/json", objectMapper.writeValueAsBytes(artists));
        MockMultipartFile genre = new MockMultipartFile("genres", "genre.json", "application/json", objectMapper.writeValueAsBytes(genres));
        MockMultipartFile composer = new MockMultipartFile("composers", "composer.json", "application/json", objectMapper.writeValueAsBytes(composers));
        MockMultipartFile[] audios = new MockMultipartFile[] {audio};

        songRequest.addAllArtists(artists);
        songRequest.addAllGenres(genres);
        songRequest.addAllComposers(composers);
        songRequest.addAudioFiles(audios);
        songRequest.setThumbnailFile(thumbnailFile);

        when(songService.createSong(ArgumentMatchers.any(SongRequest.class))).thenReturn(songResponse);

        ResultActions response = mockMvc
                .perform(
                        multipart(HttpMethod.POST, "/api/music/song")
                                .file(song)
                                .file(thumbnailFile)
                                .file(audio)
                                .file(artist)
                                .file(genre)
                                .file(composer));

        String expectedJson = objectMapper.writeValueAsString(songResponse);
        String actualJson = response.andReturn().getResponse().getContentAsString();

        System.out.println("Expected JSON: " + expectedJson);
        System.out.println("Actual JSON: " + actualJson);

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(expectedJson))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void SongController_GetSongById_ReturnOk() throws Exception {
        UUID id = SongDataForTest.SONG_IDS.getFirst();
        SongResponse songResponse = SongDataForTest.SONG_RESPONSES.getFirst();

        when(songService.getSongById(id)).thenReturn(songResponse);

        ResultActions response = mockMvc.perform(get("/api/music/song/{id}", id)
                                                         .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(songResponse)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void SongController_GetAllSongs_ReturnOk() throws Exception {
        int page = 0;
        int size = 10;
        String sort = "id";
        String order = "asc";
        Sort sortData = Sort.by(Sort.Direction.fromString(order), sort);
        Pageable pageable = PageRequest.of(page, size, sortData);
        List<SongResponse> songResponses = SongDataForTest.SONG_RESPONSES;

        when(songService.getAllSongs(pageable)).thenReturn(songResponses);

        ResultActions response = mockMvc
                .perform(get("/api/music/song")
                                 .contentType(MediaType.APPLICATION_JSON)
                                 .param("page", String.valueOf(page))
                                 .param("size", String.valueOf(size))
                                 .param("sort", sort)
                                 .param("order", order));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(songResponses)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void SongController_UpdateSong_ReturnOk() throws Exception {
        UUID id = SongDataForTest.SONG_IDS.getFirst();
        SongRequest songRequest = SongDataForTest.SONG_REQUESTS.getFirst();
        SongResponse songResponse = SongDataForTest.SONG_RESPONSES.getFirst();
        Set<String> artists = new HashSet<>(Arrays.asList("artist-1", "artist-2"));
        Set<String> genres = new HashSet<>(Arrays.asList("genre-1", "genre-2"));
        Set<String> composers = new HashSet<>(Arrays.asList("composer-1", "composer-2"));

        MockMultipartFile song = new MockMultipartFile("song", "song.json", "application/json", objectMapper.writeValueAsBytes(songRequest));
        MockMultipartFile thumbnailFile = new MockMultipartFile("thumbnailFile", "thumbnail.jpg", "image/png", new byte[0]);
        MockMultipartFile audio = new MockMultipartFile("audioFiles", "audios.mp3", "audio/mpeg", new byte[0]);
        MockMultipartFile artist = new MockMultipartFile("artists", "artist.json", "application/json", objectMapper.writeValueAsBytes(artists));
        MockMultipartFile genre = new MockMultipartFile("genres", "genre.json", "application/json", objectMapper.writeValueAsBytes(genres));
        MockMultipartFile composer = new MockMultipartFile("composers", "composer.json", "application/json", objectMapper.writeValueAsBytes(composers));
        MultipartFile[] audios = new MultipartFile[] {audio};

        songRequest.addAllArtists(artists);
        songRequest.addAllGenres(genres);
        songRequest.addAllComposers(composers);
        songRequest.addAudioFiles(audios);
        songRequest.setThumbnailFile(thumbnailFile);

        when(songService.updateSong(id, songRequest)).thenReturn(songResponse);

        ResultActions response = mockMvc
                .perform(multipart(
                        HttpMethod.PUT, "/api/music/song/{id}", id)
                                 .file(song)
                                 .file(thumbnailFile)
                                 .file(audio)
                                 .file(artist)
                                 .file(genre)
                                 .file(composer));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(songResponse)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void SongController_DeleteSong_ReturnNoContent() throws Exception {
        UUID id = SongDataForTest.SONG_IDS.getFirst();

        doNothing().when(songService).deleteSong(id);

        ResultActions response = mockMvc
                .perform(delete("/api/music/song/{id}", id)
                                 .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print());
    }
}