package hcmus.zingmp3.award.consumer;

import hcmus.zingmp3.award.AwardService;
import hcmus.zingmp3.award.model.AwardEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@KafkaListener(
        id = "award-consumer",
        topics = "${topic.name}",
        topicPartitions = @TopicPartition(
                topic = "${topic.name}",
                partitionOffsets = {
                        @PartitionOffset(partition = "0", initialOffset = "0"),
                        @PartitionOffset(partition = "1", initialOffset = "0"),
                        @PartitionOffset(partition = "2", initialOffset = "0")
                }
        )
)
public class AwardKafkaConsumer {

    private final AwardService awardService;

    @KafkaHandler(isDefault = true)
    public void listen(String message) {
        log.info("Received message: {}", message);
    }

    @KafkaHandler
    public void processAwardEvent(AwardEvent event) {
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
