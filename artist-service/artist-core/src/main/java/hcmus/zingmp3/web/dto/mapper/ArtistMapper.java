package hcmus.zingmp3.web.dto.mapper;

import hcmus.zingmp3.common.domain.model.Artist;
import hcmus.zingmp3.service.image.ImageService;
import hcmus.zingmp3.web.dto.ArtistRequest;
import hcmus.zingmp3.web.dto.ArtistResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ArtistMapper {
    private final ImageService imageService;

    public ArtistResponse toDto(Artist artist) {
        if (artist == null) {
            return null;
        }

        return new ArtistResponse(
            artist.getId(),
            artist.getAlias(),
            imageService.getImage(artist.getThumbnailId()).getUrl(),
            artist.getName(),
            artist.getRealName(),
            artist.getStatus()
        );
    }

    public Artist toEntity(ArtistRequest request) {
        if (request == null) {
            return null;
        }

        return Artist.builder()
                .id(request.id())
                .alias(request.alias())
                .thumbnailId(request.thumbnailId())
                .name(request.name())
                .realName(request.realName())
                .build();
    }

    public List<ArtistResponse> toDto(List<Artist> artists) {
        return artists.stream().map(this::toDto).toList();
    }

    public Page<ArtistResponse> toDto(Page<Artist> artists) {
        return artists.map(this::toDto);
    }
}
