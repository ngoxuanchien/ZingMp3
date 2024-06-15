package hcmus.zingmp3.service.user;

import hcmus.zingmp3.dto.User;

public interface UserService {

    User getAccessToken(String username, String password);

    void logout();
}
