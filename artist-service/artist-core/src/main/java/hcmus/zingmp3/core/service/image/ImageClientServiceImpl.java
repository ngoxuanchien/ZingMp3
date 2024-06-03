package hcmus.zingmp3.core.service.image;

import hcmus.zingmp3.image.ImageGrpcRequest;
import hcmus.zingmp3.image.ImageGrpcResponse;
import hcmus.zingmp3.image.ImageServiceGrpc;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageClient {

    @GrpcClient("image-service")
    private ImageServiceGrpc.ImageServiceBlockingStub imageClient;

    public boolean checkImageExist(UUID imageId) {
        ImageGrpcResponse response;
        try {
            var request = ImageGrpcRequest.newBuilder().setId(imageId.toString()).build();

            response = imageClient.getImage(request);
        } catch (StatusRuntimeException sre) {
            if (sre.getStatus().getCode().equals(Status.Code.NOT_FOUND)) {
                return false;
            }
            throw sre;
        }
        return response != null;
    }
}
