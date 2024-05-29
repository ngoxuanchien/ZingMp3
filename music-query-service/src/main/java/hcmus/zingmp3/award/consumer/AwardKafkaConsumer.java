package hcmus.zingmp3.award.consumer;

import hcmus.zingmp3.award.AwardService;
import hcmus.zingmp3.award.model.AwardEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class AwardKafkaConsumer {

    private final AwardService awardService;

    @KafkaListener(topics = "${topic.name}")
    public void processAwardEvent(ConsumerRecord<String, AwardEvent> consumerRecord) {
        String key = consumerRecord.key();
        AwardEvent event = consumerRecord.value();
        switch (event.getEventType().toString()) {
            case "CreateAward":
                log.info("Create award: {}", event.getAward());
                awardService.createAward(event.getAward());
                break;
            case "UpdateAward":
                log.info("Update award: {}", event.getAward());
                awardService.updateAward(event.getAward());
                break;
            case "DeleteAward":
                log.info("Delete award: {}", event.getAward());
                awardService.deleteAward(event.getAward().getId().toString());
                break;
        }
    }
}
