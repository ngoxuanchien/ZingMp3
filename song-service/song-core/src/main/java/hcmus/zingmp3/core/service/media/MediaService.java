package hcmus.zingmp3.core.service.media;

import hcmus.zingmp3.media.MediaGrpcResponse;

import java.util.UUID;

public interface MediaService {
    MediaGrpcResponse getById(UUID id);
    boolean isExists(UUID id);
}
