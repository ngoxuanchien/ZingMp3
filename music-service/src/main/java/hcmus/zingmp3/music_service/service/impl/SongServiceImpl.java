package hcmus.zingmp3.music_service.service.impl;

import hcmus.zingmp3.music_service.controller.StreamingController;
import hcmus.zingmp3.music_service.dto.*;
import hcmus.zingmp3.music_service.entity.SongEntity;
import hcmus.zingmp3.music_service.entity.StreamingEntity;
import hcmus.zingmp3.music_service.mapper.SongMapper;
import hcmus.zingmp3.music_service.mapper.StreamingMapper;
import hcmus.zingmp3.music_service.repository.SongRepository;
import hcmus.zingmp3.music_service.repository.StreamingRepository;
import hcmus.zingmp3.music_service.service.ArtistService;
import hcmus.zingmp3.music_service.service.GenreService;
import hcmus.zingmp3.music_service.service.SongService;
import hcmus.zingmp3.music_service.service.StreamingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {
    private final SongRepository songRepository;
    private final SongMapper songMapper;
    private final StreamingService streamingService;
    private final StreamingMapper streamingMapper;
    private final ArtistService artistService;
    private final GenreService genreService;
    private final WebClient.Builder webClient;

    @Value("${server.host}")
    private String host;

    private Mono<SongImageDTO> saveImage(SongImageDTO imageDTO) {
        return webClient
                .build()
                .post()
                .uri("http://playback-service/api/playback/image/song")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(imageDTO)
                .retrieve()
                .bodyToMono(SongImageDTO.class)
                .onErrorResume(e -> {
                    e.printStackTrace();
                    return Mono.empty();
                });
    }

    @Override
    public Mono<SongDTO> create(SongDTO songDTO) {
        return findByAlias(songDTO.getAlias())
                .switchIfEmpty(saveImage(SongImageDTO
                        .builder()
                        .filePath(songDTO.getAlias() + ".jpg")
                        .name(songDTO.getAlias())
                        .build())
                        .flatMap(image -> {
                            songDTO.setThumbnail("http://" + host + "/api/playback/image/song/" + image.getId());
                            return Mono.just(songDTO);
                        })
                        .flatMap(song -> {
                            Mono<StreamingDTO> streamingMono = streamingService
                                    .create(songDTO.getStreaming(), songDTO.getAlias());
                            return streamingMono
                                    .flatMap(streaming -> songRepository
                                            .save(songMapper
                                                    .toEntity(song)
                                                    .setAsNew()
                                                    .addStreaming(streaming.getId()))
                                            .onErrorResume(DuplicateKeyException.class, e -> songRepository
                                                    .findByAlias(song.getAlias())));
                        })
                        .map(songMapper::toDTO)
                        .flatMap(song -> {
                                Mono<List<ArtistDTO>> artistMono = Flux
                                        .fromIterable(songDTO.getArtists())
                                        .flatMap(artist -> artistService
                                                .saveArtist(artist, song.getId()))
                                        .collectList()
                                        .defaultIfEmpty(new ArrayList<>());
                                Mono<List<ArtistDTO>> composerMono = Flux
                                        .fromIterable(songDTO.getComposers())
                                        .flatMap(composer -> artistService
                                                .saveComposer(composer, song.getId()))
                                        .collectList()
                                        .defaultIfEmpty(new ArrayList<>());
                                Mono<List<GenreDTO>> genreMono = Flux
                                        .fromIterable(songDTO.getGenres())
                                        .flatMap(genre -> genreService
                                                .saveGenre(genre, song.getId()))
                                        .collectList()
                                        .defaultIfEmpty(new ArrayList<>());

                                return Mono.zip(artistMono, composerMono, genreMono)
                                        .map(tuple -> {
                                            song.setArtists(Sets.newHashSet(tuple.getT1()));
                                            song.setComposers(Sets.newHashSet(tuple.getT2()));
                                            song.setGenres(Sets.newHashSet(tuple.getT3()));
                                            return song;
                                        });
                            })
                )
                .onErrorResume(e -> {
                    e.printStackTrace();
                    return Mono.empty();
                });
    }

    @Override
    public Mono<SongDTO> findById(String id) {
        return songRepository
                .findById(id)
                .flatMap(songEntity ->
                    streamingService
                            .findById(songEntity.getStreamingId())
                            .map(streamingDTO -> {
                                SongDTO result = songMapper.toDTO(songEntity);
                                result.setStreaming(streamingDTO);
                                return result;
                            })
                )
                .flatMap(songDTO -> {
                    Mono<List<ArtistDTO>> artistMono = artistService
                            .findAllArtistBySongId(songDTO.getId())
                            .collectList();
                    Mono<List<GenreDTO>> genreMono = genreService
                            .findBySongId(songDTO.getId())
                            .collectList();
                    Mono<List<ArtistDTO>> composerMono = artistService
                            .findAllComposerBySongId(songDTO.getId())
                            .collectList();
                    return Mono.zip(artistMono, genreMono, composerMono)
                            .map(tuple -> {
                                songDTO.setArtists(new HashSet<>(tuple.getT1()));
                                songDTO.setGenres(new HashSet<>(tuple.getT2()));
                                songDTO.setComposers(new HashSet<>(tuple.getT3()));
                                return songDTO;
                            });
                });
    }

    @Override
    public Mono<SongDTO> findByAlias(String alias) {
        return songRepository.findByAlias(alias)
                .map(songMapper::toDTO);
    }

    @Override
    public Mono<Boolean> existsById(String id) {
        return songRepository.existsById(id);
    }
}
