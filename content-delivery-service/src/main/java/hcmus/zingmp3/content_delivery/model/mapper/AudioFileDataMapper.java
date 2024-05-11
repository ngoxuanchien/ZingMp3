package hcmus.zingmp3.content_delivery.model.mapper;

import hcmus.zingmp3.content_delivery.model.dto.AudioFileDataDTO;
import hcmus.zingmp3.content_delivery.model.entity.AudioFileData;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AudioFileDataMapper {
    public AudioFileData toEntity(AudioFileDataDTO dto) {
        AudioFileData entity = new AudioFileData();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    public AudioFileDataDTO toDTO(AudioFileData entity) {
        AudioFileDataDTO dto = new AudioFileDataDTO();
        BeanUtils.copyProperties(entity, dto);
        dto.setUrl("http://localhost:8082/api/content-delivery/play/" + entity.getId());
        return dto;
    }

    public List<AudioFileDataDTO> toDTO(List<AudioFileData> entities) {
        return entities.stream().map(this::toDTO).toList();
    }
}
