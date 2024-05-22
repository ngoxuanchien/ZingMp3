package hcmus.zingmp3.music_service.award;

import hcmus.zingmp3.music_service.award.model.Award;
import hcmus.zingmp3.music_service.award.model.AwardRequest;
import hcmus.zingmp3.music_service.award.model.AwardResponse;

import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

public class AwardDataForTest {
    public static final List<UUID> AWARD_IDS = IntStream.rangeClosed(1, 10)
            .mapToObj(i -> UUID.randomUUID())
            .toList();

    public static final List<AwardRequest> AWARD_REQUESTS = IntStream.rangeClosed(1, 10)
            .mapToObj(i -> AwardRequest.builder()
                    .name("Award " + i)
                    .build())
            .toList();

    public static final List<Award> BEFORE_SAVE_AWARDS = IntStream.rangeClosed(1, 10)
            .mapToObj(i -> Award.builder()
                    .name("Award " + i)
                    .build())
            .toList();

    public static final List<Award> AFTER_SAVE_AWARDS = IntStream.rangeClosed(1, 10)
            .mapToObj(i -> Award.builder()
                    .id(AWARD_IDS.get(i - 1))
                    .name("Award " + i)
                    .build())
            .toList();

    public static final List<AwardResponse> AWARD_RESPONSES = IntStream.rangeClosed(1, 10)
            .mapToObj(i -> AwardResponse.builder()
                    .id(AWARD_IDS.get(i - 1))
                    .name("Award " + i)
                    .build())
            .toList();
}
