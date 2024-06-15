package hcmus.zingmp3.service.artist;

import hcmus.zingmp3.common.domain.model.Artist;
import hcmus.zingmp3.common.domain.model.ArtistStatus;
import hcmus.zingmp3.common.service.artist.ArtistQueryService;
import hcmus.zingmp3.web.dto.ArtistRequest;
import hcmus.zingmp3.web.dto.ArtistResponse;
import hcmus.zingmp3.web.dto.mapper.ArtistMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService {
    private final ArtistQueryService queryService;
    private final ArtistCommandService commandService;
    private final ArtistMapper artistMapper;

    @Override
    public void create(Artist object) {
        commandService.create(object);
    }

    @Override
    public void update(Artist object) {
        commandService.update(object);
    }

    @Override
    public void delete(Artist object) {
        commandService.delete(object);
    }

    @Override
    public Artist getByAlias(String alias) {
        return queryService.getByAlias(alias);
    }

    @Override
    public Artist getById(UUID id) {
        return queryService.getById(id);
    }

    @Override
    public boolean existsByAlias(String alias) {
        return queryService.existsByAlias(alias);
    }

    @Override
    public Page<Artist> getAll(Pageable pageable) {
        return queryService.getAll(pageable);
    }

    @Override
    public List<Artist> getAll() {
        return queryService.getAll();
    }

    @Override
    public ArtistResponse createArtist(ArtistRequest request) {
        Artist artist = artistMapper.toEntity(request);
        artist.setId(UUID.randomUUID());

        if (artist.getThumbnailId() == null) {
            artist.setThumbnailId(UUID.fromString("00000000-0000-0000-0000-000000000000"));
        }

        create(artist);
        return artistMapper.toDto(artist);
    }

    @Override
    public ArtistResponse updateArtist(ArtistRequest request) {
        Artist artist = queryService.getById(request.id());
        merge(artist, request);

        update(artist);
        return artistMapper.toDto(artist);
    }

    private <T> void setIfNotNull(Consumer<T> setter, T value) {
        if (value != null) {
            setter.accept(value);
        }
    }

    private void merge(Artist artist, ArtistRequest request) {
        setIfNotNull(artist::setAlias, request.alias());
        setIfNotNull(artist::setName, request.name());
        setIfNotNull(artist::setRealName, request.realName());
        setIfNotNull(artist::setThumbnailId, request.thumbnailId());
    }

    @Override
    public void deleteArtist(UUID id) {
        Artist artist = queryService.getById(id);
        delete(artist);
    }

    @Override
    public ArtistResponse getArtistByAlias(String alias) {
        return artistMapper.toDto(getByAlias(alias));
    }

    @Override
    public void approveArtist(String alias) {
        Artist artist = getByAlias(alias);
        artist.approve();
        commandService.approve(artist);
    }

    @Override
    public void rejectArtist(String alias) {
        Artist artist = getByAlias(alias);
        artist.reject();
        commandService.reject(artist);
    }

    @Override
    public ArtistResponse getArtistById(UUID id) {
        return artistMapper.toDto(getById(id));
    }

    @Override
    public Page<ArtistResponse> getAllArtists(Pageable pageable) {
        return artistMapper.toDto(getAll(pageable));
    }

    @Override
    public List<ArtistResponse> getAllArtists() {
        return artistMapper.toDto(getAll());
    }

    @Override
    public Page<ArtistResponse> searchArtist(String name, List<ArtistStatus> status, Pageable pageable) {
        return artistMapper.toDto(queryService.searchArtist(name, status, pageable));
    }

    @Override
    public Page<ArtistResponse> getAllArtists(List<ArtistStatus> status, Pageable pageable) {
        return artistMapper.toDto(queryService.getAll(status, pageable));
    }
}
