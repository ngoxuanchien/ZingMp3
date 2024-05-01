package hcmus.zingmp3.music_service.mapper;

import hcmus.zingmp3.music_service.dto.StreamingDTO;
import hcmus.zingmp3.music_service.entity.StreamingEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class StreamingMapper {
    public Flux<StreamingDTO> toDTO(Flux<StreamingEntity> entity) {
        return entity.map(this::toDTO);
    }

    public Flux<StreamingEntity> toEntity(Flux<StreamingDTO> dto) {
        return dto.map(this::toEntity);
    }

    public Mono<StreamingDTO> toDTO(Mono<StreamingEntity> entity) {
        return entity.map(this::toDTO);
    }

    public Mono<StreamingEntity> toEntity(Mono<StreamingDTO> dto) {
        return dto.map(this::toEntity);
    }

    public StreamingDTO toDTO(StreamingEntity entity) {
        StreamingDTO dto = new StreamingDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public StreamingEntity toEntity(StreamingDTO dto) {
        StreamingEntity entity = new StreamingEntity();
        BeanUtils.copyProperties(dto, entity, "id");
        return entity;
    }
}
