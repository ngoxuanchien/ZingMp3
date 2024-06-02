package hcmus.zingmp3.award.mapper;

import hcmus.zingmp3.award.AwardAvro;
import hcmus.zingmp3.award.AwardGrpcResponse;
import hcmus.zingmp3.award.model.Award;
import hcmus.zingmp3.award.dto.AwardRestResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class AwardMapper {


    public AwardRestResponse toDto(Award entity) {
        if (entity == null) {
            return null;
        }

        return AwardRestResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .artists(entity.getArtists())
                .build();
    }

    public List<AwardRestResponse> toDto(List<Award> awards) {
        return awards
                .stream()
                .map(this::toDto)
                .toList();
    }


}
