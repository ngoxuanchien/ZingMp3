package hcmus.zingmp3.music_service.service;

import hcmus.zingmp3.music_service.dto.SongDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public interface UserService {
    Mono<Void> saveHistory(String songId, String userId);

    Flux<SongDTO> getHistory(String userId, Pageable pageable);
}
