package zingmp3.exception;

import jakarta.ws.rs.NotAuthorizedException;

public class UnauthorizedException extends NotAuthorizedException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
