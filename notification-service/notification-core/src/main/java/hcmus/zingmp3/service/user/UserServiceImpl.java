package hcmus.zingmp3.service.user;

import hcmus.zingmp3.exception.ResourceNotFoundException;
import hcmus.zingmp3.UserRequestGrpc;
import hcmus.zingmp3.UserResponseGrpc;
import hcmus.zingmp3.UserServiceGrpc;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @GrpcClient("user-service")
    UserServiceGrpc.UserServiceBlockingStub userClient;

    @Override
    public UserResponseGrpc getById(UUID id) {
        try {
            return userClient.getById(UserRequestGrpc.newBuilder().setId(id.toString()).build());
        } catch (StatusRuntimeException e) {
            if (e.getStatus().getCode().equals(io.grpc.Status.Code.NOT_FOUND)) {
                throw new ResourceNotFoundException(
                        String.format("User with id %s not found", id.toString())
                );
            }
            throw e;
        }

//        return UserResponseGrpc.newBuilder().setId(id.toString()).setEmail("nxc.hcmus.me@gmail.com").build();
    }
}
