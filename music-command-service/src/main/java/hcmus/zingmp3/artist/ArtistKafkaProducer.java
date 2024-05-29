package hcmus.zingmp3.artist;

import hcmus.zingmp3.artist.model.ArtistAvro;
import hcmus.zingmp3.artist.model.ArtistEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class ArtistKafkaProducer {

    @Value("${topic.name}")
    private String topicName;

    private final KafkaTemplate<String, ArtistEvent> template;

    public void send(String key, ArtistEvent artistEvent) {
        CompletableFuture<SendResult<String, ArtistEvent>> future = template.send(topicName, key, artistEvent);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Sent message=[" + artistEvent + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message=[" + artistEvent + "] due to : " + ex.getMessage());
            }
        });
    }

    public void createArtist(ArtistAvro artist) {
        ArtistEvent artistEvent = ArtistEvent.newBuilder()
                .setEventType("CreateArtist")
                .setArtist(artist)
                .build();
        send(artist.getId().toString(), artistEvent);
    }

    public void updateArtist(ArtistAvro artist) {
        ArtistEvent artistEvent = ArtistEvent.newBuilder()
                .setEventType("UpdateArtist")
                .setArtist(artist)
                .build();
        send(artist.getId().toString(), artistEvent);
    }

    public void deleteArtist(ArtistAvro artist) {
        ArtistEvent artistEvent = ArtistEvent.newBuilder()
                .setEventType("DeleteArtist")
                .setArtist(artist)
                .build();
        send(artist.getId().toString(), artistEvent);
    }
}
