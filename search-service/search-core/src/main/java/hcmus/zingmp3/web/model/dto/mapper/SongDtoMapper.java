package hcmus.zingmp3.web.model.dto.mapper;

import hcmus.zingmp3.domain.model.Song;
import hcmus.zingmp3.web.model.dto.SongDto;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SongDtoMapper {

    public SongDto toDto(Song entity) {
        return new SongDto(
                entity.getId(),
                entity.getAlias(),
                entity.getTitle(),
                entity.getThumbnailId(),
                entity.getReleaseDate(),
                entity.getListen(),
                entity.getLiked(),
                entity.getLyric(),
                entity.getDuration(),
                entity.getAlbum().getId()
        );
    }

    public Set<SongDto> toDto(Set<Song> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toSet());
    }
}
