package hcmus.zingmp3.playbackservice.mapper;

import hcmus.zingmp3.playbackservice.dto.SongImageDTO;
import hcmus.zingmp3.playbackservice.entity.SongImageEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class SongImageMapper {

    public Mono<SongImageEntity> toEntity(Mono<SongImageDTO> dto) {
        return dto.map(this::toEntity);
    }

    public Mono<SongImageDTO> toDTO(Mono<SongImageEntity> entity) {
        return entity.map(this::toDTO);
    }

    public SongImageDTO toDTO(SongImageEntity entity) {
        SongImageDTO dto = new SongImageDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public SongImageEntity toEntity(SongImageDTO dto) {
        SongImageEntity entity = new SongImageEntity();
        BeanUtils.copyProperties(dto, entity, "id");
        return entity;
    }
}
