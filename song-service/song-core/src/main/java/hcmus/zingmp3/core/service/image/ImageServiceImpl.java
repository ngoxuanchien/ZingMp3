package hcmus.zingmp3.core.service.image;

import hcmus.zingmp3.common.domain.exception.ResourceNotFoundException;
import hcmus.zingmp3.image.ImageGrpcRequest;
import hcmus.zingmp3.image.ImageGrpcResponse;
import hcmus.zingmp3.image.ImageServiceGrpc;
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
    public boolean isExist(UUID uuid) {
        var request = ImageGrpcRequest
                .newBuilder()
                .setId(uuid.toString())
                .build();

        try {
            var response = imageClient.getImage(request);
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
    public ImageGrpcResponse getById(UUID id) {
        try {
            var request = ImageGrpcRequest.newBuilder().setId(id.toString()).build();
            return imageClient.getImage(request);
        } catch (StatusRuntimeException e) {
            if (e.getStatus().getCode() == Status.Code.NOT_FOUND) {
                throw new ResourceNotFoundException(e.getMessage());
            }
            throw e;
        }
    }
}
