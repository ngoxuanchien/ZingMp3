package hcmus.zingmp3.award.mapper;

import hcmus.zingmp3.award.AwardAvro;
import hcmus.zingmp3.award.model.Award;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class AwardAvroMapper {
    public Award toEntity(AwardAvro dto) {
        if (dto == null) {
            return null;
        }

        return Award.builder()
                .id(toUUID(dto.getId()))
                .name(dto.getName().toString())
                .artists(toUUIDs(dto.getArtists()))
                .build();
    }

    public UUID toUUID(CharSequence id) {
        if (id == null) {
            return null;
        }

        return UUID.fromString(id.toString());
    }

    private Set<UUID> toUUIDs(List<CharSequence> ids) {
        if (ids == null) {
            return null;
        }

        return ids
                .stream()
                .map(this::toUUID)
                .collect(Collectors.toSet());
    }
}
