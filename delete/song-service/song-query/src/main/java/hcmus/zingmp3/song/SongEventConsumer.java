package hcmus.zingmp3.song;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SongEventConsumer {
    private final SongService songService;
    private final SongAvroMapper mapper;

    @RetryableTopic(attempts = "4")
    @KafkaListener(
            id = "song-consumer",
            topics = "${topic.name}"
    )
    public void processSongEvent(
            ConsumerRecord<String, SongEventAvro> record
    ) {
        String key = record.key();
        SongEventAvro event = record.value();
        String eventType = event.getEventType().toString();

        switch (eventType) {
            case "CreateSong":
                log.info("Create song: {}", event.getSong());
                songService.createSong(
                        mapper.toEntity(event.getSong()));
                break;
            case "UpdateSong":
                log.info("Update song: {}", event.getSong());
                songService.updateSong(
                        mapper.toEntity(event.getSong()));
                break;
            case "DeleteSong":
                log.info("Delete song: {}", event.getSong());
                songService.deleteSong(
                        mapper.toUUID(event.getSong().getId()));
                break;
        }
    }

    @DltHandler
    public void listenDlt(
            ConsumerRecord<String, SongEventAvro> record,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.OFFSET) long offset
    ) {
            log.info("DLT Received : {}, from {}, offset {}", record.key(), topic, offset);
    }
}
