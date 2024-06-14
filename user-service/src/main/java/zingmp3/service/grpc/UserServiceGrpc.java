package zingmp3.service.grpc;

import hcmus.zingmp3.UserRequestGrpc;
import hcmus.zingmp3.UserResponseGrpc;
import hcmus.zingmp3.UserServiceGrpc.UserServiceImplBase;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import zingmp3.service.user.UserService;

@GrpcService
@RequiredArgsConstructor
public class UserServiceGrpc extends UserServiceImplBase {

    private final UserService userService;

    @Override
    public void getById(UserRequestGrpc request, StreamObserver<UserResponseGrpc> responseObserver) {
        try {
            var userResource = userService.getById(request.getId());

            userResource.getEmail();
            responseObserver.onNext(UserResponseGrpc.newBuilder()
                                            .setId(userResource.getId())
                                            .setEmail(userResource.getEmail())
                                            .build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }
}
