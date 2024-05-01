package hcmus.zingmp3.music_service.service.impl;

import hcmus.zingmp3.music_service.dto.ArtistDTO;
import hcmus.zingmp3.music_service.dto.ArtistImageDTO;
import hcmus.zingmp3.music_service.entity.SongArtistEntity;
import hcmus.zingmp3.music_service.entity.SongComposerEntity;
import hcmus.zingmp3.music_service.mapper.ArtistMapper;
import hcmus.zingmp3.music_service.repository.ArtistRepository;
import hcmus.zingmp3.music_service.repository.SongArtistRepository;
import hcmus.zingmp3.music_service.repository.SongComposerRepository;
import hcmus.zingmp3.music_service.service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.annotation.Transient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService {
    private final ArtistRepository artistRepository;
    private final SongArtistRepository songArtistRepository;
    private final SongComposerRepository songComposerRepository;
    private final ArtistMapper artistMapper;
    private final WebClient.Builder webClient;

    @Value("${server.host}")
    private String host;

    private Mono<ArtistImageDTO> saveImage(ArtistImageDTO imageDTO) {
        return webClient
                .build()
                .post()
                .uri("http://playback-service/api/playback/image/artist")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(imageDTO)
                .retrieve()
                .bodyToMono(ArtistImageDTO.class)
                .onErrorResume(e -> {
                    e.printStackTrace();
                    return Mono.empty();
                });
    }

    @Override
    public Mono<ArtistDTO> findById(String id) {
        return artistRepository.findById(id)
                .map(artistMapper::toDTO);

    }

    @Override
    public Mono<ArtistDTO> create(ArtistDTO artistDTO) {
        return findByAlias(artistDTO.getAlias())
                .switchIfEmpty(
                        saveImage(ArtistImageDTO
                                .builder()
                                .filePath(artistDTO.getAlias() + ".jpg")
                                .name(artistDTO.getAlias())
                                .build())
                                .flatMap(image -> artistRepository
                                        .save(artistMapper
                                                .toEntity(artistDTO)
                                                .setAsNew()
                                                .setThumbnail("http://" + host + "/api/playback/image/artist/" + image.getId())))
                                .onErrorResume(DuplicateKeyException.class, e -> artistRepository
                                        .findByAlias(artistDTO.getAlias()))
                                .map(artistMapper::toDTO)
                );
    }

    @Override
    public Mono<ArtistDTO> saveArtist(ArtistDTO artistDTO, String songId) {
        return create(artistDTO)
                .flatMap(artist -> songArtistRepository
                        .save(SongArtistEntity.builder()
                                        .artistId(artist.getId())
                                        .songId(songId)
                                        .build()
                                        .setAsNew())
                        .thenReturn(artist));

    }

    @Override
    public synchronized Mono<ArtistDTO> saveComposer(ArtistDTO artistDTO, String songId) {
        return create(artistDTO)
                .flatMap(artist-> songComposerRepository
                        .save(SongComposerEntity.builder()
                                    .composerId(artist.getId())
                                    .songId(songId)
                                    .build()
                                    .setAsNew())
                        .thenReturn(artist));
    }

    @Override
    public Flux<ArtistDTO> findAllArtistBySongId(String songId) {
        return songArtistRepository.findAllBySongId(songId)
                .flatMap(songArtistEntity -> artistRepository
                        .findById(songArtistEntity.getArtistId())
                )
                .map(artistMapper::toDTO);
    }

    @Override
    public Flux<ArtistDTO> findAllComposerBySongId(String songId) {
        return songComposerRepository.findAllBySongId(songId)
                .flatMap(songComposerEntity -> artistRepository
                        .findById(songComposerEntity.getComposerId())
                )
                .map(artistMapper::toDTO);
    }

    @Override
    public Mono<ArtistDTO> findByAlias(String alias) {
        return artistRepository.findByAlias(alias)
                .map(artistMapper::toDTO);
    }
}
