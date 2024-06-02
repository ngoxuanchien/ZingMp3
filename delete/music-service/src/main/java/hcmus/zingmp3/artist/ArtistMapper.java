package hcmus.zingmp3.artist;

import hcmus.zingmp3.artist.model.Artist;
import hcmus.zingmp3.artist.model.ArtistRequest;
import hcmus.zingmp3.artist.model.ArtistResponse;
import hcmus.zingmp3.award.AwardService;
import hcmus.zingmp3.award.model.Award;
import hcmus.zingmp3.artist.model.Artist;
import hcmus.zingmp3.artist.model.ArtistRequest;
import hcmus.zingmp3.artist.model.ArtistResponse;
import hcmus.zingmp3.award.model.Award;
import hcmus.zingmp3.award.AwardService;
import hcmus.zingmp3.song.model.Song;
import hcmus.zingmp3.song.model.SongResponse;
import hcmus.zingmp3.thumbnail.ThumbnailClientService;
import hcmus.zingmp3.song.model.Song;
import hcmus.zingmp3.song.model.SongResponse;
import hcmus.zingmp3.thumbnail.ThumbnailClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
class ArtistMapper {
    private final static UUID DEFAULT_THUMBNAIL_ID = UUID.fromString("00000000-0000-0000-0000-000000000000");
    private final AwardService awardService;
    private final ThumbnailClientService thumbnailClientService;

    public ArtistResponse toDTO(Artist entity) {
        ArtistResponse dto = new ArtistResponse();
        BeanUtils.copyProperties(entity, dto);

        // Todo get image url from image service
        dto.setBirthday(entity.getBirthday() == null ? null : entity.getBirthday().toString());
        dto.setThumbnail(thumbnailClientService.getById(entity.getThumbnail()).getUrl());
        dto.setSongs(entity.getSongs().stream().map(this::toDTO).collect(Collectors.toSet()));
        dto.setAwards(entity.getAwards().stream().map(Award::getName).collect(Collectors.toSet()));

        return dto;
    }

    private SongResponse toDTO(Song entity) {
        System.out.println(entity);
        SongResponse dto = new SongResponse();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setAlias(entity.getAlias());
        dto.setArtists(entity.getArtists().stream().map(this::toDTO).collect(Collectors.toSet()));
        dto.setThumbnail(thumbnailClientService.getById(entity.getThumbnail()).getUrl());
        return dto;
    }

    private UUID getOrCreateThumbnailIfNotExist(MultipartFile file) {
        if (file == null) {
            return DEFAULT_THUMBNAIL_ID;
        }

        return UUID.fromString(thumbnailClientService.getOrCreateIfNotExist(file, "artist").getId());
    }

    public Artist toEntity(ArtistRequest dto) {
        Artist entity = new Artist();
        BeanUtils.copyProperties(dto, entity);

        // get or create thumbnail, awards
        entity.setThumbnail(getOrCreateThumbnailIfNotExist(dto.getThumbnailFile()));
        entity.setAwards(getOrCreateAwardIfNotExist(dto.getAwards()));
//        entity.setSongs(getSong(dto.getSongs()));

        return entity;
    }

    private List<Award> getOrCreateAwardIfNotExist(Set<String> newAwards) {
        if (newAwards == null) {
            return List.of();
        }

        return newAwards.stream().map(awardService::getOrCreateByName).toList();
    }

    public List<ArtistResponse> toDTO(List<Artist> entities) {
        return entities.stream().map(this::toDTO).toList();
    }

    public Set<Artist> toEntity(Set<ArtistRequest> requests) {
        if (requests == null) {
            return new HashSet<>();
        }

        return requests.stream().map(this::toEntity).collect(Collectors.toSet());
    }
}

