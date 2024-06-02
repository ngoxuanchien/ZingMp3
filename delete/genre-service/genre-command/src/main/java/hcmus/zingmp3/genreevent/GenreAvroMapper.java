package hcmus.zingmp3.genreevent;

import hcmus.zingmp3.genre.Genre;
import hcmus.zingmp3.genre.GenreAvro;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Component
public class GenreAvroMapper {
    public GenreAvro toAvro(Genre genre) {
        var builder = GenreAvro.newBuilder()
                .setId(genre.getId().toString())
                .setName(genre.getName())
                .setTitle(genre.getTitle())
                .setAlias(genre.getAlias())
                .setSongs(toCharSequences(genre.getSongs()))
                .setCreatedAt(toLong(genre.getCreatedAt()))
                .setModifiedAt(toLong(genre.getModifiedAt()));

        Optional.ofNullable(genre.getCreatedBy())
                .map(UUID::toString)
                .ifPresent(builder::setCreatedBy);

        Optional.ofNullable(genre.getModifiedBy())
                .map(UUID::toString)
                .ifPresent(builder::setModifiedBy);

        return builder.build();
    }

    private long toLong(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return 0;
        }

        return localDateTime.toInstant(ZoneOffset.UTC).toEpochMilli();
    }

    private CharSequence toCharSequence(UUID id) {
        if (id == null) {
            return null;
        }

        return id.toString();
    }

    private List<CharSequence> toCharSequences(Set<UUID> ids) {
        if (ids == null) {
            return null;
        }
        return ids.stream()
                .map(this::toCharSequence)
                .toList();
    }
}
