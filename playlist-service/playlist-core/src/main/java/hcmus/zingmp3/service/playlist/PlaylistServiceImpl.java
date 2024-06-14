package hcmus.zingmp3.service.playlist;

import hcmus.zingmp3.common.domain.model.Playlist;
import hcmus.zingmp3.common.service.playlist.PlaylistQueryService;
import hcmus.zingmp3.web.dto.PlaylistRequest;
import hcmus.zingmp3.web.dto.PlaylistResponse;
import hcmus.zingmp3.web.dto.mapper.PlaylistMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class PlaylistServiceImpl implements PlaylistService {

    private final PlaylistCommandService commandService;
    private final PlaylistQueryService queryService;
    private final PlaylistMapper mapper;

    @Override
    public Playlist getByAlias(String alias) {
        return queryService.getByAlias(alias);
    }

    @Override
    public Playlist getById(UUID id) {
        return queryService.getById(id);
    }

    @Override
    public boolean existsByAlias(String alias) {
        return queryService.existsByAlias(alias);
    }

    @Override
    public List<Playlist> getAll(Pageable pageable) {
        return queryService.getAll(pageable);
    }

    @Override
    public List<Playlist> getAll() {
        return queryService.getAll();
    }

    @Override
    public void create(Playlist object) {
        commandService.create(object);
    }

    @Override
    public void update(Playlist object) {
        commandService.update(object);
    }

    @Override
    public void delete(Playlist object) {
        commandService.delete(object);
    }

    @Override
    public PlaylistResponse createPlaylist(PlaylistRequest request) {
        var playlist = mapper.toEntity(request);

        create(playlist);

        return mapper.toDto(playlist);
    }

    @Override
    public PlaylistResponse getPlaylistById(UUID id) {
        return mapper.toDto(getById(id));
    }

    @Override
    public PlaylistResponse getPlaylistByAlias(String alias) {
        return mapper.toDto(getByAlias(alias));
    }

    @Override
    public List<PlaylistResponse> getAllPlaylists() {
        return mapper.toDto(getAll());
    }

    @Override
    public List<PlaylistResponse> getAllPlaylists(Pageable pageable) {
        return mapper.toDto(getAll(pageable));
    }

    @Override
    public PlaylistResponse updatePlaylist(PlaylistRequest request) {
        var playlist = getById(request.id());
        mergePlaylist(playlist, request);
        update(playlist);
        return mapper.toDto(playlist);
    }

    private <T> void setIfNotNull(Consumer<T> setter, T value) {
        if (value != null) {
            setter.accept(value);
        }
    }

    private void mergePlaylist(Playlist playlist, PlaylistRequest request) {
        setIfNotNull(playlist::setAlias, request.alias());
        setIfNotNull(playlist::setTitle, request.title());
        setIfNotNull(playlist::setThumbnailId, request.thumbnailId());
        setIfNotNull(playlist::setType, request.type());
        setIfNotNull(playlist::setDescription, request.description());
        setIfNotNull(playlist::setArtistIds, request.artistIds());
        setIfNotNull(playlist::setSongIds, request.songIds());
        setIfNotNull(playlist::setPublic, request.isPublic());
    }

    @Override
    public void deletePlaylist(UUID id) {
        Playlist playlist = getById(id);
        delete(playlist);
    }

    @Override
    public List<PlaylistResponse> getMyPlaylists(Pageable pageable) {
        UUID userId = UUID.fromString(SecurityContextHolder.getContext().getAuthentication().getName());
        return mapper.toDto(queryService.getMyPlaylists(userId, pageable));
    }

    @Override
    public List<PlaylistResponse> searchPlaylist(String title, Pageable pageable) {
        return mapper.toDto(queryService.searchPlaylist(title, pageable));
    }
}
