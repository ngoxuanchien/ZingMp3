package hcmus.zingmp3.award.producer;

import hcmus.zingmp3.award.model.AwardAvro;
import hcmus.zingmp3.award.model.AwardEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class AwardKafkaProducer {

    @Value("${topic.name}")
    private String topicName;

    private final KafkaTemplate<String, AwardEvent> template;

    public void send(String key, AwardEvent award) {
        CompletableFuture<SendResult<String, AwardEvent>> future = template.send(topicName, key, award);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Sent message=[" + award + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message=[" + award + "] due to : " + ex.getMessage());
            }
        });
    }

    public void createAward(AwardAvro award) {
        AwardEvent awardEvent = AwardEvent.newBuilder()
                .setEventType("CreateAward")
                .setAward(award)
                .build();
        send(award.getId().toString(), awardEvent);
    }

    public void updateAward(AwardAvro award) {
        AwardEvent awardEvent = AwardEvent.newBuilder()
                .setEventType("UpdateAward")
                .setAward(award)
                .build();
        send(award.getId().toString(), awardEvent);
    }

    public void deleteAward(AwardAvro award) {
        AwardEvent awardEvent = AwardEvent.newBuilder()
                .setEventType("DeleteAward")
                .setAward(award)
                .build();
        send(award.getId().toString(), awardEvent);
    }
}
