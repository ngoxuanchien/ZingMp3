package hcmus.zingmp3.award;

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
public class AwardKafkaConsumer {

    private final ArtistService artistService;

    @RetryableTopic(attempts = "4")
    @KafkaListener(
            id = "artist-award",
            topics = "award-service"
    )
    public void processAwardEvent(AwardEvent awardEvent) {
        log.info("Received award event: {}", awardEvent);
        String eventType = awardEvent.getEventType().toString();
        AwardAvro award = awardEvent.getAward();

        switch (eventType) {
            case "CreateAward":
                log.info("Create award: {}", awardEvent.getAward());
                updateArtist(award.getArtists(), toUUID(award.getId()));
                break;
            case "UpdateAward":
                log.info("Update award: {}", awardEvent.getAward());
                updateArtist(award.getArtists(), toUUID(award.getId()));
                break;
            case "DeleteAward":
                log.info("Delete award: {}", awardEvent.getAward());
                removeAward(awardEvent);
                break;
        }
    }

    @DltHandler
    public void handleDlt(AwardEvent awardEvent) {
        log.error("Award event processing failed: {}", awardEvent);
    }

    private void removeAward(AwardEvent awardEvent) {
        if (awardEvent.getAward().getArtists() == null ||
                awardEvent.getAward().getArtists().isEmpty()) {
            return;
        }

        UUID awardId = toUUID(awardEvent.getAward().getId());
        Set<UUID> artistIds = awardEvent.getAward()
                .getArtists()
                .stream()
                .map(this::toUUID)
                .collect(Collectors.toSet());

        artistService.removeAward(artistIds, awardId);
    }

    void updateArtist(List<CharSequence> artists, UUID awardId) {
        if (artists == null || awardId == null) {
            return;
        }

        Set<UUID> artistIds = artists.stream()
                .map(this::toUUID)
                .collect(Collectors.toSet());

        artistService.updateAward(artistIds, awardId);
    }

    private UUID toUUID(CharSequence id) {
        return UUID.fromString(id.toString());
    }

}
