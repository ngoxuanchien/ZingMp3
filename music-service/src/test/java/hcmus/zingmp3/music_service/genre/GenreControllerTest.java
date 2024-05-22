package hcmus.zingmp3.music_service.genre;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hcmus.zingmp3.music_service.genre.model.GenreRequest;
import hcmus.zingmp3.music_service.genre.model.GenreResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.awt.print.Pageable;
import java.util.List;
import java.util.UUID;

import static hcmus.zingmp3.music_service.genre.GenreDataForTest.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = GenreController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class GenreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GenreService genreService;

    @Test
    void GenreController_CreateGenre_ReturnCreated() throws Exception {
        GenreRequest genreRequest = GENRE_REQUESTS.getFirst();
        GenreResponse genreResponse = GENRE_RESPONSES.getFirst();

        when(genreService.createGenre(genreRequest)).thenReturn(genreResponse);

        ResultActions response = mockMvc.perform(post("/api/music/genre")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(genreRequest)));

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(genreResponse)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void GenreController_GetGenreById_ReturnOk() throws Exception {
        UUID id = GENRE_IDS.getFirst();
        GenreResponse genreResponse = GENRE_RESPONSES.getFirst();

        when(genreService.getGenreById(id)).thenReturn(genreResponse);

        ResultActions response = mockMvc.perform(get("/api/music/genre/" + id)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(genreResponse)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void GenreController_GetAllGenres_ReturnOk() throws Exception {
        List<GenreResponse> genreResponses = GENRE_RESPONSES;

        given(genreService.getAllGenres(ArgumentMatchers.any()))
                .willAnswer(invocation -> genreResponses);

        ResultActions response = mockMvc.perform(get("/api/music/genre")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(genreResponses)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void GenreController_UpdateGenre_ReturnOk() throws Exception {
        UUID id = GENRE_IDS.getFirst();
        GenreRequest genreRequest = GENRE_REQUESTS.getFirst();
        GenreResponse genreResponse = GENRE_RESPONSES.getFirst();

        when(genreService.updateGenre(id, genreRequest)).thenReturn(genreResponse);

        ResultActions response = mockMvc.perform(put("/api/music/genre/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(genreRequest)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(genreResponse)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void GenreController_DeleteGenre_ReturnNoContent() throws Exception {
        UUID id = GENRE_IDS.getFirst();

        doNothing().when(genreService).deleteGenre(id);

        ResultActions response = mockMvc.perform(delete("/api/music/genre/" + id)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print());
    }
}