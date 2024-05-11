package hcmus.zingmp3.music_service.award;

import hcmus.zingmp3.music_service.award.model.Award;
import hcmus.zingmp3.music_service.award.model.AwardRequest;
import hcmus.zingmp3.music_service.award.model.AwardResponse;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AwardServiceTest {
    @InjectMocks
    AwardServiceImpl awardService;

    @Mock
    AwardMapper awardMapper;

    @Mock
    AwardRepository awardRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterAll
    static void afterAll() {
        System.out.println("All tests are done.");
    }

    @Test
    public void testGetAwardById() {
        // Given
        AwardRequest awardRequest = AwardRequest.builder().name("Top nhạc sĩ trẻ 2012").build();
        Award award = Award.builder().name("Top nhạc sĩ trẻ 2012").build();
        AwardResponse response = AwardResponse.builder().name("Top nhạc sĩ trẻ 2012").build();
        response.setId(UUID.randomUUID());

        // Mock the call
        when(awardMapper.toEntity(awardRequest)).thenReturn(award);
        when(awardRepository.save(award)).thenReturn(award);
        when(awardMapper.toDTO(award)).thenReturn(response);

        // When
        AwardResponse awardResponse = awardService.createAward(awardRequest);

        // Then
        assertEquals(awardRequest.getName(), awardResponse.getName());
        assertNotNull(awardResponse.getId());

        verify(awardMapper, times(1)).toEntity(awardRequest);
        verify(awardRepository, times(1)).save(award);
        verify(awardMapper, times(1)).toDTO(award);
    }
}