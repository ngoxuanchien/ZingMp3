package zingmp3.hcmus.playlistservice.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zingmp3.hcmus.playlistservice.dto.GenreDTO;

@Service
public interface GenreService {
    Flux<GenreDTO> findAllGenresByPlaylistId(String id);
    Mono<GenreDTO> save(GenreDTO genreDTO, String playlistId);

}
