package hcmus.zingmp3.music_service.mapper;

import hcmus.zingmp3.music_service.dto.GenreDTO;
import hcmus.zingmp3.music_service.entity.GenreEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class GenreMapper {
    public Flux<GenreDTO> toDTO(Flux<GenreEntity> entity) {
        return entity.map(this::toDTO);
    }

    public Flux<GenreEntity> toEntity(Flux<GenreDTO> dto) {
        return dto.map(this::toEntity);
    }

    public Mono<GenreDTO> toDTO(Mono<GenreEntity> entity) {
        return entity.map(this::toDTO);
    }

    public Mono<GenreEntity> toEntity(Mono<GenreDTO> dto) {
        return dto.map(this::toEntity);
    }

    public GenreDTO toDTO(GenreEntity entity) {
        GenreDTO dto = new GenreDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public GenreEntity toEntity(GenreDTO dto) {
        GenreEntity entity = new GenreEntity();
        BeanUtils.copyProperties(dto, entity, "id");
        return entity;
    }
}
