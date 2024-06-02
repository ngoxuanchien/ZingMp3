package hcmus.zingmp3.artist;

import hcmus.zingmp3.artist.dto.ArtistRestResponse;
import org.springframework.stereotype.Component;

@Component
public class ArtistProtoMapper {
    public ArtistGrpcResponse toProto(ArtistRestResponse result) {
        return ArtistGrpcResponse.newBuilder()
                .setId(result.getId().toString())
                .setName(result.getName())
                .setAlias(result.getAlias())
                .setThumbnail(result.getThumbnail())
                .setTotalFollow(result.getTotalFollow())
                .setSortBiography(result.getSortBiography())
                .setBiography(result.getBiography())
                .setNational(result.getNational())
                .setRealName(result.getRealName())
                .setBirthday(result.getBirthday())
                .setPlaylistId(result.getPlaylistId().toString())
                .build();
    }
}
