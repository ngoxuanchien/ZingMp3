package hcmus.zingmp3.award;

import hcmus.zingmp3.award.consumer.AwardKafkaConsumer;
import hcmus.zingmp3.award.model.Award;
import hcmus.zingmp3.award.model.AwardAvro;
import hcmus.zingmp3.award.model.AwardRestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

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

    @Override
    public List<AwardRestResponse> getAllAwards(Pageable pageable) {

        return awardRepository
                .findAll(pageable)
                .map(awardMapper::toDto)
                .toList();
    }

    @Override
    public List<AwardRestResponse> getAllAwards() {
        return StreamSupport.stream(awardRepository.findAll().spliterator(), true)
                .map(awardMapper::toDto)
                .toList();
    }

    @Override
    public List<AwardRestResponse> getAwardLog() {
        return List.of();
    }
}

