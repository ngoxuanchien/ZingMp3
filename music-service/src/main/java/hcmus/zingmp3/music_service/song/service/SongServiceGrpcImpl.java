package hcmus.zingmp3.music_service.song.service;

import hcmus.zingmp3.music_service.SongAlias;
import hcmus.zingmp3.music_service.SongId;
import hcmus.zingmp3.music_service.SongResponse;
import hcmus.zingmp3.music_service.SongServiceGrpc;
import hcmus.zingmp3.music_service.song.SongMapper;
import hcmus.zingmp3.music_service.song.SongRepository;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.UUID;

@GrpcService
@RequiredArgsConstructor
public class SongServiceGrpcImpl extends SongServiceGrpc.SongServiceImplBase {
    private final SongRepository songRepository;
    private final SongMapper songMapper;

    @Override
    public void getSongById(SongId request, StreamObserver<SongResponse> responseObserver) {
        songRepository
                .findById(UUID.fromString(request.getId()))
                .ifPresentOrElse(
                        song -> responseObserver.onNext(songMapper.toResponse(song)),
                        () -> responseObserver.onError(io.grpc.Status.NOT_FOUND
                                .withDescription("Song not found")
                                .asRuntimeException()));
        responseObserver.onCompleted();
    }

    @Override
    public void getSongIdByAlias(SongAlias request, StreamObserver<SongId> responseObserver) {
        songRepository
                .findByAlias(request.getAlias())
                .ifPresentOrElse(
                        song -> responseObserver.onNext(songMapper.toSongId(song)),
                        () -> responseObserver.onError(io.grpc.Status.NOT_FOUND
                                .withDescription("Song not found")
                                .asRuntimeException()));
        responseObserver.onCompleted();
    }
}
