package hcmus.zingmp3.award;

import hcmus.zingmp3.award.model.AwardAvro;
import org.springframework.stereotype.Service;

@Service
public interface AwardService {

    AwardRestResponse getAwardByName(String name);

    void createAward(AwardAvro award);

    void updateAward(AwardAvro award);

    void deleteAward(String id);
}
