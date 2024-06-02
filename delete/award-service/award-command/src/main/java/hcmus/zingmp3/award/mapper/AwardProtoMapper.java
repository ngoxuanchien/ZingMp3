package hcmus.zingmp3.award.mapper;

import hcmus.zingmp3.award.AwardGrpcRequest;
import hcmus.zingmp3.award.AwardGrpcResponse;
import hcmus.zingmp3.award.model.Award;
import org.springframework.stereotype.Component;

@Component
public class AwardProtoMapper {

    public Award toEntity(AwardGrpcRequest dto) {
        if (dto == null) {
            return null;
        }

        return Award.builder()
                .name(dto.getName())
                .build();
    }

    public AwardGrpcResponse toProto(Award award) {
        if (award == null) {
            return null;
        }

        return AwardGrpcResponse.newBuilder()
                .setId(award.getId().toString())
                .setName(award.getName())
                .build();
    }
}
