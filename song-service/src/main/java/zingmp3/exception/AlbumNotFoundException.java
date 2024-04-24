package zingmp3.exception;

public class AlbumNotFoundException extends RuntimeException {
    public AlbumNotFoundException(String message) {
        super(message);
    }
}
