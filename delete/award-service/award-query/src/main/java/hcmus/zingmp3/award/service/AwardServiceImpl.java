package hcmus.zingmp3.award.service;

import com.ctc.wstx.shaded.msv_core.datatype.xsd.UnicodeUtil;
import hcmus.zingmp3.award.AwardAvro;
import hcmus.zingmp3.award.AwardGrpcRequest;
import hcmus.zingmp3.award.exeception.AwardNotFoundException;
import hcmus.zingmp3.award.repository.AwardRepository;
import hcmus.zingmp3.award.mapper.AwardMapper;
import hcmus.zingmp3.award.model.Award;
import hcmus.zingmp3.award.dto.AwardRestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
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
    public void createAward(Award award) {
        awardRepository.save(award);
    }

    @Override
    public void updateAward(Award award) {
        awardRepository.save(award);
    }

    @Override
    public void deleteAward(UUID id) {
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

    @Override
    public AwardRestResponse getAwardById(String id) {
        Award award = awardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Award not found with id: " + id));
        return awardMapper.toDto(award);
    }

    @Override
    public Award getAwardById(AwardGrpcRequest request) {
        return awardRepository.findById(request.getId())
                .orElseThrow(() -> new AwardNotFoundException(
                        String.format("No Award found with the provided Id: %s", request.getId())
                ));
    }
}

