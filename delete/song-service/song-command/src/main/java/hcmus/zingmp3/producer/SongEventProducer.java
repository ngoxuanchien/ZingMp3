package hcmus.zingmp3.producer;

import hcmus.zingmp3.song.SongEventAvro;
import hcmus.zingmp3.song.SongAvroMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class SongEventProducer {

    private final SongAvroMapper mapper;

    @Value("${topic.name}")
    private String topicName;

    private final KafkaTemplate<String, SongEventAvro> template;

    public void send(String key, SongEventAvro eventAvro) {
        CompletableFuture<SendResult<String, SongEventAvro>> future = template.send(topicName, key, eventAvro);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Sent message=[" + eventAvro + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message=[" + eventAvro + "] due to : " + ex.getMessage());
            }
        });
    }
}
