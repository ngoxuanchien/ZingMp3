package hcmus.zingmp3.award.service;

import hcmus.zingmp3.award.AwardAvro;
import hcmus.zingmp3.award.AwardEvent;
import hcmus.zingmp3.award.mapper.AwardAvroMapper;
import hcmus.zingmp3.award.mapper.AwardMapper;
import hcmus.zingmp3.award.model.Award;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class AwardKafkaProducer {

    @Value("${topic.name}")
    private String topicName;

    private final AwardAvroMapper mapper;

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

    public void createAward(Award award) {
        var avro = mapper.toAvro(award);
        send(avro.getId().toString(),
                AwardEvent.newBuilder()
                    .setEventType("CreateAward")
                    .setAward(avro)
                    .build()
        );
    }

    public void updateAward(Award award) {
        var avro = mapper.toAvro(award);
        send(avro.getId().toString(),
                AwardEvent.newBuilder()
                    .setEventType("UpdateAward")
                    .setAward(avro)
                    .build()
        );
    }

    public void deleteAward(Award award) {
        var avro = mapper.toAvro(award);
        send(avro.getId().toString(),
                AwardEvent.newBuilder()
                    .setEventType("DeleteAward")
                    .setAward(avro)
                    .build()
        );
    }
}
