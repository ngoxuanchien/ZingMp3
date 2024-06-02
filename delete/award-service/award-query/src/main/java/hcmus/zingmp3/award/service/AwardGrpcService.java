package hcmus.zingmp3.award.service;

import hcmus.zingmp3.award.AwardGrpcRequest;
import hcmus.zingmp3.award.AwardGrpcResponse;
import hcmus.zingmp3.award.AwardServiceGrpc;
import hcmus.zingmp3.award.mapper.AwardGrpcMapper;
import hcmus.zingmp3.award.model.Award;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@Slf4j
@RequiredArgsConstructor
public class AwardGrpcService extends AwardServiceGrpc.AwardServiceImplBase {

    private final AwardService awardService;
    private final AwardGrpcMapper mapper;

    @Override
    public void getById(AwardGrpcRequest request, StreamObserver<AwardGrpcResponse> responseObserver) {
        Award award = awardService.getAwardById(request);
        responseObserver.onNext(mapper.toGrpc(award));
        responseObserver.onCompleted();
    }
}
