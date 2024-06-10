package zingmp3.web.dto.mapper;

import org.springframework.stereotype.Component;
import zingmp3.domain.model.Distributor;
import zingmp3.web.dto.DistributorRequest;

@Component
public class DistributorMapper {
    public Distributor toEntity(DistributorRequest request) {
        var distributor = Distributor.builder()
                .name(request.name())
                .address(request.address())
                .website(request.website())
                .timestamp(request.timestamp())
                .contract(request.contract())
                .build();

        distributor.setThumbnailId(request.thumbnailId());

        return distributor;
    }
}
