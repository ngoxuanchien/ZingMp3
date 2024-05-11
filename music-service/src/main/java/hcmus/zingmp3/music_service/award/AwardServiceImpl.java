package hcmus.zingmp3.music_service.award;

import hcmus.zingmp3.music_service.award.model.Award;
import hcmus.zingmp3.music_service.award.model.AwardRequest;
import hcmus.zingmp3.music_service.award.model.AwardResponse;
import hcmus.zingmp3.music_service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AwardServiceImpl implements AwardService {

    private final AwardRepository awardRepository;
    private final AwardMapper awardMapper;


    @Override
    public AwardResponse getAwardById(UUID id) {
        Award award = awardRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Award not found with id: " + id));
        return awardMapper.toDTO(award);
    }

    @Override
    public List<AwardResponse> getAllAwards(Pageable pageable) {
        return awardMapper.toDTO(awardRepository.findAll(pageable).getContent());
    }

    @Override
    public AwardResponse createAward(AwardRequest request) {
        Award newAward = awardMapper.toEntity(request);
        return awardMapper.toDTO(awardRepository.save(newAward));
    }

    @Override
    public AwardResponse updateAward(UUID id, AwardRequest request) {
        awardRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Award not found with id: " + id));
        Award newAward = awardMapper.toEntity(request);
        newAward.setId(id);
        return awardMapper.toDTO(awardRepository.save(newAward));
    }

    @Override
    public void deleteAward(UUID id) {
        awardRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Award not found with id: " + id));
        awardRepository.deleteById(id);
    }

    @Override
    public Award getOrCreateByName(String name) {
        return awardRepository.findByName(name)
                .orElseGet(() -> awardRepository.save(Award.builder().name(name).build()));
    }
}
