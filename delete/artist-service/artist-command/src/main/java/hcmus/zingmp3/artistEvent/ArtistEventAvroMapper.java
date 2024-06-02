package hcmus.zingmp3.artistEvent;

import hcmus.zingmp3.artist.ArtistEventAvro;
import hcmus.zingmp3.artist.ArtistAvroMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ArtistEventAvroMapper {
    private final ArtistAvroMapper artistAvroMapper;

    public ArtistEventAvro toAvro(ArtistEvent entity) {
        return ArtistEventAvro.newBuilder()
                .setEventType(entity.getEventType())
                .setArtist(artistAvroMapper.toAvro(entity.getArtist()))
                .build();
    }
}
