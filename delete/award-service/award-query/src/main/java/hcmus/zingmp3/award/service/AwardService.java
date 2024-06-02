package hcmus.zingmp3.award.service;

import hcmus.zingmp3.award.AwardAvro;
import hcmus.zingmp3.award.AwardGrpcRequest;
import hcmus.zingmp3.award.dto.AwardRestResponse;
import hcmus.zingmp3.award.model.Award;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface AwardService {

    AwardRestResponse getAwardByName(String name);

    void createAward(Award award);

    void updateAward(Award award);

    void deleteAward(UUID id);

    List<AwardRestResponse> getAllAwards(Pageable pageable);
    List<AwardRestResponse> getAllAwards();

    List<AwardRestResponse> getAwardLog();

    AwardRestResponse getAwardById(String id);
    Award getAwardById(AwardGrpcRequest awardId);
}
