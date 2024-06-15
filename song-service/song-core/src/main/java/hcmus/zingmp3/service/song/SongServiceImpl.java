package hcmus.zingmp3.service.song;

import hcmus.zingmp3.common.domain.model.Song;
import hcmus.zingmp3.common.domain.model.SongStatus;
import hcmus.zingmp3.common.service.song.SongQueryService;
import hcmus.zingmp3.service.media.MediaService;
import hcmus.zingmp3.web.dto.SongRequest;
import hcmus.zingmp3.web.dto.SongResponse;
import hcmus.zingmp3.web.dto.mapper.SongMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {

    private final SongQueryService queryService;

    private final SongCommandService commandService;

    private final SongMapper mapper;

    private final MediaService mediaService;


    @Override
    public SongResponse createSong(SongRequest request) {
        Song song = mapper.toEntity(request);
        setDuration(song, request.mediaIds());

        commandService.create(song);

        return mapper.toDto(song);
    }

    @Override
    public SongResponse updateSong(SongRequest request) {
        // todo: implement this method
        Song song = queryService.getById(request.id());
        mergeSong(song, request);

        commandService.update(song);

        return mapper.toDto(song);
    }

    private <T> void setIfNotNull(Consumer<T> setter, T value) {
        if (value != null) {
            setter.accept(value);
        }
    }

    private void setDuration(Song song, List<UUID> mediaIds) {
        if (mediaIds == null) {
            return;
        }

        song.setDuration(mediaService.getById(mediaIds.getFirst()).getDuration());
    }

    private void mergeSong(Song song, SongRequest request) {
        setIfNotNull(song::setAlias, request.alias());
        setIfNotNull(song::setAlias, request.alias());
        setIfNotNull(song::setTitle, request.title());
        setIfNotNull(song::setThumbnailId, request.thumbnailId());
        setIfNotNull(song::setArtistIds, request.artistIds());
        setIfNotNull(song::setGenreIds, request.genreIds());
        setIfNotNull(song::setComposerIds, request.composerIds());
        setIfNotNull(song::setReleaseDate, request.releaseDate());
        setIfNotNull(song::setListen, request.listen());
        setIfNotNull(song::setLiked, request.liked());
        setIfNotNull(song::setMediaIds, request.mediaIds());

        setDuration(song, request.mediaIds());
    }

    @Override
    public void deleteSong(UUID id) {
        Song song = queryService.getById(id);
        commandService.delete(song);
    }

    @Override
    public SongResponse getSongById(UUID id) {
        var song = queryService.getById(id);
        return mapper.toDto(song);
    }

    @Override
    public SongResponse getSongByAlias(String alias) {
        var song = queryService.getByAlias(alias);
        return mapper.toDto(song);
    }

    @Override
    public void approvedSong(String alias) {
        var song = queryService.getByAlias(alias);
        song.approved();
        commandService.approved(song);
    }

    @Override
    public void rejectedSong(String alias) {
        var song = queryService.getByAlias(alias);
        song.rejected();
        commandService.rejected(song);
    }

    @Override
    public void releasedSong(String alias) {
        var song = queryService.getByAlias(alias);
        song.released();
        commandService.released(song);
    }

    @Override
    public Page<SongResponse> getAllSongs(Pageable pageable) {
        return mapper.toDto(queryService.getAll(pageable));
    }

    @Override
    public List<SongResponse> getAllSongs() {
        return mapper.toDto(queryService.getAll());
    }

    @Override
    public Page<SongResponse> getAllMySongs(Pageable pageable) {
        UUID userId = UUID.fromString(SecurityContextHolder.getContext().getAuthentication().getName());
        return mapper.toDto(queryService.getAllByCreatedBy(userId, pageable));
    }

    @Override
    public Page<SongResponse> searchSong(String title, Pageable pageable) {
        return mapper.toDto(queryService.searchSong(title, pageable));
    }

    @Override
    public Page<SongResponse> searchMySongs(String name, List<SongStatus> status, Pageable pageable) {
        UUID userId = UUID.fromString(SecurityContextHolder.getContext().getAuthentication().getName());
        return mapper.toDto(queryService.searchMySongs(name, status, userId, pageable));
    }

    @Override
    public Page<SongResponse> getAllMySongs(List<SongStatus> status, Pageable pageable) {
        UUID userId = UUID.fromString(SecurityContextHolder.getContext().getAuthentication().getName());
        return mapper.toDto(queryService.getAllMySongs(status, userId, pageable));
    }
}
