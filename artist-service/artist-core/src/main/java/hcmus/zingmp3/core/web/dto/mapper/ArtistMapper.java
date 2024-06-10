package hcmus.zingmp3.core.web.dto.mapper;

import hcmus.zingmp3.common.domain.model.Artist;
import hcmus.zingmp3.core.service.image.ImageService;
import hcmus.zingmp3.core.web.dto.ArtistRequest;
import hcmus.zingmp3.core.web.dto.ArtistResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ArtistMapper {
    private final ImageService imageClient;

    public ArtistResponse toDto(Artist artist) {
        if (artist == null) {
            return null;
        }

        return new ArtistResponse(
            artist.getId(),
            artist.getAlias(),
            artist.getThumbnailId(),
            artist.getName(),
            artist.getRealName(),
            artist.getStatus(),
            artist.getCreateBy()
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
}
