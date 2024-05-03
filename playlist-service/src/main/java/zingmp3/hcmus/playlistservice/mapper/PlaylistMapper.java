package zingmp3.hcmus.playlistservice.mapper;

import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zingmp3.hcmus.playlistservice.dto.PlaylistDTO;
import zingmp3.hcmus.playlistservice.entity.PlaylistEntity;

@Component
public class PlaylistMapper {
    @Value("${playback.image.playlist}")
    private String imageUrl;

    public Flux<PlaylistDTO> toDTO(Flux<PlaylistEntity> entity) {
        return entity.map(this::toDTO);
    }

    public Flux<PlaylistEntity> toEntity(Flux<PlaylistDTO> dto) {
        return dto.map(this::toEntity);
    }

    public Mono<PlaylistDTO> toDTO(Mono<PlaylistEntity> entity) {
        return entity.map(this::toDTO);
    }

    public Mono<PlaylistEntity> toEntity(Mono<PlaylistDTO> dto) {
        return dto.map(this::toEntity);
    }

    public PlaylistDTO toDTO(PlaylistEntity entity) {
        PlaylistDTO dto = new PlaylistDTO();
        BeanUtils.copyProperties(entity, dto);
        dto.setThumbnail(imageUrl + dto.getThumbnail());
        return dto;

    }

    public PlaylistEntity toEntity(PlaylistDTO dto) {
        PlaylistEntity entity = new PlaylistEntity();
        BeanUtils.copyProperties(dto, entity, "id");
        return entity;
    }
}
