package zingmp3.hcmus.playlistservice.service.impl;

import com.google.common.collect.Sets;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zingmp3.hcmus.playlistservice.dto.*;
import zingmp3.hcmus.playlistservice.entity.PlaylistEntity;
import zingmp3.hcmus.playlistservice.exception.PlaylistNotFoundException;
import zingmp3.hcmus.playlistservice.mapper.PlaylistMapper;
import zingmp3.hcmus.playlistservice.repository.PlaylistArtistRepository;
import zingmp3.hcmus.playlistservice.repository.PlaylistRepository;
import zingmp3.hcmus.playlistservice.repository.PlaylistSongRepository;
import zingmp3.hcmus.playlistservice.service.ArtistService;
import zingmp3.hcmus.playlistservice.service.GenreService;
import zingmp3.hcmus.playlistservice.service.PlaylistService;
import zingmp3.hcmus.playlistservice.service.SongService;

import java.time.Duration;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PlaylistServiceImpl implements PlaylistService {
    private final PlaylistRepository playlistRepository;
    private final PlaylistMapper playlistMapper;
    private final WebClient.Builder webClient;
    private final SongService songService;
    private final ArtistService artistService;
    private final PlaylistSongRepository playlistSongRepository;
    private final GenreService genreService;
    private final PlaylistArtistRepository playlistArtistRepository;

    @Value("${server.host}")
    private String host;

    private Mono<PlaylistImageDTO> saveImage(PlaylistImageDTO playlistImageDTO) {
        return webClient
                .build()
                .post()
                .uri("http://playback-service/api/playback/image/playlist")
                .bodyValue(playlistImageDTO)
                .retrieve()
                .bodyToMono(PlaylistImageDTO.class);
    }

    @Override
    public Flux<PlaylistDTO> findAll(Pageable pageable) {
        return playlistRepository.findAllBy(pageable)
                .map(playlistMapper::toDTO)
                .flatMap(playlistDTO -> {
                        Flux<ArtistDTO> artistFlux = artistService.findAllArtistsByPlaylistId(playlistDTO.getId());
                        Flux<GenreDTO> genreFlux = genreService.findAllGenresByPlaylistId(playlistDTO.getId());

                        return Mono.zip(artistFlux.collectList(), genreFlux.collectList())
                                .map(tuple -> {
                                        playlistDTO.setArtists(new HashSet<>(tuple.getT1()));
                                        playlistDTO.setGenres(new HashSet<>(tuple.getT2()));
                                        return playlistDTO;
                                });
                });
    }

    @Override
    public Mono<PlaylistDTO> findById(String id) {
        return playlistRepository.findById(id)
                .map(playlistMapper::toDTO)
                .flatMap(playlistDTO -> {
                        Flux<ArtistDTO> artistFlux = artistService.findAllArtistsByPlaylistId(playlistDTO.getId());
                        Flux<GenreDTO> genreFlux = genreService.findAllGenresByPlaylistId(playlistDTO.getId());
                        Flux<SongDTO> songFlux = songService.findAllSongByPlaylistId(playlistDTO.getId(), Pageable.unpaged());

                        return Mono.zip(artistFlux.collectList(), genreFlux.collectList(), songFlux.collectList())
                                .map(tuple -> {
                                        playlistDTO.setArtists(new HashSet<>(tuple.getT1()));
                                        playlistDTO.setGenres(new HashSet<>(tuple.getT2()));
                                        playlistDTO.setSongs(new HashSet<>(tuple.getT3()));
                                        return playlistDTO;
                                });
                });
    }

    @Override
    public Mono<PlaylistDTO> create(PlaylistDTO playlistDTO) {
        System.out.println(playlistDTO.getTitle());
        return saveImage(PlaylistImageDTO
                        .builder()
                        .filePath(playlistDTO.getAliasTitle() + ".jpg")
                        .build())
                .flatMap(image -> playlistRepository
                        .save(playlistMapper
                                .toEntity(playlistDTO)
                                .setAsNew()
                                .setThumbnail(image.getId()))
                        .map(playlistMapper::toDTO)
                        .flatMap(playlist -> {
                                Mono<List<GenreDTO>> genresMono = Flux.fromIterable(playlistDTO.getGenres())
                                                .flatMap(genreDTO -> genreService.save(genreDTO, playlist.getId()))
                                                .collectList();
                                Mono<List<SongDTO>> songsMono = Flux.fromIterable(playlistDTO.getSongs())
                                                .flatMap(songDTO -> songService.save(songDTO, playlist.getId()))
                                                .collectList();
                                Mono<List<ArtistDTO>> artistsMono = Flux.fromIterable(playlistDTO.getArtists())
                                                .flatMap(artistDTO -> artistService.save(artistDTO, playlist.getId()))
                                                .collectList();
                                return Mono.zip(genresMono, songsMono, artistsMono)
                                        .map(tuple -> {
                                                playlist.setGenres(new HashSet<>(tuple.getT1()));
                                                playlist.setSongs(new HashSet<>(tuple.getT2()));
                                                playlist.setArtists(new HashSet<>(tuple.getT3()));
                                                return playlist;
                                        });
                        })
                );
    }

    @Override
    public Mono<PlaylistDTO> update(String id, PlaylistDTO playlistDTO) {
        return playlistRepository.findById(id)
                .map(Optional::of)
                .defaultIfEmpty(Optional.empty())
                .flatMap(optionalPlaylist -> {
                    if (optionalPlaylist.isPresent()) {
                        PlaylistEntity playlist = playlistMapper.toEntity(playlistDTO);
                        return playlistRepository.save(playlist);
                    }
                    return Mono.empty();
                })
                .flatMap(playlist -> Mono.just(playlistMapper.toDTO(playlist)));
    }

    @Override
    public Mono<Void> delete(String id) {
        return playlistRepository.findById(id)
                .switchIfEmpty(Mono.error(new PlaylistNotFoundException("Playlist not found " + id)))
                .flatMap(playlistRepository::delete);


    }

    @Override
    public Flux<PlaylistDTO> searchPlaylists(String name, Pageable pageable) {
        return playlistRepository
                .findByTitleContainingIgnoreCase(name, pageable)
                .map(playlistMapper::toDTO)
                .flatMap(playlistDTO -> {
                    Flux<ArtistDTO> artistFlux = artistService.findAllArtistsByPlaylistId(playlistDTO.getId());
                    Flux<GenreDTO> genreFlux = genreService.findAllGenresByPlaylistId(playlistDTO.getId());

                    return Mono.zip(artistFlux.collectList(), genreFlux.collectList())
                            .map(tuple -> {
                                playlistDTO.setArtists(new HashSet<>(tuple.getT1()));
                                playlistDTO.setGenres(new HashSet<>(tuple.getT2()));
                                return playlistDTO;
                            });
                });
    }
}
