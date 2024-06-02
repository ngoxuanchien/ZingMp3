package hcmus.zingmp3.artist;

import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class ArtistGrpcMapper {
    public ArtistRestResponse toDto(ArtistGrpcResponse response) {
        var builder =  ArtistRestResponse.builder()
                .id(UUID.fromString(response.getId()))
                .name(response.getName())
                .alias(response.getAlias())
                .thumbnail(response.getThumbnail())
                .totalFollow(response.getTotalFollow())
                .sortBiography(response.getSortBiography())
                .biography(response.getBiography())
                .national(response.getNational())
                .realName(response.getRealName())
                .birthday(response.getBirthday());

        Optional.of(response.getPlaylistId())
                .map(UUID::fromString)
                .ifPresent(builder::playlistId);

        return builder.build();
    }
}
