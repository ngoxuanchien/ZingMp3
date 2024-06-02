package hcmus.zingmp3.artist.mapper;

import hcmus.zingmp3.artist.ArtistAvro;
import hcmus.zingmp3.artist.dto.ArtistRestResponse;
import hcmus.zingmp3.artist.model.Artist;
import hcmus.zingmp3.artist.service.AwardGrpcClient;
import hcmus.zingmp3.award.AwardGrpcMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
@RequiredArgsConstructor
public class ArtistMapper {

    private final AwardGrpcMapper mapper;

    private static final UUID DEFAULT_UUID = UUID.fromString("00000000-0000-0000-0000-000000000000");

    private UUID toUUID(CharSequence charSequence) {
        return Optional.ofNullable(charSequence)
                .map(CharSequence::toString)
                .map(UUID::fromString)
                .orElse(DEFAULT_UUID);
    }

    private String toString(CharSequence charSequence) {
        return Optional.ofNullable(charSequence)
                .map(CharSequence::toString)
                .orElse("");
    }

    private String toString(UUID uuid) {
        return Optional.ofNullable(uuid)
                .map(UUID::toString)
                .orElse(DEFAULT_UUID.toString());
    }

    public Artist toEntity(ArtistAvro dto) {
        return Artist.builder()
                .id(toUUID(dto.getId()))
                .alias(toString(dto.getAlias()))
                .name(toString(dto.getName()))
                .playlistId(toUUID(dto.getPlaylistId()))
                .thumbnail(toUUID(dto.getThumbnail()))
                .totalFollow(dto.getTotalFollow())
                .sortBiography(toString(dto.getSortBiography()))
                .biography(toString(dto.getBiography()))
                .national(toString(dto.getNational()))
                .realName(toString(dto.getRealName()))
                .birthday(LocalDate.parse(toString(dto.getBirthday())))
                .awards(toUUIDs(dto.getAwards()))
                .songs(toUUIDs(dto.getSongs()))
                .composedSongs(toUUIDs(dto.getComposedSongs()))
                .build();
    }

    private Set<UUID> toUUIDs(List<CharSequence> charSequences) {
        return charSequences.stream()
                .map(this::toUUID)
                .collect(Collectors.toSet());
    }

    public List<ArtistRestResponse> toDTOs(List<Artist> entities) {
        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

    }

    public ArtistRestResponse toDTO(Artist entity) {
        return ArtistRestResponse.builder()
                .id(entity.getId())
                .alias(entity.getAlias())
                .name(entity.getName())
                .playlistId(entity.getPlaylistId())
                .thumbnail(toString(entity.getThumbnail()))
                .totalFollow(entity.getTotalFollow())
                .sortBiography(entity.getSortBiography())
                .biography(entity.getBiography())
                .national(entity.getNational())
                .realName(entity.getRealName())
                .birthday(entity.getBirthday().toString())
                .awards(mapper.toDTO(entity.getAwards()))
                .songs(entity.getSongs())
                .composedSongs(entity.getComposedSongs())
                .build();
    }

    public List<ArtistRestResponse> toDTOs(Iterable<Artist> dtos) {
        return StreamSupport.stream(dtos.spliterator(), true)
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
