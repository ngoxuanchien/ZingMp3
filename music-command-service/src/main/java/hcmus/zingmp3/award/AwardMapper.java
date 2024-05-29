package hcmus.zingmp3.award;

import hcmus.zingmp3.award.model.Award;
import hcmus.zingmp3.award.model.AwardAvro;
import hcmus.zingmp3.award.model.AwardRestRequest;
import hcmus.zingmp3.award.model.AwardRestResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AwardMapper {

    public Award toEntity(AwardRestRequest dto) {
        var entity = new Award();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    public AwardRestResponse toDTO(Award entity) {
        var dto = new AwardRestResponse();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public AwardAvro toAvro(Award entity) {
        var avro = new AwardAvro();
        avro.setId(entity.getId().toString());
        avro.setName(entity.getName());
        System.out.println(avro);
        return avro;
    }
}
