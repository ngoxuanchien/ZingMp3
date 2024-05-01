package zingmp3.hcmus.playlistservice.mapper;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zingmp3.hcmus.playlistservice.dto.SongDTO;
import zingmp3.hcmus.playlistservice.entity.PlaylistSongEntity;

public class PlaylistSongMapper {
    public Flux<SongDTO> toDTO(Flux<PlaylistSongEntity> entity) {
        return entity.map(this::toDTO);
    }

    public Flux<PlaylistSongEntity> toEntity(Flux<SongDTO> dto) {
        return dto.map(this::toEntity);
    }

    public Mono<SongDTO> toDTO(Mono<PlaylistSongEntity> entity) {
        return entity.map(this::toDTO);
    }

    public Mono<PlaylistSongEntity> toEntity(Mono<SongDTO> dto) {
        return dto.map(this::toEntity);
    }

    public SongDTO toDTO(PlaylistSongEntity entity) {
        SongDTO dto = SongDTO.builder()
                .id(entity.getSongId())
                        .build();
        return dto;
    }

    public PlaylistSongEntity toEntity(SongDTO dto) {
        PlaylistSongEntity entity = PlaylistSongEntity.builder()
                .songId(dto.getId())
                .build();
        return entity;
    }
}
