package hcmus.zingmp3.core.service.grpc;

import hcmus.zingmp3.common.domain.exception.ResourceNotFoundException;
import hcmus.zingmp3.common.service.song.SongQueryService;
import hcmus.zingmp3.core.web.dto.mapper.SongMapperGrpc;
import hcmus.zingmp3.song.SongRequestGrpc;
import hcmus.zingmp3.song.SongResponseGrpc;
import hcmus.zingmp3.song.SongServiceGrpc.*;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.UUID;

@GrpcService
@RequiredArgsConstructor
public class SongServiceGrpc extends SongServiceImplBase {

    private final SongQueryService queryService;
    private final SongMapperGrpc mapper;

    @Override
    public void getById(SongRequestGrpc request, StreamObserver<SongResponseGrpc> responseObserver) {
        try {
            var song = queryService.getById(UUID.fromString(request.getId()));
            var response = mapper.toDto(song);
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (ResourceNotFoundException e) {
            Status status = Status.NOT_FOUND.withDescription(e.getMessage());
            responseObserver.onError(status.asRuntimeException());
        }
    }
}
