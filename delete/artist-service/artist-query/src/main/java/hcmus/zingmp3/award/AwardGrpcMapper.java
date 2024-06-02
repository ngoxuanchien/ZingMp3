package hcmus.zingmp3.award;

import hcmus.zingmp3.artist.service.AwardGrpcClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AwardGrpcMapper {
    private final AwardGrpcClient awardGrpcClient;
    public Set<AwardRestResponse> toDTO(Set<UUID> awards) {
        return awardGrpcClient.getByIds(awards)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toSet());
    }

    public AwardRestResponse toDTO(UUID id) {
        return toDTO(awardGrpcClient.getById(id));
    }

    public AwardRestResponse toDTO(AwardGrpcResponse response) {
        return AwardRestResponse.builder()
                .id(UUID.fromString(response.getId()))
                .name(response.getName())
                .build();
    }
}
