package hcmus.zingmp3.award.service;

import hcmus.zingmp3.award.AwardAvro;
import hcmus.zingmp3.award.AwardEvent;
import hcmus.zingmp3.award.mapper.AwardAvroMapper;
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
public class AwardKafkaConsumer {

    private final AwardService awardService;
    private final AwardAvroMapper mapper;

    @RetryableTopic(attempts = "4")
    @KafkaListener(
            id = "award-event-consumer",
            topics = "${topic.name}"
    )
    public void processAwardEvent(
            ConsumerRecord<String, AwardEvent> record
    ) {
        String key = record.key();
        AwardEvent event = record.value();

        switch (event.getEventType().toString()) {
            case "CreateAward":
                log.info("Create award: {}", event.getAward());
                awardService.createAward(mapper.toEntity(event.getAward()));
                break;
            case "UpdateAward":
                log.info("Update award: {}", event.getAward());
                awardService.updateAward(mapper.toEntity(event.getAward()));
                break;
            case "DeleteAward":
                log.info("Delete award: {}", event.getAward());
                awardService.deleteAward(mapper.toUUID(event.getAward().getId()));
                break;
        }

    }

    @DltHandler
    public void listenDlt(
            ConsumerRecord<String, AwardEvent> record,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.OFFSET) long offset) {
        log.info("DLT Received : {}, from {}, offset {}", record.key(), topic, offset);
    }
}
