package hcmus.zingmp3.content_delivery.model.mapper;

import hcmus.zingmp3.content_delivery.model.dto.ImageFileDataDTO;
import hcmus.zingmp3.content_delivery.model.entity.ImageFileData;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ImageFileDataMapper {
    private static final String IMAGE_PATH_FORMAT = "%s/images/%s%s";

    @Value("${server.gateway-url}")
    private String url;

    public ImageFileData toEntity(ImageFileDataDTO dto) {
        ImageFileData entity = new ImageFileData();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    public ImageFileDataDTO toDTO(ImageFileData entity) {
        ImageFileDataDTO dto = new ImageFileDataDTO();
        BeanUtils.copyProperties(entity, dto);
        dto.setUrl(formatImageUrl(entity.getPath(), entity.getName()));
        return dto;
    }

    public List<ImageFileDataDTO> toDTO(List<ImageFileData> entities) {
        return entities.stream().map(this::toDTO).toList();
    }

    private String formatImageUrl(String path, String name) {
        return String.format(IMAGE_PATH_FORMAT, url, path, name);
    }
}
