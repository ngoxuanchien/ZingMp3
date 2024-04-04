package zingmp3.hcmus.playlistservice.dto.song;

import lombok.Builder;
import lombok.Data;
import zingmp3.hcmus.playlistservice.dto.artist.ArtistDTO;
import zingmp3.hcmus.playlistservice.dto.album.AlbumDTO;

@Data
@Builder
public class SongDTO {
    private long id;
    private String name;
    private String thumbnail;
    private ArtistDTO artist;
    private AlbumDTO album;
}
