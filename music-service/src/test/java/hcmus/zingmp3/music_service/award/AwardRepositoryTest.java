package hcmus.zingmp3.music_service.award;

import hcmus.zingmp3.music_service.award.model.Award;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.UUID;

import static hcmus.zingmp3.music_service.award.AwardDataForTest.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class AwardRepositoryTest {

    @Autowired
    private AwardRepository awardRepository;

    @BeforeAll
    static void beforeAll() {
        System.out.println(BEFORE_SAVE_AWARDS);
    }

    @BeforeEach
    void setUp() {
        awardRepository.deleteAllInBatch();
    }

    @Test
    public void AwardRepository_Save_ReturnSaveAward() {
        Award award = AFTER_SAVE_AWARDS.getFirst();

        Award savedAward = awardRepository.save(award);

        assertNotNull(savedAward);
        assertNotNull(savedAward.getId());
    }

    @Test
    public void AwardRepository_FindById_ReturnAward() {
        Award award = BEFORE_SAVE_AWARDS.getFirst();

        award = awardRepository.save(award);

        Award foundAward = awardRepository.findById(award.getId()).orElse(null);

        assertNotNull(foundAward);
        assertEquals(foundAward, award);
    }

    @Test
    public void AwardRepository_FindAll_ReturnMoreThanOneAward() {
        List<Award> awardList = BEFORE_SAVE_AWARDS;

        awardRepository.saveAll(awardList);

        List<Award> awards = awardRepository.findAll();

        assertNotNull(awards);
        assertThat(awards).hasSize(awardList.size());
    }

    @Test
    public void AwardRepository_FindByName_ReturnAward() {
        Award award = BEFORE_SAVE_AWARDS.getFirst();

        award = awardRepository.save(award);

        Award foundAward = awardRepository.findByName(award.getName()).orElse(null);

        assertNotNull(foundAward);
        assertEquals(foundAward, award);
    }

    @Test
    public void AwardRepository_UpdateAward_ReturnAward() {
        Award award = BEFORE_SAVE_AWARDS.getFirst();

        award = awardRepository.save(award);

        Award updatedAward = new Award();
        BeanUtils.copyProperties(award, updatedAward, "id");
        updatedAward.setId(award.getId());
        updatedAward.setName("Updated Award");

        Award savedAward = awardRepository.save(updatedAward);

        assertNotNull(savedAward);
        assertEquals(savedAward, updatedAward);
    }

    @Test
    public void AwardRepository_DeleteAward_ReturnNull() {
        Award award = BEFORE_SAVE_AWARDS.getFirst();

        awardRepository.save(award);

        awardRepository.deleteById(award.getId());

        Award foundAward = awardRepository.findById(award.getId()).orElse(null);

        assertNull(foundAward);
    }

}