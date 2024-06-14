package hcmus.zingmp3.service.image;

import hcmus.zingmp3.ImageResponseGrpc;

import java.util.UUID;

public interface ImageService {
    boolean existsById(UUID imageId);
    ImageResponseGrpc getImage(UUID imageId);
}
