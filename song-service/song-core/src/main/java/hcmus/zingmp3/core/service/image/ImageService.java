package hcmus.zingmp3.core.service.image;

import hcmus.zingmp3.image.ImageResponseGrpc;

import java.util.UUID;

public interface ImageService {
    boolean existsById(UUID uuid);
    ImageResponseGrpc getById(UUID id);
}
