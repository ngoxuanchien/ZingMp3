package hcmus.mp3.web.dto.mapper;

import hcmus.mp3.domain.model.Audio;
import hcmus.mp3.web.dto.AudioResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AudioMapper {
    @Value("${media.url}")
    private String url;

    public AudioResponseDto toDto(Audio entity) {
        return new AudioResponseDto(
                entity.getId(),
                entity.getName(),
                entity.getType(),
                entity.getPath(),
                entity.getSize(),
                entity.getBitrate(),
                url + "/api/play/" + entity.getId(),
                entity.getDuration()
        );
    }

    public List<AudioResponseDto> toDtos(List<Audio> audios) {
        return audios.stream().map(this::toDto).toList();
    }
}
