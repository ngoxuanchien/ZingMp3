package hcmus.zingmp3.core.service.media;

import hcmus.zingmp3.media.MediaResponseGrpc;

import java.util.UUID;

public interface MediaService {
    MediaResponseGrpc getById(UUID id);
    boolean isExists(UUID id);
}
