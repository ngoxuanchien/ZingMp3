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
    public AwardAvro toAvro(Award entity) {
        if (entity == null) {
            return null;
        }

        return AwardAvro.newBuilder()
                .setId(entity.getId().toString())
                .setName(entity.getName())
                .setArtists(toCharSequenceList(entity.getArtistIds()))
                .build();
    }

    private List<CharSequence> toCharSequenceList(Set<UUID> uuids) {
        if (uuids == null) {
            return null;
        }

        return uuids.stream()
                .map(UUID::toString)
                .collect(Collectors.toList());
    }
}
