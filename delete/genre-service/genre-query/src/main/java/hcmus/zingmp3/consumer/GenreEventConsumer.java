package hcmus.zingmp3.consumer;

import hcmus.zingmp3.genre.GenreAvroMapper;
import hcmus.zingmp3.genre.GenreEventAvro;
import hcmus.zingmp3.genre.GenreService;
import hcmus.zingmp3.genre.GenreServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenreEventConsumer {

    private final GenreAvroMapper mapper;
    private final GenreService genreService;

    @RetryableTopic(attempts = "4")
    @KafkaListener(
            id = "genre-consumer",
            topics = "${topic.name}"
    )
    public void handleGenreEvent(GenreEventAvro genreEvent) {
        System.out.println("Received genre event: " + genreEvent);
        String eventType = genreEvent.getEventType().toString();

        switch (eventType) {
            case "CreateGenre":
                System.out.println("Create genre: " + genreEvent.getGenre());
                genreService.createGenre(mapper.toEntity(genreEvent.getGenre()));
                break;
            case "UpdateGenre":
                System.out.println("Update genre: " + genreEvent.getGenre());
                genreService.updateGenre(mapper.toEntity(genreEvent.getGenre()));
                break;
            case "DeleteGenre":
                System.out.println("Delete genre: " + genreEvent.getGenre());
                genreService.deleteGenre(mapper.toUUID(genreEvent.getGenre().getId()));
                break;
            default:
                System.out.println("Unknown event type: " + eventType);
        }
    }

    @DltHandler
    public void handleDlt(GenreEventAvro genreEvent) {
        System.out.println("Handling DLT for genre event: " + genreEvent);
    }
}
