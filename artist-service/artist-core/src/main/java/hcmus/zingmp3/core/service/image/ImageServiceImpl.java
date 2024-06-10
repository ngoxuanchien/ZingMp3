package hcmus.zingmp3.core.service.image;

import hcmus.zingmp3.image.ImageRequestGrpc;
import hcmus.zingmp3.image.ImageResponseGrpc;
import hcmus.zingmp3.image.ImageServiceGrpc;
import io.grpc.StatusRuntimeException;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static io.grpc.Status.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    @GrpcClient("image-service")
    ImageServiceGrpc.ImageServiceBlockingStub imageClient;

    @Override
    public boolean existsById(UUID imageId) {
        try {
            var request = ImageRequestGrpc
                    .newBuilder()
                    .setId(imageId.toString())
                    .build();

            return imageClient.getById(request)
                    .getId()
                    .equals(imageId.toString());
        } catch (StatusRuntimeException sre) {
            if (sre.getStatus().getCode().equals(NOT_FOUND.getCode())) {
                return false;
            }
            throw sre;
        }
    }

    @Override
    public ImageResponseGrpc getImage(UUID imageId) {
        // todo: image client get image
        var request = ImageRequestGrpc.newBuilder()
                .setId(imageId.toString())
                .build();
        return imageClient.getById(request);
    }
}
