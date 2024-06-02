package hcmus.zingmp3.song;

import hcmus.zingmp3.artist.ArtistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class SongEventConsumer {
    private final ArtistService artistService;

    @DltHandler
    public void handleDlt(SongEventAvro songEvent) {
        log.error("Song event processing failed: {}", songEvent);
    }

    @RetryableTopic(attempts = "4")
    @KafkaListener(
            id = "artist-song",
            topics = "song-service"
    )
    public void processSongEvent(SongEventAvro songEvent) {
        log.info("Received song event: {}", songEvent);
        String eventType = songEvent.getEventType().toString();

        switch (eventType) {
            case "CreateSong":
                log.info("Create song: {}", songEvent.getSong());
                updateSong(songEvent);
                updateComposerSong(songEvent);
                break;
            case "UpdateSong":
                log.info("Update song: {}", songEvent.getSong());
                updateSong(songEvent);
                updateComposerSong(songEvent);
                break;
            case "DeleteSong":
                log.info("Delete song: {}", songEvent.getSong());
                removeSong(songEvent);
                removeComposerSong(songEvent);
                break;
        }
    }

    private void updateComposerSong(SongEventAvro songEvent) {
        if (songEvent.getSong().getArtists() == null ||
                songEvent.getSong().getArtists().isEmpty()) {
            return;
        }

        UUID songId = toUUID(songEvent.getSong().getId());
        Set<UUID> composerIds = songEvent.getSong()
                .getComposers()
                .stream()
                .map(this::toUUID)
                .collect(Collectors.toSet());

        artistService.updateComposerSong(composerIds, songId);
    }

    private void removeComposerSong(SongEventAvro songEvent) {
        if (songEvent.getSong().getArtists() == null ||
                songEvent.getSong().getArtists().isEmpty()) {
            return;
        }

        UUID songId = toUUID(songEvent.getSong().getId());
        Set<UUID> composerIds = songEvent.getSong()
                .getComposers()
                .stream()
                .map(this::toUUID)
                .collect(Collectors.toSet());

        artistService.removeComposerSong(composerIds, songId);
    }




    private void removeSong(SongEventAvro songEvent) {
        if (songEvent.getSong().getArtists() == null ||
                songEvent.getSong().getArtists().isEmpty()) {
            return;
        }

        UUID songId = toUUID(songEvent.getSong().getId());
        Set<UUID> artistIds = songEvent.getSong()
                .getArtists()
                .stream()
                .map(this::toUUID)
                .collect(Collectors.toSet());

        artistService.removeSong(artistIds, songId);
    }

    private void updateSong(SongEventAvro songEvent) {
        if (songEvent.getSong().getArtists() == null ||
                songEvent.getSong().getArtists().isEmpty()) {
            return;
        }

        UUID songId = toUUID(songEvent.getSong().getId());
        Set<UUID> artistIds = songEvent.getSong()
                .getArtists()
                .stream()
                .map(this::toUUID)
                .collect(Collectors.toSet());

        artistService.updateSong(artistIds, songId);
    }

    private UUID toUUID(CharSequence id) {
        return UUID.fromString(id.toString());
    }

}
