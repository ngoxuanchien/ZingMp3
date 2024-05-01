package hcmus.zingmp3.music_service.service;

import hcmus.zingmp3.music_service.dto.GenreDTO;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GenreService {
    Mono<GenreDTO> create(GenreDTO genreDTO);
    Mono<GenreDTO> findById(String id);

    Mono<GenreDTO> findByAlias(String alias);

    Mono<GenreDTO> saveGenre(GenreDTO genre, String id);
    Flux<GenreDTO> findBySongId(String songId);
}
