package hcmus.zingmp3.award.mapper;

import hcmus.zingmp3.award.AwardGrpcResponse;
import hcmus.zingmp3.award.model.Award;
import org.springframework.stereotype.Component;

@Component
public class AwardGrpcMapper {
    public AwardGrpcResponse toGrpc(Award entity) {
        return AwardGrpcResponse.newBuilder()
                .setId(entity.getId().toString())
                .setName(entity.getName())
                .build();
    }
}
