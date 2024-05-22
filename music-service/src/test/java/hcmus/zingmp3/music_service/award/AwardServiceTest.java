package hcmus.zingmp3.music_service.award;

import hcmus.zingmp3.music_service.award.model.Award;
import hcmus.zingmp3.music_service.award.model.AwardRequest;
import hcmus.zingmp3.music_service.award.model.AwardResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static hcmus.zingmp3.music_service.award.AwardDataForTest.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AwardServiceTest {
    @InjectMocks
    AwardServiceImpl awardService;

    @Mock
    AwardMapper awardMapper;

    @Mock
    AwardRepository awardRepository;

    @Test
    public void AwardService_CreateAward_ReturnAwardResponse() {
        AwardRequest awardRequest = AWARD_REQUESTS.getFirst();
        Award awardBeforeSave = BEFORE_SAVE_AWARDS.getFirst();
        Award awardAfterSave = AFTER_SAVE_AWARDS.getFirst();
        AwardResponse awardResponse = AWARD_RESPONSES.getFirst();


        when(awardMapper.toEntity(awardRequest)).thenReturn(awardBeforeSave);
        when(awardRepository.save(awardBeforeSave)).thenReturn(awardAfterSave);
        when(awardMapper.toDTO(awardAfterSave)).thenReturn(awardResponse);

        AwardResponse response = awardService.createAward(awardRequest);

        assertNotNull(response);
        assertEquals(response, awardResponse);

        verify(awardMapper, times(1)).toEntity(awardRequest);
        verify(awardRepository, times(1)).save(awardBeforeSave);
        verify(awardMapper, times(1)).toDTO(awardAfterSave);
    }

    @Test
    public void AwardService_GetAwardById_ReturnAwardResponse() {
        AwardRequest awardRequest = AWARD_REQUESTS.getFirst();
        Award award = AFTER_SAVE_AWARDS.getFirst();
        AwardResponse awardResponse = AWARD_RESPONSES.getFirst();

        when(awardRepository.findById(award.getId())).thenReturn(Optional.of(award));
        when(awardMapper.toDTO(award)).thenReturn(awardResponse);

        AwardResponse response = awardService.getAwardById(award.getId());

        assertNotNull(awardResponse);
        assertEquals(awardResponse, response);
        assertEquals(awardResponse.getId(), award.getId());

        verify(awardRepository, times(1)).findById(award.getId());
        verify(awardMapper, times(1)).toDTO(award);
    }

    @Test
    public void AwardService_GetAllAwards_ReturnListOfAwardResponse() {
        Pageable pageable = Mockito.mock(Pageable.class);
        Page<Award> page = new PageImpl<>(AFTER_SAVE_AWARDS, pageable, AFTER_SAVE_AWARDS.size());
        List<AwardResponse> awardResponse = AWARD_RESPONSES;

        when(awardRepository.findAll(pageable)).thenReturn(page);
        when(awardMapper.toDTO(page.getContent())).thenReturn(awardResponse);

        var response = awardService.getAllAwards(pageable);

        assertNotNull(response);
        assertEquals(response.size(), awardResponse.size());
        assertEquals(response, awardResponse);

        verify(awardRepository, times(1)).findAll(pageable);
        verify(awardMapper, times(1)).toDTO(page.getContent());
    }

    @Test
    public void AwardService_UpdateAward_ReturnAwardResponse() {
        AwardRequest awardRequest = AWARD_REQUESTS.getFirst();
        Award beforeSaveAward = BEFORE_SAVE_AWARDS.getFirst();
        Award afterSaveAward = AFTER_SAVE_AWARDS.getFirst();
        AwardResponse awardResponse = AWARD_RESPONSES.getFirst();

        when(awardRepository.findById(afterSaveAward.getId())).thenReturn(Optional.of(afterSaveAward));
        when(awardMapper.toEntity(awardRequest)).thenReturn(beforeSaveAward);
        when(awardRepository.save(beforeSaveAward)).thenReturn(afterSaveAward);
        when(awardMapper.toDTO(afterSaveAward)).thenReturn(awardResponse);

        var response = awardService.updateAward(afterSaveAward.getId(), awardRequest);

        assertNotNull(response);
        assertEquals(response, awardResponse);

        verify(awardRepository, times(1)).findById(afterSaveAward.getId());
        verify(awardMapper, times(1)).toEntity(awardRequest);
        verify(awardRepository, times(1)).save(beforeSaveAward);
        verify(awardMapper, times(1)).toDTO(afterSaveAward);
    }

    @Test
    public void AwardService_DeleteAward_ReturnVoid() {
        Award award = AFTER_SAVE_AWARDS.getFirst();

        when(awardRepository.findById(award.getId())).thenReturn(Optional.of(award));

        assertAll(() -> awardService.deleteAward(award.getId()));

        verify(awardRepository, times(1)).findById(award.getId());
        verify(awardRepository, times(1)).deleteById(award.getId());
    }
}