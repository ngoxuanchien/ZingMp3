package hcmus.zingmp3.award;

import hcmus.zingmp3.award.model.AwardRequest;
import hcmus.zingmp3.award.model.AwardResponse;
import hcmus.zingmp3.award.model.Award;
import hcmus.zingmp3.award.model.AwardRequest;
import hcmus.zingmp3.award.model.AwardResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface AwardService {
    AwardResponse getAwardById(UUID id);
    List<AwardResponse> getAllAwards(Pageable pageable);
    AwardResponse createAward(AwardRequest request);
    AwardResponse updateAward(UUID id, AwardRequest request);
    void deleteAward(UUID id);
    Award getOrCreateByName(String name);
}
