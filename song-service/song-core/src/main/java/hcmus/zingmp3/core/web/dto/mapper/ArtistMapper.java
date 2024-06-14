package hcmus.zingmp3.core.web.dto.mapper;

import hcmus.zingmp3.artist.ArtistResponseGrpc;
import hcmus.zingmp3.core.web.dto.ArtistResponse;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ArtistMapper {
    public ArtistResponse toDto(ArtistResponseGrpc response) {
        return new ArtistResponse(
                UUID.fromString(response.getId()),
                response.getAlias(),
                response.getName(),
                response.getThumbnail(),
                response.getRealName()
        );
    }
}
