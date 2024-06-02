package hcmus.zingmp3.genreevent;

import hcmus.zingmp3.genre.Genre;
import hcmus.zingmp3.producer.GenreEventProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenreEventServiceImpl implements GenreEventService {

    private final GenreEventRepository repository;
    private final GenreEventProducer producer;
    private final GenreEventMapper mapper;

    @Override
    public void createGenre(Genre genre) {
        var newEvent = GenreEvent.builder()
                .eventType("CreateGenre")
                .genre(genre)
                .build();

        repository.save(newEvent);
        producer.send(genre.getId().toString(), mapper.toAvro(newEvent));
    }

    @Override
    public void updateGenre(Genre genre) {
        var newEvent = GenreEvent.builder()
                .eventType("UpdateGenre")
                .genre(genre)
                .build();

        repository.save(newEvent);
        producer.send(genre.getId().toString(), mapper.toAvro(newEvent));
    }

    @Override
    public void deleteGenre(Genre genre) {
        var newEvent = GenreEvent.builder()
                .eventType("DeleteGenre")
                .genre(genre)
                .build();

        repository.save(newEvent);
        producer.send(genre.getId().toString(), mapper.toAvro(newEvent));
    }
}
