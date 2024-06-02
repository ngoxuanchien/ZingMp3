package hcmus.zingmp3.artistEvent;

import hcmus.zingmp3.artist.Artist;
import hcmus.zingmp3.producer.ArtistEventProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistEventServiceImpl implements ArtistEventService {
    private final ArtistEventAvroMapper artistEventAvroMapper;
    private final ArtistEventRepository repository;
    private final ArtistEventProducer producer;

    @Override
    public void createArtist(Artist artist) {
        ArtistEvent newEvent = ArtistEvent.builder()
                .eventType("CreateArtist")
                .artist(artist)
                .build();

        repository.save(newEvent);
        producer.send(artist.getId().toString(), artistEventAvroMapper.toAvro(newEvent));

    }

    @Override
    public void updateArtist(Artist artist) {
        ArtistEvent newEvent = ArtistEvent.builder()
                .eventType("UpdateArtist")
                .artist(artist)
                .build();

        repository.save(newEvent);
        producer.send(artist.getId().toString(), artistEventAvroMapper.toAvro(newEvent));

    }

    @Override
    public void deleteArtist(Artist artist) {
        ArtistEvent newEvent = ArtistEvent.builder()
                .eventType("DeleteArtist")
                .artist(artist)
                .build();

        repository.save(newEvent);
        producer.send(artist.getId().toString(), artistEventAvroMapper.toAvro(newEvent));
    }

    @Override
    public List<ArtistEvent> getAllArtistEvents() {
        return repository.findAllIncludingDeletedArtists();
    }
}
