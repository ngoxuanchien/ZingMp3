package hcmus.zingmp3.audio;

import hcmus.zingmp3.audio.model.Audio;
import hcmus.zingmp3.audio.model.AudioRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AudioMapper {
    public Audio toEntity(AudioRequest dto) {
        Audio entity = new Audio();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    public Set<Audio> toEntity(Set<AudioRequest> dtos) {
        if (dtos == null) {
            return new HashSet<>();
        }

        return dtos.stream().map(this::toEntity).collect(Collectors.toSet());
    }
}
