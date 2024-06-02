package hcmus.zingmp3.songevent;

import hcmus.zingmp3.producer.SongEventProducer;
import hcmus.zingmp3.song.Song;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SongEventServiceImpl implements SongEventService {
    private final SongEventMapper mapper;
    private final SongEventProducer producer;
    private final SongEventRepository repository;


    @Override
    public void createSong(Song song) {
        var newEvent = SongEvent.builder()
                .eventType("CreateSong")
                .song(song)
                .build();

        repository.save(newEvent);
        producer.send(song.getId().toString(), mapper.toAvro(newEvent));

    }

    @Override
    public void updateSong(Song song) {
        var newEvent = SongEvent.builder()
                .eventType("UpdateSong")
                .song(song)
                .build();

        repository.save(newEvent);
        producer.send(song.getId().toString(), mapper.toAvro(newEvent));
    }

    @Override
    public void deleteSong(Song song) {
        var newEvent = SongEvent.builder()
                .eventType("DeleteSong")
                .song(song)
                .build();

        repository.save(newEvent);
        producer.send(song.getId().toString(), mapper.toAvro(newEvent));
    }
}
