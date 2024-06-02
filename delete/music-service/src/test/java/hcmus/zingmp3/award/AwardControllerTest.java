package hcmus.zingmp3.award;

import com.fasterxml.jackson.databind.ObjectMapper;
import hcmus.zingmp3.award.model.AwardRequest;
import hcmus.zingmp3.award.model.AwardResponse;
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

import java.util.List;
import java.util.UUID;

import static hcmus.zingmp3.award.AwardDataForTest.AWARD_REQUESTS;
import static hcmus.zingmp3.award.AwardDataForTest.AWARD_RESPONSES;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AwardController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class AwardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AwardService awardService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void AwardController_CreateAward_ReturnCreated() throws Exception {
        AwardRequest awardRequest = AwardDataForTest.AWARD_REQUESTS.getFirst();
        AwardResponse awardResponse = AwardDataForTest.AWARD_RESPONSES.getFirst();

        when(awardService.createAward(awardRequest)).thenReturn(awardResponse);

        ResultActions response = mockMvc.perform(post("/api/music/award")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(awardRequest)));

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(awardResponse)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void AwardController_GetAwardById_ReturnOk() throws Exception {
        UUID id = AwardDataForTest.AWARD_RESPONSES.getFirst().getId();
        AwardResponse awardResponse = AwardDataForTest.AWARD_RESPONSES.getFirst();

        when(awardService.getAwardById(id)).thenReturn(awardResponse);

        ResultActions response = mockMvc.perform(get("/api/music/award/" + id)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(awardResponse)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void AwardController_GetAllAwards_ReturnOk() throws Exception {
        List<AwardResponse> awardResponses = AwardDataForTest.AWARD_RESPONSES;


        given(awardService.getAllAwards(ArgumentMatchers.any()))
                .willAnswer(invocation -> awardResponses);

        ResultActions response = mockMvc.perform(get("/api/music/award")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(awardResponses)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void AwardController_UpdateAward_ReturnOk() throws Exception {
        UUID id = AwardDataForTest.AWARD_RESPONSES.getFirst().getId();
        AwardRequest awardRequest = AwardDataForTest.AWARD_REQUESTS.getFirst();
        AwardResponse awardResponse = AwardDataForTest.AWARD_RESPONSES.getFirst();

        when(awardService.updateAward(id, awardRequest)).thenReturn(awardResponse);

        ResultActions response = mockMvc.perform(put("/api/music/award/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(awardRequest)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(awardResponse)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void AwardController_DeleteAward_ReturnNoContent() throws Exception {
        UUID id = AwardDataForTest.AWARD_RESPONSES.getFirst().getId();

        doNothing().when(awardService).deleteAward(id);

        ResultActions response = mockMvc.perform(delete("/api/music/award/" + id)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print());
    }
}