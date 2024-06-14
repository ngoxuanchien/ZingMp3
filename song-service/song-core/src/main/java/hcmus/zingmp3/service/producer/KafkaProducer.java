package hcmus.zingmp3.service.producer;

import hcmus.zingmp3.common.events.AbstractEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class KafkaProducer {

    @Value("${kafka.topic.name}")
    private String topicName;

    private final KafkaTemplate<String, AbstractEvent> template;

    public void send(AbstractEvent event) {
        CompletableFuture<SendResult<String, AbstractEvent>> future = template.send(topicName, event);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Sent message=[" + event + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message=[" + event + "] due to : " + ex.getMessage());
            }
        });
    }
}
