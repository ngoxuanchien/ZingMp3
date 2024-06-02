package hcmus.zingmp3.producer;

import hcmus.zingmp3.genre.GenreEventAvro;
import hcmus.zingmp3.song.SongEventAvro;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
public class GenreEventProducer {
    @Value("${topic.name}")
    private String topicName;

    private final KafkaTemplate<String, GenreEventAvro> template;

    public void send(String key, GenreEventAvro eventAvro) {
        CompletableFuture<SendResult<String, GenreEventAvro>> future = template.send(topicName, key, eventAvro);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Sent message=[" + eventAvro + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message=[" + eventAvro + "] due to : " + ex.getMessage());
            }
        });
    }
}
