package hcmus.zingmp3.music_service.service.impl;

import hcmus.zingmp3.music_service.dto.GenreDTO;
import hcmus.zingmp3.music_service.entity.SongGenreEntity;
import hcmus.zingmp3.music_service.mapper.GenreMapper;
import hcmus.zingmp3.music_service.repository.GenreRepository;
import hcmus.zingmp3.music_service.repository.SongGenreRepository;
import hcmus.zingmp3.music_service.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;
    private final SongGenreRepository songGenreRepository;
    private final GenreMapper genreMapper;



    @Override
    public Mono<GenreDTO> create(GenreDTO genreDTO) {
        return findByAlias(genreDTO.getAlias())
                .switchIfEmpty(
                        genreRepository.save(genreMapper.toEntity(genreDTO)
                                        .setAsNew())
                                .onErrorResume(DuplicateKeyException.class, e -> genreRepository
                                        .findByAlias(genreDTO.getAlias()))
                                .map(genreMapper::toDTO)
                );
    }

    @Override
    public Mono<GenreDTO> findById(String id) {
        return genreRepository.findById(id)
                    .map(genreMapper::toDTO);
    }

    @Override
    public Mono<GenreDTO> findByAlias(String alias) {
        return genreRepository.findByAlias(alias)
                .map(genreMapper::toDTO);
    }

    @Override
    public Mono<GenreDTO> saveGenre(GenreDTO genre, String id) {
        return create(genre)
                .flatMap(genreDTO -> songGenreRepository
                        .save(SongGenreEntity.builder()
                                    .genreId(genreDTO.getId())
                                    .songId(id)
                                    .build()
                                    .setAsNew())
                        .thenReturn(genreDTO));
    }

    @Override
    public Flux<GenreDTO> findBySongId(String songId) {
        return songGenreRepository.findBySongId(songId)
                .flatMap(songGenreEntity -> genreRepository
                        .findById(songGenreEntity.getGenreId())
                )
                .map(genreMapper::toDTO);
    }
}
