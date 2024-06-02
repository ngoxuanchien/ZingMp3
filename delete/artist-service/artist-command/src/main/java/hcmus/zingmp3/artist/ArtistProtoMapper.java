package hcmus.zingmp3.artist;

import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Component
public class ArtistProtoMapper {
    public ArtistGrpcResponse toProto(Artist artist) {
        if (artist == null) {
            return null;
        }

        ArtistGrpcResponse.Builder builder = ArtistGrpcResponse.newBuilder()
                .setId(artist.getId().toString())
                .setAlias(artist.getAlias())
                .setName(artist.getName())
                .setTotalFollow(artist.getTotalFollow())
                .setSortBiography(artist.getSortBiography())
                .setBiography(artist.getBiography())
                .setNational(artist.getNational())
                .setRealName(artist.getRealName())
                .addAllAwards(toProto(artist.getAwards()))
                .addAllSongs(toProto(artist.getSongs()))
                .addAllComposedSongs(toProto(artist.getComposedSongs()));

        Optional.ofNullable(artist.getPlaylistId())
                .map(UUID::toString)
                .ifPresent(builder::setPlaylistId);

        Optional.ofNullable(artist.getThumbnail())
                .map(UUID::toString)
                .ifPresent(builder::setThumbnail);

        return builder.build();
    }

    private Iterable<String> toProto(Set<UUID> uuidList) {
        return uuidList
                .stream()
                .map(UUID::toString)
                .toList();
    }
}
