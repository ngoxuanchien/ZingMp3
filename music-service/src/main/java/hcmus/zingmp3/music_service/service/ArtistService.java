package hcmus.zingmp3.music_service.service;

import hcmus.zingmp3.music_service.dto.ArtistDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ArtistService {
    Mono<ArtistDTO> findById(String id);
    Mono<ArtistDTO> create(ArtistDTO artistDTO);

    Mono<ArtistDTO> findByAlias(String alias);
    Mono<ArtistDTO> saveArtist(ArtistDTO artistDTO, String songId);
    Mono<ArtistDTO> saveComposer(ArtistDTO artistDTO, String songId);

    Flux<ArtistDTO> findAllArtistBySongId(String songId);
    Flux<ArtistDTO> findAllComposerBySongId(String songId);
}
