package hcmus.zingmp3.genreevent;

import hcmus.zingmp3.genre.GenreEventAvro;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GenreEventMapper {
    private final GenreAvroMapper mapper;

    public GenreEventAvro toAvro(GenreEvent newEvent) {
        return GenreEventAvro.newBuilder()
                .setEventType(newEvent.getEventType())
                .setGenre(mapper.toAvro(newEvent.getGenre()))
                .build();
    }
}
