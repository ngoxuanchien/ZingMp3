package hcmus.zingmp3.artist;

import hcmus.zingmp3.award.model.Award;
import hcmus.zingmp3.award.service.AwardService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArtistKafkaConsumer {

    private final AwardService awardService;

    @RetryableTopic(attempts = "4")
    @KafkaListener(
            id = "award-event-consumer",
            topics = "artist-service"
    )
    public void consumeArtistEvent(ArtistEventAvro artistEvent) {
        String eventType = artistEvent.getEventType().toString();
        ArtistAvro artist = artistEvent.getArtist();
        switch (eventType) {
            case "CreateArtist":
                System.out.println("Create artist: " + artist);
                updateAward(artistEvent);
                break;
            case "UpdateArtist":
                System.out.println("Update artist: " + artist);
                updateAward(artistEvent);
                break;
            case "DeleteArtist":
                System.out.println("Delete artist: " + artist);
                removeArtist(artistEvent);
                break;
        }
    }

    private void removeArtist(ArtistEventAvro artistEvent) {
        if (artistEvent.getArtist().getAwards() == null) {
            return;
        }

        UUID artistId = UUID.fromString(artistEvent.getArtist().getId().toString());
        Set<UUID> awardIds = artistEvent.getArtist()
                .getAwards()
                .stream()
                .map(awardId -> UUID.fromString(awardId.toString()))
                .collect(Collectors.toSet());

        awardService.removeArtist(artistId, awardIds);
    }

    private void updateAward(ArtistEventAvro artistEvent) {
        if (artistEvent.getArtist().getAwards() == null) {
            return;
        }

        UUID artistId = UUID.fromString(artistEvent.getArtist().getId().toString());
        Set<UUID> awardIds = artistEvent.getArtist()
                .getAwards()
                .stream()
                .map(awardId -> UUID.fromString(awardId.toString()))
                .collect(Collectors.toSet());

        awardService.updateAward(artistId, awardIds);


    }

    @DltHandler
    public void handleDlt(ArtistEventAvro artistEvent) {
        System.out.println("Handling dlt: " + artistEvent);
    }
}
