package hcmus.zingmp3.core.service.image;

import hcmus.zingmp3.image.ImageGrpcResponse;

import java.util.UUID;

public interface ImageService {
    boolean existsById(UUID uuid);
    ImageGrpcResponse getById(UUID id);
}
