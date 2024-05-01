package hcmus.zingmp3.playbackservice.mapper;

import hcmus.zingmp3.playbackservice.dto.PlaylistImageDTO;
import hcmus.zingmp3.playbackservice.entity.PlaylistImageEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class PlaylistImageMapper {
    public PlaylistImageDTO toDTO(PlaylistImageEntity entity) {
        PlaylistImageDTO dto = new PlaylistImageDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public PlaylistImageEntity toEntity(PlaylistImageDTO dto) {
        PlaylistImageEntity entity = new PlaylistImageEntity();
        BeanUtils.copyProperties(dto, entity, "id");
        return entity;
    }
}
