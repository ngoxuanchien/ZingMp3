package hcmus.zingmp3.music_service.artist;

import com.fasterxml.jackson.databind.ObjectMapper;
import hcmus.zingmp3.music_service.artist.model.ArtistRequest;
import hcmus.zingmp3.music_service.artist.model.ArtistResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.UUID;

import static hcmus.zingmp3.music_service.artist.ArtistDataForTest.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = ArtistController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class ArtistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArtistService artistService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void ArtistController_CreateArtist_ReturnCreated() throws Exception {
        ArtistRequest artistRequest = ARTIST_REQUESTS.getFirst();
        ArtistResponse artistResponse = ARTIST_RESPONSES.getFirst();

        MockMultipartFile artist = new MockMultipartFile("artist", "artist.json", "application/json", objectMapper.writeValueAsBytes(artistRequest));
        MockMultipartFile awards = new MockMultipartFile("awards", "awards.json", "application/json", objectMapper.writeValueAsBytes(new String[]{"award1", "award2"}));
        MockMultipartFile thumbnail = new MockMultipartFile("thumbnail", "thumbnail", "image/png", new byte[0]);

        given(artistService.createArtist(ArgumentMatchers.any()))
                .willAnswer(invocation -> artistResponse);
//        when(artistService.createArtist(artistRequest)).thenReturn(artistResponse);
        ResultActions response = mockMvc.perform(multipart("/api/music/artist")
                .file(artist)
                .file(awards)
                .file(thumbnail));

        response.andExpect(MockMvcResultMatchers
                                   .status()
                                   .isCreated())
                .andExpect(MockMvcResultMatchers
                                    .content()
                                    .json(objectMapper.writeValueAsString(artistResponse)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void ArtistController_GetAllArtists_ReturnOk() throws Exception {
        List<ArtistResponse> artistResponseList = ARTIST_RESPONSES;

        given(artistService.getAllArtists(ArgumentMatchers.any()))
                .willAnswer(invocation -> artistResponseList);

        mockMvc.perform(get("/api/music/artist")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                                   .status()
                                   .isOk())
                .andExpect(MockMvcResultMatchers
                                   .content()
                                   .json(objectMapper.writeValueAsString(artistResponseList)))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void ArtistController_GetArtistById_ReturnOk() throws Exception {
        UUID id = ARTIST_IDS.getFirst();
        ArtistResponse artistResponse = ARTIST_RESPONSES.getFirst();

        when(artistService.getArtistById(id.toString()))
                .thenReturn(artistResponse);

        mockMvc.perform(get("/api/music/artist/" + id)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                                   .status()
                                   .isOk())
                .andExpect(MockMvcResultMatchers
                                   .content()
                                   .json(objectMapper.writeValueAsString(artistResponse)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void ArtistController_DeleteArtist_ReturnNoContent() throws Exception {
        UUID id = ARTIST_IDS.getFirst();
        doNothing().when(artistService).deleteArtist(id.toString());

        ResultActions resultActions = mockMvc.perform(delete("/api/music/artist/" + id)
                                .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(MockMvcResultMatchers
                                   .status()
                                   .isNoContent())
                .andDo(MockMvcResultHandlers.print());
    }

}