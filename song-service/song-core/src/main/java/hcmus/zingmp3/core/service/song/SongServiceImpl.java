package hcmus.zingmp3.core.service.song;

import hcmus.zingmp3.common.domain.model.Genre;
import hcmus.zingmp3.common.domain.model.Song;
import hcmus.zingmp3.common.domain.model.SongStatus;
import hcmus.zingmp3.common.service.song.SongQueryService;
import hcmus.zingmp3.core.service.genre.GenreService;
import hcmus.zingmp3.core.web.dto.SongRequest;
import hcmus.zingmp3.core.web.dto.SongResponse;
import hcmus.zingmp3.core.web.dto.mapper.SongMapper;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {

    private final SongQueryService queryService;

    private final SongCommandService commandService;

    private final GenreService genreService;

    private final SongMapper mapper;

    @Override
    public Song getById(UUID id) {
        return queryService.getById(id);
    }

    @Override
    public Song getByAlias(String alias) {
        return queryService.getByAlias(alias);
    }

    @Override
    public boolean existsByAlias(String alias) {
        return queryService.existsByAlias(alias);
    }

    @Override
    public List<Song> getAll(Pageable pageable) {
        return queryService.getAll(pageable);
    }

    @Override
    public List<Song> getAll() {
        return queryService.getAll();
    }

    @Override
    public void update(Song object) {
        commandService.update(object);
    }

    @Override
    public void delete(Song object) {
        commandService.delete(object);
    }

    @Override
    public void create(Song object) {
        commandService.create(object);
    }

    @Override
    public SongResponse createSong(SongRequest request) {
        // todo: implement this method

        // todo: check the artist exists or not
        Set<UUID> artistIds = new HashSet<>();

        // todo: check the composer exists or not
        Set<UUID> composerIds = new HashSet<>();

        // todo: check the genre exists or not
        Set<Genre> genres = new HashSet<>();
        request.genreIds().stream()
                .map(genreService::getById)
                .forEach(genres::add);

        // todo: check the media exists or not
        Set<UUID> mediaIds = new HashSet<>();

        Song song = mapper.toEntity(request);
        song.setArtistIds(artistIds);
        song.setComposerIds(composerIds);
        song.setGenres(genres);
        song.setMediaIds(mediaIds);
        song.setStatus(SongStatus.APPROVAL_PENDING);

        create(song);

        return mapper.toDto(song);
    }

    @Override
    public SongResponse updateSong(SongRequest request) {
        // todo: implement this method

        // todo: check the artist exists or not
        Set<UUID> artistIds = new HashSet<>();

        // todo: check the composer exists or not
        Set<UUID> composerIds = new HashSet<>();

        // todo: check the genre exists or not
        Set<Genre> genres = new HashSet<>();

        // todo: check the media exists or not
        Set<UUID> mediaIds = new HashSet<>();

        Song song = getById(request.id());
        merge(song, request);

        update(song);

        return mapper.toDto(song);
    }

    private void merge(Song song, SongRequest request) {
        if (StringUtils.isNotBlank(request.title())) {
            song.setTitle(request.title());
        }

        if (StringUtils.isNotBlank(request.lyric())) {
            song.setLyric(request.lyric());
        }

        song.setStatus(request.status());
    }

    @Override
    public void deleteSong(UUID id) {
        Song song = getById(id);
        delete(song);
    }

    @Override
    public SongResponse getSongById(UUID id) {
        var song = getById(id);
        return mapper.toDto(song);
    }

    @Override
    public SongResponse getSongByAlias(String alias) {
        var song = getByAlias(alias);
        return mapper.toDto(song);
    }

    @Override
    public void approvedSong(String alias) {
        var song = getByAlias(alias);
        song.setStatus(SongStatus.APPROVED);
        update(song);
    }

    @Override
    public void rejectedSong(String alias) {
        var song = getByAlias(alias);
        song.setStatus(SongStatus.REJECTED);
        update(song);
    }

    @Override
    public void releasedSong(String alias) {
        var song = getByAlias(alias);
        song.setStatus(SongStatus.RELEASED);
        update(song);
    }

    @Override
    public List<SongResponse> getAllSongs(Pageable pageable) {
        return mapper.toDto(getAll(pageable));
    }

    @Override
    public List<SongResponse> getAllSongs() {
        return mapper.toDto(getAll());
    }
}
