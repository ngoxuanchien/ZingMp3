package hcmus.zingmp3.service.media;

import hcmus.zingmp3.MediaResponseGrpc;

import java.util.UUID;

public interface MediaService {
    MediaResponseGrpc getById(UUID id);
    boolean isExists(UUID id);
}
