package hcmus.zingmp3.service.song;

import hcmus.zingmp3.SongRequestGrpc;
import hcmus.zingmp3.SongResponseGrpc;
import hcmus.zingmp3.SongServiceGrpc;
import hcmus.zingmp3.web.dto.SongResponse;
import hcmus.zingmp3.web.dto.mapper.SongMapper;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {

    @GrpcClient("song-service")
    SongServiceGrpc.SongServiceBlockingStub songClient;

    private final SongMapper mapper;

    @Override
    public boolean isExist(UUID songId) {
        try {
            var request = SongRequestGrpc.newBuilder()
                    .setId(songId.toString())
                    .build();

            return songClient.getById(request)
                    .getId()
                    .equals(songId.toString());
        } catch (StatusRuntimeException sre) {
            if (sre.getStatus().getCode().equals(Status.Code.NOT_FOUND)) {
                return false;
            }
            return false;
        }
    }

    @Override
    public SongResponse getById(UUID songId) {
        try {
            var request = SongRequestGrpc.newBuilder()
                    .setId(songId.toString())
                    .build();

            return mapper.toDto(songClient.getById(request));
        } catch (StatusRuntimeException e) {
            return mapper.toDto(SongResponseGrpc.newBuilder().setId(songId.toString()).build());
        }
    }

    @Override
    public List<SongResponse> getAllById(List<UUID> songIds) {
        return songIds.stream().map(this::getById).toList();
    }
}
