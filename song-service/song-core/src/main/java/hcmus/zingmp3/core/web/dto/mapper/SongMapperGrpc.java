package hcmus.zingmp3.core.web.dto.mapper;

import hcmus.zingmp3.common.domain.model.Song;
import hcmus.zingmp3.song.SongResponseGrpc;
import hcmus.zingmp3.song.SongStatusGrpc;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class SongMapperGrpc {
    public SongResponseGrpc toDto(Song entity) {
        return SongResponseGrpc.newBuilder()
                .setId(entity.getId().toString())
                .setAlias(entity.getAlias())
                .setTitle(entity.getTitle())
                .setThumbnailId(entity.getThumbnailId().toString())
                .addAllArtistIds(entity.getArtistIds().stream().map(UUID::toString).collect(Collectors.toList()))
                .addAllGenreIds(entity.getGenreIds().stream().map(UUID::toString).collect(Collectors.toList()))
                .addAllComposerIds(entity.getComposerIds().stream().map(UUID::toString).collect(Collectors.toList()))
                .setStatus(SongStatusGrpc.valueOf(entity.getStatus().name()))
                .setReleaseDate(entity.getReleaseDate())
                .setListen(entity.getListen())
                .setLiked(entity.getLiked())
                .setLyric(entity.getLyric())
                .addAllMediaIds(entity.getMediaIds().stream().map(UUID::toString).collect(Collectors.toList()))
                .setCreatedBy(entity.getCreatedBy().toString())
                .setCreatedDate(String.valueOf(entity.getCreatedDate()))
                .setLastModifiedBy(entity.getLastModifiedBy().toString())
                .setLastModifiedDate(String.valueOf(entity.getLastModifiedDate()))
                .build();
    }
}
