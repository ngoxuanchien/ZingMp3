package hcmus.zingmp3.playbackservice.mapper;

import hcmus.zingmp3.playbackservice.dto.ArtistImageDTO;
import hcmus.zingmp3.playbackservice.entity.ArtistImageEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ArtistImageMapper {
    public ArtistImageDTO toDTO(ArtistImageEntity entity) {
        ArtistImageDTO dto = new ArtistImageDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public ArtistImageEntity toEntity(ArtistImageDTO dto) {
        ArtistImageEntity entity = new ArtistImageEntity();
        BeanUtils.copyProperties(dto, entity, "id");
        return entity;
    }
}
