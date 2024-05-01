package zingmp3.hcmus.playlistservice.dto.record;

public record RemoveSongRequest(
        String playlistId,
        String songId
) {
}
