package hcmus.zingmp3.award;

import hcmus.zingmp3.award.model.AwardRequest;
import hcmus.zingmp3.award.model.AwardResponse;
import hcmus.zingmp3.award.model.Award;
import hcmus.zingmp3.award.model.AwardRequest;
import hcmus.zingmp3.award.model.AwardResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AwardMapper {

    public AwardResponse toDTO(Award entity) {
        AwardResponse dto = new AwardResponse();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public Award toEntity(AwardRequest dto) {
        Award entity = new Award();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    public List<AwardResponse> toDTO(List<Award> entities) {
        return entities.stream().map(this::toDTO).toList();
    }
}
