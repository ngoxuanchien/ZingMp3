package hcmus.zingmp3.award.mapper;

import hcmus.zingmp3.award.AwardGrpcRequest;
import hcmus.zingmp3.award.model.Award;

public class AwardGrpcMapper {

    public Award toEntity(AwardGrpcRequest dto) {
        return Award.builder()
                .name(dto.getName())
                .build();
    }
}
