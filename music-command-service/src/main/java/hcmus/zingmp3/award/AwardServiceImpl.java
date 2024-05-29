package hcmus.zingmp3.award;

import hcmus.zingmp3.award.exeception.AwardAlreadyExistsException;
import hcmus.zingmp3.award.exeception.AwardNotFoundException;
import hcmus.zingmp3.award.model.Award;
import hcmus.zingmp3.award.model.AwardRestRequest;
import hcmus.zingmp3.award.model.AwardRestResponse;
import hcmus.zingmp3.award.producer.AwardKafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AwardServiceImpl implements AwardService {

    private final AwardRepository awardRepository;
    private final AwardMapper awardMapper;
    private final AwardKafkaProducer awardKafkaProducer;

    @Override
    public AwardRestResponse createAward(AwardRestRequest request) {
        if (awardRepository.existsByName(request.getName())) {
            throw new AwardAlreadyExistsException("Award already exists with name: " + request.getName());
        }

        Award newAward = awardRepository.save(awardMapper.toEntity(request));
        awardKafkaProducer.createAward(awardMapper.toAvro(newAward));

        return awardMapper.toDTO(newAward);
    }

    @Override
    public AwardRestResponse updateAward(UUID id, AwardRestRequest request) {
        if (!awardRepository.existsById(id)) {
            throw new AwardNotFoundException("Award not found with id: " + id);
        }

        if (awardRepository.existsByName(request.getName())) {
            throw new AwardAlreadyExistsException("Award already exists with name: " + request.getName());
        }

        var newAward = awardMapper.toEntity(request);
        newAward.setId(id);
        newAward = awardRepository.save(newAward);

        awardKafkaProducer.updateAward(awardMapper.toAvro(newAward));

        return awardMapper.toDTO(newAward);
    }

    @Override
    public void deleteAward(UUID id) {
        Award deletedAward = awardRepository.findById(id)
                .orElseThrow(() -> new AwardNotFoundException("Award not found with id: " + id));

        awardRepository.deleteById(id);
        awardKafkaProducer.deleteAward(awardMapper.toAvro(deletedAward));
    }
}
