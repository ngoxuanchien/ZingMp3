package hcmus.zingmp3.award.mapper;

import hcmus.zingmp3.award.AwardAvro;
import hcmus.zingmp3.award.AwardGrpcRequest;
import hcmus.zingmp3.award.AwardGrpcResponse;
import hcmus.zingmp3.award.dto.*;
import hcmus.zingmp3.award.model.Award;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AwardMapper {

    public Award toEntity(AwardRestRequest request) {
        if (request == null) {
            return null;
        }

        return Award.builder()
                .name(request.getName())
                .artistIds(request.getArtists())
                .build();
    }

    public AwardRestResponse toDTO(Award award) {
        if (award == null) {
            return null;
        }

        return AwardRestResponse.builder()
                .id(award.getId())
                .name(award.getName())
                .artists(award.getArtistIds())
                .build();
    }






}
