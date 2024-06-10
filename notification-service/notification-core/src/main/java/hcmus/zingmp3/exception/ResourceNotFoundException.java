package hcmus.zingmp3.exception;

import io.grpc.StatusRuntimeException;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
