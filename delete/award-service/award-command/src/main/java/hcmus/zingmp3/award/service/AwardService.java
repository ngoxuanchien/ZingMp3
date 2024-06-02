package hcmus.zingmp3.award.service;

import hcmus.zingmp3.award.AwardGrpcRequest;
import hcmus.zingmp3.award.AwardGrpcResponse;
import hcmus.zingmp3.award.dto.AwardRestRequest;
import hcmus.zingmp3.award.dto.AwardRestResponse;
import hcmus.zingmp3.award.model.Award;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public interface AwardService {
    AwardRestResponse createAward(AwardRestRequest request);
    AwardRestResponse updateAward(UUID id, AwardRestRequest request);
    void deleteAward(UUID id);
    Award getOrCreateAward(Award request);
    void updateAward(UUID artistId, Set<UUID> awardIds);
    void removeArtist(UUID artistId, Set<UUID> awardIds);
}
