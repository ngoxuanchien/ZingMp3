package zingmp3.service.producer;

import hcmus.zingmp3.notification.domain.events.AbstractNotificationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class KafkaProducer {

    @Value("notification-service")
    private String topicName;

    private final KafkaTemplate<String, AbstractNotificationEvent> template;

    public void send(AbstractNotificationEvent event) {
        CompletableFuture<SendResult<String, AbstractNotificationEvent>> future = template.send(topicName, event);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Sent message=[" + event + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message=[" + event + "] due to : " + ex.getMessage());
            }
        });
    }
}
