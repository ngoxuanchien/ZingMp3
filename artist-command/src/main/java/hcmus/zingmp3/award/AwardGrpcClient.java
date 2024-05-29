package hcmus.zingmp3.artist.service;


import hcmus.zingmp3.artist.dto.AwardRestRequest;
import hcmus.zingmp3.artist.mapper.AwardMapper;
import hcmus.zingmp3.award.AwardGrpcRequest;
import hcmus.zingmp3.award.AwardGrpcResponse;
import hcmus.zingmp3.award.AwardServiceGrpc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AwardGrpcClient {
    private final AwardMapper awardMapper;
    @GrpcClient(value = "award-command")
    AwardServiceGrpc.AwardServiceBlockingStub awardClient;

    public AwardGrpcResponse getOrCreateAward(final AwardGrpcRequest awardGrpcRequest) {
        AwardGrpcResponse response;
        try {
            response = awardClient.getOrCreateAward(awardGrpcRequest);
        } catch (Exception e) {
            log.error("Error while getting or creating award: {}", awardGrpcRequest, e);
            return null;
        }
        return response;
    }

    public Set<UUID> getOrCreateAwards(final Set<AwardRestRequest> awardRestRequests) {
        return awardRestRequests.stream()
                .map(awardMapper::toGrpc)
                .map(this::getOrCreateAward)
                .map(awardGrpcResponse -> UUID.fromString(awardGrpcResponse.getId()))
                .collect(Collectors.toSet());
    }
}
