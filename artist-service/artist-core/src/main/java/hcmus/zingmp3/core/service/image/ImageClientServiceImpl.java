package hcmus.zingmp3.core.service.image;

import hcmus.zingmp3.image.ImageGrpcRequest;
import hcmus.zingmp3.image.ImageGrpcResponse;
import hcmus.zingmp3.image.ImageServiceGrpc;
import io.grpc.StatusRuntimeException;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static io.grpc.Status.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ImageClientServiceImpl implements ImageClientService {

    @GrpcClient("image-service")
    ImageServiceGrpc.ImageServiceBlockingStub imageClient;

    @Override
    public boolean isImageExist(UUID imageId) {
        try {
            var request = ImageGrpcRequest
                    .newBuilder()
                    .setId(imageId.toString())
                    .build();

            imageClient.getImage(request);
            return true;
        } catch (StatusRuntimeException sre) {
            if (sre.getStatus().getCode().equals(NOT_FOUND.getCode())) {
                return false;
            }
            throw sre;
        }
    }

    @Override
    public ImageGrpcResponse getImage(UUID imageId) {
        // todo: image client get image
        return ImageGrpcResponse.newBuilder()
                .setId(imageId.toString())
                .setUrl("http://localhost:8080/image/" + imageId.toString())
                .build();
    }
}
