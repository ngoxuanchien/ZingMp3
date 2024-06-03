package hcmus.zingmp3.core.web.dto.mapper;

import hcmus.zingmp3.common.domain.exception.ResourceNotFoundException;
import hcmus.zingmp3.common.domain.model.Artist;
import hcmus.zingmp3.core.service.image.ImageClientService;
import hcmus.zingmp3.core.web.dto.ArtistRequest;
import hcmus.zingmp3.core.web.dto.ArtistResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ArtistMapper {
    private final ImageClientService imageClient;

    public ArtistResponse toDto(Artist artist) {
        String thumbnailUrl = imageClient
                .getImage(artist.getThumbnailId())
                .getUrl();

        return new ArtistResponse(
            artist.getId(),
            artist.getAlias(),
            thumbnailUrl,
            artist.getName(),
            artist.getRealName(),
            artist.getStatus(),
            artist.getUserId()
        );
    }

    public Artist toEntity(ArtistRequest request) {
        if (request == null) {
            return null;
        }

        return Artist.builder()
                .id(request.id())
                .alias(request.alias())
                .name(request.name())
                .realName(request.realName())
                .thumbnailId(request.thumbnailId())
                .build();
    }

    public List<ArtistResponse> toDto(List<Artist> artists) {
        return artists.stream().map(this::toDto).toList();
    }
}
