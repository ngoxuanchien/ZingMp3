package hcmus.zingmp3.content_delivery.model.mapper;

import hcmus.zingmp3.content_delivery.model.dto.ImageFileDataDTO;
import hcmus.zingmp3.content_delivery.model.entity.ImageFileData;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ImageFileDataMapper {
    public ImageFileData toEntity(ImageFileDataDTO dto) {
        ImageFileData entity = new ImageFileData();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    public ImageFileDataDTO toDTO(ImageFileData entity) {
        ImageFileDataDTO dto = new ImageFileDataDTO();
        BeanUtils.copyProperties(entity, dto);
        dto.setUrl("http://localhost:8082/images/" + entity.getPath() + entity.getName());
        return dto;
    }

    public List<ImageFileDataDTO> toDTO(List<ImageFileData> entities) {
        return entities.stream().map(this::toDTO).toList();
    }
}
