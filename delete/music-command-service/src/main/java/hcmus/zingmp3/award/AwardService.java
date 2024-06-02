package hcmus.zingmp3.award;

import hcmus.zingmp3.award.model.AwardRestRequest;
import hcmus.zingmp3.award.model.AwardRestResponse;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface AwardService {
    AwardRestResponse createAward(AwardRestRequest request);
    AwardRestResponse updateAward(UUID id, AwardRestRequest request);
    void deleteAward(UUID id);
}
