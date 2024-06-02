package hcmus.zingmp3.artist.service;

import hcmus.zingmp3.artist.ArtistEventAvro;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.*;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ArtistKafkaConsumer {


    private final ArtistService artistService;

    @RetryableTopic(attempts = "4")
    @KafkaListener(
            id = "artist-award-consumer",
            topics = "${topic.name}"
    )
    public void processArtistEvent(ConsumerRecord<String, ArtistEventAvro> record) {
        String key = record.key();
        ArtistEventAvro event = record.value();
        switch (event.getEventType().toString()) {
            case "CreateArtist":
                log.info("Create artist: {}", event.getArtist());
                artistService.createArtist(event.getArtist());
                break;
            case "UpdateArtist":
                log.info("Update artist: {}", event.getArtist());
                artistService.updateArtist(event.getArtist());
                break;
            case "DeleteArtist":
                log.info("Delete artist: {}", event.getArtist());
                artistService.deleteArtist(event.getArtist().getId().toString());
                break;
        }
    }

    @DltHandler
    public void listenDlt(
            ConsumerRecord<String, ArtistEventAvro> record,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.OFFSET) long offset
    ) {
        log.info("DLT Received : {}, from {}, offset {}", record.key(), topic, offset);
    }


}
