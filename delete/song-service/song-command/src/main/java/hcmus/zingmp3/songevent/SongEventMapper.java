package hcmus.zingmp3.songevent;

import hcmus.zingmp3.song.SongEventAvro;
import hcmus.zingmp3.song.SongAvroMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SongEventMapper {
    private final SongAvroMapper mapper;

    public SongEventAvro toAvro(SongEvent songEvent) {
        return SongEventAvro.newBuilder()
                .setEventType(songEvent.getEventType())
                .setSong(mapper.toAvro(songEvent.getSong()))
                .build();
    }
}
