package hcmus.zingmp3.common.domain.exception;

public class ResourceAlreadyExistsException extends RuntimeException {
    public ResourceAlreadyExistsException(
            final String message
    ) {
        super(message);
    }
}
