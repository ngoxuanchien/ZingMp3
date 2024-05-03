package hcmus.zingmp3.music_service.mapper;

import hcmus.zingmp3.music_service.dto.SongDTO;
import hcmus.zingmp3.music_service.entity.SongEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class SongMapper {
    @Value("${playback.image.song}")
    private String imageUrl;

    public Flux<SongDTO> toDTO(Flux<SongEntity> entity) {
        return entity.map(this::toDTO);
    }

    public Flux<SongEntity> toEntity(Flux<SongDTO> dto) {
        return dto.map(this::toEntity);
    }

    public Mono<SongDTO> toDTO(Mono<SongEntity> entity) {
        return entity.map(this::toDTO);
    }

    public Mono<SongEntity> toEntity(Mono<SongDTO> dto) {
        return dto.map(this::toEntity);
    }

    public SongDTO toDTO(SongEntity entity) {
        SongDTO dto = new SongDTO();
        BeanUtils.copyProperties(entity, dto);
        dto.setThumbnail(imageUrl + dto.getThumbnail());
        return dto;
    }

    public SongEntity toEntity(SongDTO dto) {
        SongEntity entity = new SongEntity();
        BeanUtils.copyProperties(dto, entity, "id");
        return entity;
    }
}
