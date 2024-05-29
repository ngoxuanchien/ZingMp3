package hcmus.zingmp3.award;

import hcmus.zingmp3.award.model.Award;
import hcmus.zingmp3.award.model.AwardAvro;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AwardServiceImpl implements AwardService {

    private final AwardRepository awardRepository;
    private final AwardMapper awardMapper;

    @Override
    public AwardRestResponse getAwardByName(String name) {
        Award award = awardRepository.findByName(name).orElseThrow(() -> new RuntimeException("Award not found with name: " + name));
        return awardMapper.toDto(award);
    }

    @Override
    public void createAward(AwardAvro award) {
        awardRepository.save(awardMapper.toEntity(award));
    }

    @Override
    public void updateAward(AwardAvro award) {
        awardRepository.save(awardMapper.toEntity(award));
    }

    @Override
    public void deleteAward(String id) {
        awardRepository.deleteById(id);
    }
}

