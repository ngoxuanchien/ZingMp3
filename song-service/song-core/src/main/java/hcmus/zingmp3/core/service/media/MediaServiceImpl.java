package hcmus.zingmp3.core.service.media;

import hcmus.zingmp3.common.domain.exception.ResourceNotFoundException;
import hcmus.zingmp3.media.MediaRequestGrpc;
import hcmus.zingmp3.media.MediaResponseGrpc;
import hcmus.zingmp3.media.MediaServiceGrpc;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {
    @GrpcClient("media-service")
    MediaServiceGrpc.MediaServiceBlockingStub mediaClient;

    @Override
    public MediaResponseGrpc getById(UUID id) {
        try {
            var request = MediaRequestGrpc.newBuilder().setId(id.toString()).build();
            return mediaClient.getById(request);
        } catch (StatusRuntimeException e) {
            if (e.getStatus().getCode() == Status.Code.NOT_FOUND) {
                throw new ResourceNotFoundException(e.getMessage());
            }
            throw e;
        }
    }

    @Override
    public boolean isExists(UUID id) {
        try {
            var request = MediaRequestGrpc.newBuilder().setId(id.toString()).build();
            return mediaClient.getById(request).getId().equals(id.toString());
        } catch (StatusRuntimeException e) {
            if (e.getStatus().getCode() == Status.Code.NOT_FOUND) {
                return false;
            }
            throw e;
        }
    }
}
