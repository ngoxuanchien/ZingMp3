package hcmus.zingmp3.music_service.mapper;

import hcmus.zingmp3.music_service.dto.ArtistDTO;
import hcmus.zingmp3.music_service.entity.ArtistEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ArtistMapper {
    @Value("${playback.image.artist}")
    private String imageUrl;

    public Flux<ArtistDTO> toDTO(Flux<ArtistEntity> entities) {
        return entities.map(this::toDTO);
    }

    public Flux<ArtistEntity> toEntity(Flux<ArtistDTO> dtos) {
        return dtos.map(this::toEntity);
    }

    public Mono<ArtistDTO> toDTO(Mono<ArtistEntity> entity) {
        return entity.map(this::toDTO);
    }

    public Mono<ArtistEntity> toEntity(Mono<ArtistDTO> dto) {
        return dto.map(this::toEntity);
    }

    public ArtistDTO toDTO(ArtistEntity entity) {
        ArtistDTO dto = new ArtistDTO();
        BeanUtils.copyProperties(entity, dto);
        dto.setThumbnail(imageUrl + dto.getThumbnail());
        return dto;
    }

    public ArtistEntity toEntity(ArtistDTO dto) {
        ArtistEntity entity = new ArtistEntity();
        BeanUtils.copyProperties(dto, entity, "id");
        return entity;
    }
}
