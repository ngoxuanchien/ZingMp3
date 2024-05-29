package hcmus.zingmp3.award;

import hcmus.zingmp3.award.model.Award;
import hcmus.zingmp3.award.model.AwardRequest;
import hcmus.zingmp3.award.model.AwardResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static hcmus.zingmp3.award.AwardDataForTest.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AwardMapperTest {

    @InjectMocks
    AwardMapper awardMapper;

    @Test
    public void AwardMapper_ToDTO_ReturnAwardResponse() {
        Award award = AwardDataForTest.AFTER_SAVE_AWARDS.getFirst();
        AwardResponse awardResponse = AwardDataForTest.AWARD_RESPONSES.getFirst();

        AwardResponse response = awardMapper.toDTO(award);

        assertNotNull(response);
        assertEquals(response, awardResponse);
    }

    @Test
    public void AwardMapper_ToEntity_ReturnAward() {
        AwardRequest awardRequest = AwardDataForTest.AWARD_REQUESTS.getFirst();
        Award award = AwardDataForTest.BEFORE_SAVE_AWARDS.getFirst();

        Award response = awardMapper.toEntity(awardRequest);

        assertNotNull(response);
        assertEquals(response, award);
    }

    @Test
    public void AwardMapper_ToDTO_ReturnListAwardResponse() {
        List<Award> awards;
        awards = AwardDataForTest.AFTER_SAVE_AWARDS;
        List<AwardResponse> awardResponses;
        awardResponses = AwardDataForTest.AWARD_RESPONSES;

        List<AwardResponse> responses = awardMapper.toDTO(awards);

        assertNotNull(responses);
        assertEquals(responses, awardResponses);
    }
}