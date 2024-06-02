package hcmus.zingmp3.producer;

import hcmus.zingmp3.artist.ArtistAvro;
import hcmus.zingmp3.artist.ArtistEventAvro;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class ArtistEventProducer {

    @Value("${topic.name}")
    private String topicName;

    private final KafkaTemplate<String, ArtistEventAvro> template;

    public void send(String key, ArtistEventAvro artistEvent) {
        CompletableFuture<SendResult<String, ArtistEventAvro>> future = template.send(topicName, key, artistEvent);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Sent message=[" + artistEvent + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message=[" + artistEvent + "] due to : " + ex.getMessage());
            }
        });
    }
}
