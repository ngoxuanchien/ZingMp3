package hcmus.zingmp3.service.image;

import hcmus.zingmp3.common.domain.exception.ResourceNotFoundException;
import hcmus.zingmp3.ImageRequestGrpc;
import hcmus.zingmp3.ImageResponseGrpc;
import hcmus.zingmp3.ImageServiceGrpc;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {

    @GrpcClient("image-service")
    ImageServiceGrpc.ImageServiceBlockingStub imageClient;

    @Override
    public boolean existsById(UUID uuid) {
        var request = ImageRequestGrpc
                .newBuilder()
                .setId(uuid.toString())
                .build();

        try {
            var response = imageClient.getById(request);
            if (response != null) {
                return true;
            }
        } catch (StatusRuntimeException sre) {
            if (sre.getStatus().getCode() == Status.NOT_FOUND.getCode()) {
                return false;
            }
            throw sre;
        }

        return false;
    }

    @Override
    public ImageResponseGrpc getById(UUID id) {
        try {
            var request = ImageRequestGrpc.newBuilder().setId(id.toString()).build();
            return imageClient.getById(request);
        } catch (StatusRuntimeException e) {
            if (e.getStatus().getCode() == Status.Code.NOT_FOUND) {
                throw new ResourceNotFoundException(e.getMessage());
            }
            throw e;
        }
    }
}
