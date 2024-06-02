package hcmus.zingmp3.award;

import hcmus.zingmp3.award.model.AwardAvro;
import hcmus.zingmp3.award.model.AwardRestResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AwardService {

    AwardRestResponse getAwardByName(String name);

    void createAward(AwardAvro award);

    void updateAward(AwardAvro award);

    void deleteAward(String id);

    List<AwardRestResponse> getAllAwards(Pageable pageable);
    List<AwardRestResponse> getAllAwards();

    List<AwardRestResponse> getAwardLog();
}
