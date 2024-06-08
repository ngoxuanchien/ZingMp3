package hcmus.zingmp3.service.song;

import hcmus.zingmp3.song.SongRequestGrpc;
import hcmus.zingmp3.song.SongServiceGrpc;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SongServiceImpl implements SongService {

    @GrpcClient("song-service")
    SongServiceGrpc.SongServiceBlockingStub songClient;

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
            throw sre;
        }
    }
}
