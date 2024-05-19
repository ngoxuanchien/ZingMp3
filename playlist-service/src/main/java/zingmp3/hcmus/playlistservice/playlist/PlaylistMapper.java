package zingmp3.hcmus.playlistservice.playlist;

import com.google.protobuf.Descriptors;
import hcmus.zingmp3.*;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import zingmp3.hcmus.playlistservice.playlist.model.Playlist;
import zingmp3.hcmus.playlistservice.playlist.model.PlaylistRequest;
import zingmp3.hcmus.playlistservice.playlist.model.PlaylistResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.UUID.fromString;

@Component
@RequiredArgsConstructor
public class PlaylistMapper {

    @GrpcClient("music")
    SongServiceGrpc.SongServiceBlockingStub songService;

    @GrpcClient("music")
    GenreServiceGrpc.GenreServiceBlockingStub genreService;

    @GrpcClient("music")
    ArtistServiceGrpc.ArtistServiceBlockingStub artistService;

    public PlaylistResponse toDTO(Playlist entity) {
        PlaylistResponse dto = new PlaylistResponse();
        BeanUtils.copyProperties(entity, dto);

        dto.setSongs(entity.getSongs().stream()
                .map(this::getSong)
                .collect(Collectors.toSet()));
        return dto;
    }

    private Map<String, String> getSong(UUID id) {
        SongResponse response = songService.getSongById(SongId.newBuilder().setId(id.toString()).build());
        Map<String, String> song = new HashMap<>();
        for (Descriptors.FieldDescriptor field : response.getDescriptorForType().getFields()) {
            if (field.isRepeated()) {
                song.put(field.getName(), response.getField(field).toString());
            } else if (response.hasField(field)) {
                song.put(field.getName(), response.getField(field).toString());
            } else {
                song.put(field.getName(), field.getDefaultValue().toString());
            }
        }
        return song;
    }

    public Playlist toEntity(PlaylistRequest dto) {
        Playlist entity = new Playlist();
        BeanUtils.copyProperties(dto, entity);

//        entity.setGenres(getGenres(dto.getGenres()));
        entity.setSongs(getSongs(dto.getSongs()));
//        entity.setArtists(getArtists(dto.getArtists()));

        return entity;
    }

    private Set<UUID> getArtists(Set<String> artists) {
        return artists.stream()
                .map(artistAlias -> fromString(artistService
                          .getArtistByAlias(
                                  ArtistRequest
                                        .newBuilder()
                                        .setAlias(artistAlias)
                                        .build())
                          .getId()))
                .collect(Collectors.toSet());
    }

    private Set<UUID> getGenres(Set<String> genres) {
        return genres.stream()
                .map(genreAlias -> fromString(genreService
                         .getGenreByAlias(
                                 GenreRequest
                                         .newBuilder()
                                         .setAlias(genreAlias)
                                         .build())
                         .getId()))
                .collect(Collectors.toSet());
    }

    private Set<UUID> getSongs(Set<String> songs) {
        return songs.stream()
                .map(songAlias -> fromString(songService
                        .getSongIdByAlias(
                                SongAlias
                                        .newBuilder()
                                        .setAlias(songAlias)
                                        .build())
                        .getId()))
                .collect(Collectors.toSet());
    }
}
