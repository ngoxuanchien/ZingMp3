package hcmus.zingmp3.genre;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class GenreAvroMapper {
    public UUID toUUID(CharSequence id) {
        if (id == null) {
            return null;
        }

        return UUID.fromString(id.toString());
    }

    public Genre toEntity(GenreAvro genre) {
        if (genre == null) {
            return null;
        }

        var builder = Genre.builder()
                .id(toUUID(genre.getId()))
                .createdAt(genre.getCreatedAt())
                .modifiedAt(genre.getModifiedAt())
                .songs(toUUIDs(genre.getSongs()));

        Optional.ofNullable(genre.getName())
                .map(CharSequence::toString)
                .ifPresent(builder::name);

        Optional.ofNullable(genre.getTitle())
                .map(CharSequence::toString)
                .ifPresent(builder::title);

        Optional.ofNullable(genre.getAlias())
                .map(CharSequence::toString)
                .ifPresent(builder::alias);

        Optional.ofNullable(genre.getCreatedBy())
                .map(this::toUUID)
                .ifPresent(builder::createdBy);

        Optional.ofNullable(genre.getModifiedBy())
                .map(this::toUUID)
                .ifPresent(builder::modifiedBy);

        return builder.build();
    }

    public Set<UUID> toUUIDs(List<CharSequence> request) {
        if (request == null) {
            return new HashSet<>();
        }

        return request.stream()
                .map(this::toUUID)
                .collect(Collectors.toSet());
    }
}
