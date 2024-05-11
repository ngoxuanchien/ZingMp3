package hcmus.zingmp3.music_service.award;

import hcmus.zingmp3.music_service.MusicServiceApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = MusicServiceApplication.class)
//@TestPropertySource(locations = "classpath:application-test.properties")
class AwardMapperTest {

    AwardMapper awardMapper;

    @BeforeEach
    void setUp() {
        awardMapper = new AwardMapper();
    }

    @Test
    void toDTO() {


    }

    @Test
    void toEntity() {
    }

    @Test
    void testToDTO() {
    }
}