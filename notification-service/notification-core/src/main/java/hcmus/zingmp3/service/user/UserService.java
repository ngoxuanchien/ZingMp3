package hcmus.zingmp3.service.user;

import hcmus.zingmp3.user.UserResponseGrpc;

import java.util.UUID;

public interface UserService {
    UserResponseGrpc getById(UUID id);
}
