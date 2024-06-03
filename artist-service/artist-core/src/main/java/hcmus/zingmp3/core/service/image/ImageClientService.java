package hcmus.zingmp3.core.service.image;

import hcmus.zingmp3.image.ImageGrpcResponse;

import java.util.UUID;

public interface ImageClientService {
    boolean isImageExist(UUID imageId);
    ImageGrpcResponse getImage(UUID imageId);
}
