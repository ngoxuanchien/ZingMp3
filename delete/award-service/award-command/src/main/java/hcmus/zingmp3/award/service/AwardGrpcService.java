package hcmus.zingmp3.award.service;

import hcmus.zingmp3.award.AwardGrpcRequest;
import hcmus.zingmp3.award.AwardGrpcResponse;
import hcmus.zingmp3.award.AwardServiceGrpc;
import hcmus.zingmp3.award.mapper.AwardProtoMapper;
import hcmus.zingmp3.award.model.Award;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class AwardGrpcService extends AwardServiceGrpc.AwardServiceImplBase {
    private final AwardProtoMapper mapper;
    private final AwardService awardService;

    @Override
    public void getOrCreateAward(AwardGrpcRequest request, StreamObserver<AwardGrpcResponse> responseObserver) {
        Award award = awardService.getOrCreateAward(mapper.toEntity(request));
        responseObserver.onNext(mapper.toProto(award));
        responseObserver.onCompleted();
    }
}
