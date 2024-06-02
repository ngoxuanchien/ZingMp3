package hcmus.zingmp3.artist.service;

import com.google.protobuf.Descriptors;
import hcmus.zingmp3.award.AwardGrpcRequest;
import hcmus.zingmp3.award.AwardGrpcResponse;
import hcmus.zingmp3.award.AwardRestResponse;
import hcmus.zingmp3.award.AwardServiceGrpc;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AwardGrpcClient {
    @GrpcClient(value = "award-query")
    AwardServiceGrpc.AwardServiceBlockingStub awardClient;

    public AwardGrpcResponse getById(UUID id) {
        AwardGrpcResponse response;
        try {
            var request = AwardGrpcRequest.newBuilder()
                    .setId(id.toString())
                    .build();

            response = awardClient.getById(request);
        } catch (StatusRuntimeException sre) {
            if (sre.getStatus().getCode() == Status.Code.UNAVAILABLE) {
                return AwardGrpcResponse.newBuilder()
                        .setId(id.toString())
                        .build();
            }

            throw sre;
        }

        return response;
    }

    public Set<AwardGrpcResponse> getByIds(Set<UUID> ids) {
        return ids.stream()
                .map(this::getById)
                .collect(Collectors.toSet());
    }
}
