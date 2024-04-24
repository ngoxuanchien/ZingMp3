package zingmp3.hcmus.playlistservice.converter;

import org.springframework.stereotype.Component;
import zingmp3.hcmus.playlistservice.dto.playlist.PlaylistDTO;
import zingmp3.hcmus.playlistservice.entity.Playlist;

@Component
public class PlaylistConverter implements Converter<Playlist, PlaylistDTO> {
    @Override
    public PlaylistDTO convert(Playlist source) {
        return PlaylistDTO.builder()
                .id(source.getId())
                .name(source.getName())
                .isPublic(source.isPublic())
                .isRandomPlaying(source.isRandomPlaying())
                .modifiedDate(source.getModifiedDate())
                .createdBy(source.getCreatedBy())
                .createdDate(source.getCreatedDate())
                .build();
    }

    @Override
    public Playlist reverseConvert(PlaylistDTO target) {
        return Playlist.builder()
                .name(target.getName())
                .isPublic(target.isPublic())
                .isRandomPlaying(target.isRandomPlaying())
                .build();
    }
}
