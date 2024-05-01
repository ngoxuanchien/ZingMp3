package zingmp3.hcmus.playlistservice.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zingmp3.hcmus.playlistservice.dto.ArtistDTO;

public interface ArtistService {
    Flux<ArtistDTO> findAllArtistsByPlaylistId(String id);
    Mono<ArtistDTO> save(ArtistDTO artistDTO, String playlistId);
}
