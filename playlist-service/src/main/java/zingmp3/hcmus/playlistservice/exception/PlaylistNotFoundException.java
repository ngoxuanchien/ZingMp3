package zingmp3.hcmus.playlistservice.exception;

public class PlaylistNotFoundException extends RuntimeException {
    public PlaylistNotFoundException(String id) {
        super("Playlist not found with id: " + id);
    }
}
