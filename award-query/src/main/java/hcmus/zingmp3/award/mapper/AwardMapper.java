package hcmus.zingmp3.award;

import hcmus.zingmp3.award.model.Award;
import hcmus.zingmp3.award.model.AwardAvro;
import hcmus.zingmp3.award.model.AwardRestResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AwardMapper {
    public Award toEntity(AwardAvro dto) {
        var entity = new Award();
        entity.setId(dto.getId().toString());
        entity.setName(dto.getName().toString());
        return entity;
    }

    public AwardRestResponse toDto(Award entity) {
        var dto = new AwardRestResponse();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    public List<AwardRestResponse> toDto(List<Award> awards) {
        return awards
                .stream()
                .map(this::toDto)
                .toList();
    }
}
