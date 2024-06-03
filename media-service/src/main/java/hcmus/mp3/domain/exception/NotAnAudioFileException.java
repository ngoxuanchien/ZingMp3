package hcmus.mp3.domain.exception;

public class NotAnAudioFileException extends RuntimeException {
    public NotAnAudioFileException(String message) {
        super(message);
    }
}
