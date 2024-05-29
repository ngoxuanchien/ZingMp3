package hcmus.zingmp3.artist.mapper;

import hcmus.zingmp3.artist.dto.AwardRestRequest;
import hcmus.zingmp3.award.AwardGrpcRequest;
import hcmus.zingmp3.award.AwardGrpcResponse;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class AwardMapper {

    public AwardGrpcRequest toGrpc(AwardRestRequest dto) {
        return AwardGrpcRequest.newBuilder()
                .setName(dto.getName())
                .build();
    }

    public Set<UUID> toUUIDs(Set<AwardGrpcResponse> dtos) {
        return dtos.stream()
                .map(dto -> UUID.fromString(dto.getId()))
                .collect(Collectors.toSet());
    }
}
