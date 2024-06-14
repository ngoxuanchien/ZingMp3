package hcmus.zingmp3.web.model.dto.mapper;

import hcmus.zingmp3.web.model.dto.MediaDto;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class MediaDtoMapper {

    public MediaDto toDto(Media entity) {
        return new MediaDto(entity.getQuality(), entity.getUrl());
    }

    public Set<MediaDto> toDto(Set<Media> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toSet());
    }
}
