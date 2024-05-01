package hcmus.zingmp3.music_service.service.impl;

import hcmus.zingmp3.music_service.dto.SongDTO;
import hcmus.zingmp3.music_service.entity.SongUserEntity;
import hcmus.zingmp3.music_service.mapper.SongMapper;
import hcmus.zingmp3.music_service.repository.SongRepository;
import hcmus.zingmp3.music_service.repository.SongUserRepository;
import hcmus.zingmp3.music_service.service.SongService;
import hcmus.zingmp3.music_service.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final SongUserRepository songUserRepository;
    private final SongService songService;

    @Override
    public Mono<Void> saveHistory(String songId, String userId) {
        return songService
                .existsById(songId)
                .flatMap(exists -> {
                    if (exists) {
                        return songUserRepository
                                .save(SongUserEntity
                                        .builder()
                                        .songId(songId)
                                        .userId(userId)
                                        .build()
                                        .setAsNew())
                                .then();
                    }
                    return Mono.empty();
                });
    }

    @Override
    public Flux<SongDTO> getHistory(String userId, Pageable pageable) {
        return songUserRepository.findAllByUserId(userId, pageable)
                .flatMap(songUserEntity -> songService
                        .findById(songUserEntity
                                .getSongId()));
    }
}
