package zingmp3.exception;


import lombok.AllArgsConstructor;

public class SongNotFoundException extends RuntimeException {
    public SongNotFoundException(String message) {
        super(message);
    }
}
