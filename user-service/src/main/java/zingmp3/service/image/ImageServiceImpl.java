package zingmp3.service.image;

import hcmus.zingmp3.ImageRequestGrpc;
import hcmus.zingmp3.ImageServiceGrpc;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {

    @GrpcClient("image-service")
    ImageServiceGrpc.ImageServiceBlockingStub imageClient;

    @Override
    public boolean existsById(UUID imageId) {
        try {
            var request = ImageRequestGrpc.newBuilder()
                    .setId(imageId.toString())
                    .build();
            return imageClient.getById(request)
                    .getId()
                    .equals(imageId.toString());
        } catch (StatusRuntimeException sre) {
            if (sre.getStatus().getCode().equals(io.grpc.Status.Code.NOT_FOUND)) {
                return false;
            }
            throw sre;
        }
    }
}
