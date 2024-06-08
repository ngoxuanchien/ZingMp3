package hcmus.zingmp3.core.service.image;

import hcmus.zingmp3.image.ImageResponseGrpc;

import java.util.UUID;

public interface ImageClientService {
    boolean isImageExist(UUID imageId);
    ImageResponseGrpc getImage(UUID imageId);
}
