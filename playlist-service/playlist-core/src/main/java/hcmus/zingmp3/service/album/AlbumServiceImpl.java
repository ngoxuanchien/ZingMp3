package hcmus.zingmp3.service.album;

import hcmus.zingmp3.common.domain.model.Album;
import hcmus.zingmp3.common.domain.model.AlbumStatus;
import hcmus.zingmp3.common.service.album.AlbumQueryService;
import hcmus.zingmp3.web.dto.AlbumRequest;
import hcmus.zingmp3.web.dto.AlbumResponse;
import hcmus.zingmp3.web.dto.mapper.AlbumMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService {

    private final AlbumQueryService queryService;
    private final AlbumCommandService commandService;
    private final AlbumMapper mapper;

    @Override
    public Album getByAlias(String alias) {
        return queryService.getByAlias(alias);
    }

    @Override
    public Album getById(UUID id) {
        return queryService.getById(id);
    }

    @Override
    public boolean existsByAlias(String alias) {
        return queryService.existsByAlias(alias);
    }

    @Override
    public List<Album> getAll(Pageable pageable) {
        return queryService.getAll(pageable);
    }

    @Override
    public List<Album> getAll() {
        return queryService.getAll();
    }

    @Override
    public void create(Album object) {
        commandService.create(object);
    }

    @Override
    public void update(Album object) {
        commandService.update(object);
    }

    @Override
    public void delete(Album object) {
        commandService.delete(object);
    }

    @Override
    public AlbumResponse createAlbum(AlbumRequest request) {
        var album = mapper.toEntity(request);
        album.setId(UUID.randomUUID());
        create(album);
        return mapper.toDto(album);
    }

    @Override
    public AlbumResponse getAlbumByAlias(String alias) {
        return mapper.toDto(getByAlias(alias));
    }

    @Override
    public AlbumResponse getAlbumById(UUID id) {
        return mapper.toDto(getById(id));
    }

    @Override
    public List<AlbumResponse> getAllAlbums() {
        return mapper.toDto(getAll());
    }

    @Override
    public List<AlbumResponse> getAllAlbums(Pageable pageable) {
        return mapper.toDto(getAll(pageable));
    }

    @Override
    public AlbumResponse updateAlbum(AlbumRequest request) {
        var album = getById(request.id());
        mergeAlbum(album, request);

        update(album);

        return mapper.toDto(album);
    }

    @Override
    public void approvedAlbum(String alias) {
        var album = getByAlias(alias);
        album.approved();
        commandService.approve(album);
    }

    @Override
    public void rejectedAlbum(String alias) {
        var album = getByAlias(alias);
        album.rejected();
        commandService.reject(album);
    }

    @Override
    public void releasedAlbum(String alias) {
        var album = getByAlias(alias);
        album.released();
        commandService.release(album);
    }

    @Override
    public AlbumResponse removeSongFromAlbum(UUID albumId, UUID songId) {
        var album = getById(albumId);
        album.removeSong(songId);
        update(album);
        return mapper.toDto(album);
    }

    @Override
    public AlbumResponse addSongToAlbum(UUID albumId, UUID songId) {
        var album = getById(albumId);
        album.addSong(songId);
        update(album);
        return mapper.toDto(album);
    }

    private <T> void setIfNotNull(Consumer<T> setter, T value) {
        if (value != null) {
            setter.accept(value);
        }
    }

    void mergeAlbum(Album album, AlbumRequest request) {
        setIfNotNull(album::setAlias, request.alias());
        setIfNotNull(album::setThumbnailId, request.thumbnailId());
        setIfNotNull(album::setTitle, request.title());
        setIfNotNull(album::setDescription, request.description());
        setIfNotNull(album::setArtistIds, request.artistIds());
        setIfNotNull(album::setReleaseDate, request.releaseDate());
        setIfNotNull(album::setSongIds, request.songIds());
    }

    @Override
    public void deleteAlbum(UUID id) {
        Album album = getById(id);
        delete(album);
    }
}