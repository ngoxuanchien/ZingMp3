package hcmus.zingmp3.core.service.song;

import hcmus.zingmp3.common.domain.exception.ResourceAlreadyExistsException;
import hcmus.zingmp3.common.domain.model.Genre;
import hcmus.zingmp3.common.domain.model.Song;
import hcmus.zingmp3.common.domain.model.SongStatus;
import hcmus.zingmp3.common.service.song.SongQueryService;
import hcmus.zingmp3.core.service.artist.ArtistService;
import hcmus.zingmp3.core.service.genre.GenreService;
import hcmus.zingmp3.core.service.image.ImageService;
import hcmus.zingmp3.core.service.media.MediaService;
import hcmus.zingmp3.core.web.dto.SongRequest;
import hcmus.zingmp3.core.web.dto.SongResponse;
import hcmus.zingmp3.core.web.dto.mapper.SongMapper;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {

    private final SongQueryService queryService;

    private final SongCommandService commandService;

    private final MediaService mediaService;

    private final GenreService genreService;

    private final ArtistService artistService;

    private final SongMapper mapper;

    private final ImageService imageService;

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

    private Set<UUID> checkArtist(Set<UUID> artistIds) {
        return Optional.ofNullable(artistIds)
                .orElse(Collections.emptySet())
                .stream()
                .map(artistService::getById)
                .map(artist -> UUID.fromString(artist.getId()))
                .collect(Collectors.toSet());
    }

    private Set<Genre> checkGenre(Set<UUID> genreIds) {
        return Optional.ofNullable(genreIds)
                .orElse(Collections.emptySet())
                .stream()
                .map(genreService::getById)
                .collect(Collectors.toSet());
    }

    private Set<UUID> checkMedia(Set<UUID> mediaIds) {
        return Optional.ofNullable(mediaIds)
                .orElse(Collections.emptySet())
                .stream()
                .map(mediaService::getById)
                .map(media -> UUID.fromString(media.getId()))
                .collect(Collectors.toSet());
    }

    private UUID checkThumbnail(UUID thumbnailId) {
        return Optional
                .ofNullable(thumbnailId)
                .orElse(UUID.fromString("00000000-0000-0000-0000-000000000000"));
    }

    @Override
    public SongResponse createSong(SongRequest request) {
        // todo: implement this method

        if (queryService.existsByAlias(request.alias())) {
            throw new ResourceAlreadyExistsException(
                    String.format("Song with alias %s already exists", request.alias())
            );
        }

        // todo: check the artist exists or not
        Set<UUID> artistIds = checkArtist(request.artistIds());

        // todo: check the composer exists or not
        Set<UUID> composerIds = checkArtist(request.composerIds());

        // todo: check the genre exists or not
        Set<Genre> genres = checkGenre(request.genreIds());

        // todo: check the media exists or not
        Set<UUID> mediaIds = checkMedia(request.mediaIds());

        // todo: check the thumbnail exists or not
        UUID thumbnailId = checkThumbnail(request.thumbnailId());
        imageService.getById(thumbnailId);

        Song song = mapper.toEntity(request);
        song.setId(UUID.randomUUID());
        song.setThumbnailId(thumbnailId);
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
        if (queryService.existsByAlias(request.alias())) {
            throw new ResourceAlreadyExistsException(
                    String.format("Song with alias %s already exists", request.alias())
            );
        }

        // todo: check the artist exists or not
        Set<UUID> artistIds = checkArtist(request.artistIds());

        // todo: check the composer exists or not
        Set<UUID> composerIds = checkArtist(request.composerIds());

        // todo: check the genre exists or not
        Set<Genre> genres = checkGenre(request.genreIds());

        // todo: check the media exists or not
        Set<UUID> mediaIds = checkMedia(request.mediaIds());

        // todo: check the thumbnail exists or not
        UUID thumbnailId = checkThumbnail(request.thumbnailId());
        imageService.getById(thumbnailId);

        Song song = getById(request.id());
        merge(song, request);
        song.setGenres(genres);
        song.setThumbnailId(thumbnailId);
        song.setArtistIds(artistIds);
        song.setComposerIds(composerIds);
        song.setMediaIds(mediaIds);

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
