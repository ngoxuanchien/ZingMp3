package hcmus.zingmp3.web.dto.mapper;

import hcmus.zingmp3.ArtistResponseGrpc;
import hcmus.zingmp3.web.dto.ArtistResponse;
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
