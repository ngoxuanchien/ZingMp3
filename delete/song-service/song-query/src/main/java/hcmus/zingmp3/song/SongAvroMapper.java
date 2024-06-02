package hcmus.zingmp3.song;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class SongAvroMapper {
    public Song toEntity(SongAvro request) {
        return Song.builder()
                .id(toUUID(request.getId()))
                .title(request.getTitle().toString())
                .alias(request.getAlias().toString())
                .isOfficial(request.getIsOfficial())
                .isWorldWide(request.getIsWorldWide())
                .thumbnail(toUUID(request.getThumbnail()))
                .duration(request.getDuration())
                .isPrivate(request.getIsPrivate())
                .releaseDate(request.getReleaseDate())
                .distributor(request.getDistributor().toString())
                .hasLyric(request.getHasLyric())
                .likes(request.getLikes())
                .listen(request.getListen())
                .comment(request.getComment())
                .artists(toUUIDs(request.getArtists()))
                .genres(toUUIDs(request.getGenres()))
                .composers(toUUIDs(request.getComposers()))
                .audios(toUUIDs(request.getAudios()))
                .build();
    }

    public Set<UUID> toUUIDs(List<CharSequence> request) {
        return request.stream()
                .map(this::toUUID)
                .collect(Collectors.toSet());
    }

    public UUID toUUID(CharSequence request) {
        return UUID.fromString(request.toString());
    }
}
