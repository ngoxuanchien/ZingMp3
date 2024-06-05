package hcmus.zingmp3.core.service.artist;

import hcmus.zingmp3.common.domain.exception.AliasIsExistsException;
import hcmus.zingmp3.common.domain.exception.ResourceNotFoundException;
import hcmus.zingmp3.common.domain.model.Artist;
import hcmus.zingmp3.common.domain.model.ArtistStatus;
import hcmus.zingmp3.common.service.artist.ArtistQueryService;
import hcmus.zingmp3.core.service.image.ImageClientService;
import hcmus.zingmp3.core.web.dto.ArtistRequest;
import hcmus.zingmp3.core.web.dto.ArtistResponse;
import hcmus.zingmp3.core.web.dto.mapper.ArtistMapper;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService {
    private final ArtistQueryService queryService;
    private final ArtistCommandService commandService;
    private final ArtistMapper artistMapper;
    private final ImageClientService imageClient;

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
    public List<Artist> getAll(Pageable pageable) {
        return queryService.getAll(pageable);
    }

    @Override
    public List<Artist> getAll() {
        return queryService.getAll();
    }

    @Override
    public ArtistResponse createArtist(ArtistRequest request) {
        if (existsByAlias(request.alias())) {
            throw new AliasIsExistsException(
                    String.format("Alias %s is already exist", request.alias())
            );
        }

        Artist artist = artistMapper.toEntity(request);
        artist.setId(UUID.randomUUID());
        artist.setStatus(ArtistStatus.APPROVAL_PENDING);

        if (artist.getThumbnailId() == null) {
            artist.setThumbnailId(UUID.fromString("00000000-0000-0000-0000-000000000000"));
        } else if (!imageClient.isImageExist(artist.getThumbnailId())) {
            throw new ResourceNotFoundException(
                    String.format("Thumbnail %s is not found", artist.getThumbnailId())
            );
        }

        create(artist);
        return artistMapper.toDto(artist);
    }

    @Override
    public ArtistResponse updateArtist(ArtistRequest request) {
        // todo: check thumbnailId
        Artist artist = queryService.getById(request.id());
        merge(artist, request);
        if (artist.getThumbnailId() == null) {
            artist.setThumbnailId(UUID.fromString("00000000-0000-0000-0000-000000000000"));
        } else if (!imageClient.isImageExist(artist.getThumbnailId())) {
            throw new ResourceNotFoundException(
                    String.format("Thumbnail %s is not found", artist.getThumbnailId())
            );
        }

        update(artist);
        return artistMapper.toDto(artist);
    }

    private void merge(Artist artist, ArtistRequest request) {
        if (StringUtils.isNotEmpty(request.alias())) {
            artist.setAlias(request.alias());
        }

        if (request.thumbnailId() != null) {
            artist.setThumbnailId(request.thumbnailId());
        }

        if (request.status() != null) {
            artist.setStatus(request.status());
        }

        if (StringUtils.isNotEmpty(request.name())) {
            artist.setName(request.name());
        }

        if (StringUtils.isNotEmpty(request.realName())) {
            artist.setRealName(request.realName());
        }
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
        artist.setStatus(ArtistStatus.APPROVED);
        update(artist);
    }

    @Override
    public void rejectArtist(String alias) {
        Artist artist = getByAlias(alias);
        artist.setStatus(ArtistStatus.REJECTED);
        update(artist);
    }

    @Override
    public ArtistResponse getArtistById(UUID id) {
        return artistMapper.toDto(getById(id));
    }

    @Override
    public List<ArtistResponse> getAllArtists(Pageable pageable) {
        return artistMapper.toDto(getAll(pageable));
    }

    @Override
    public List<ArtistResponse> getAllArtists() {
        return artistMapper.toDto(getAll());
    }
}
