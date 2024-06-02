package hcmus.zingmp3.artist;

import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ArtistAvroMapper {
    public ArtistAvro toAvro(Artist entity) {
        var dto = new ArtistAvro();
        dto.setId(entity.getId().toString());
        dto.setName(entity.getName());
        dto.setAlias(entity.getAlias());
//        dto.setPlaylistId(entity.getPlaylistId().toString());
        dto.setThumbnail(entity.getThumbnail().toString());
        dto.setTotalFollow(entity.getTotalFollow());
        dto.setSortBiography(entity.getSortBiography());
        dto.setBiography(entity.getBiography());
        dto.setNational(entity.getNational());
        dto.setRealName(entity.getRealName());
        dto.setBirthday(entity.getBirthday().toString());
        dto.setAwards(
                entity.getAwards()
                        .stream()
                        .map(UUID::toString)
                        .collect(Collectors.toList()));
        dto.setSongs(
                entity.getSongs()
                        .stream()
                        .map(UUID::toString)
                        .collect(Collectors.toList()));
        dto.setComposedSongs(
                entity.getComposedSongs()
                        .stream()
                        .map(UUID::toString)
                        .collect(Collectors.toList()));
        return dto;
    }
}
