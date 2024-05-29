package hcmus.zingmp3.artist;

import hcmus.zingmp3.artist.model.ArtistRestRequest;
import hcmus.zingmp3.artist.model.ArtistRestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService {

    private final UUID DEFAULT_THUMBNAIL = UUID.fromString("00000000-0000-0000-0000-000000000000");
    private final ArtistRepository artistRepository;
    private final ArtistMapper artistMapper;
    private final ArtistKafkaProducer artistKafkaProducer;

    @Override
    public ArtistRestResponse createArtist(ArtistRestRequest request, MultipartFile thumbnail) {

        // set thumbnail
        request.setThumbnail(DEFAULT_THUMBNAIL);

        var newArtist = artistMapper.toEntity(request);
        newArtist = artistRepository.save(newArtist);

        artistKafkaProducer.createArtist(artistMapper.toAvro(newArtist));

        return artistMapper.toDto(newArtist);
    }

    @Override
    public ArtistRestResponse updateArtist(UUID id, ArtistRestRequest request, MultipartFile thumbnail) {

        // set thumbnail
        request.setThumbnail(DEFAULT_THUMBNAIL);

        var newArtist = artistMapper.toEntity(request);
        newArtist.setId(id);
        newArtist = artistRepository.save(newArtist);

        artistKafkaProducer.updateArtist(artistMapper.toAvro(newArtist));
        return artistMapper.toDto(newArtist);
    }

    @Override
    public void deleteArtist(UUID id) {
        var deletedArtist = artistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artist not found with id: " + id));

        artistKafkaProducer.deleteArtist(artistMapper.toAvro(deletedArtist));
        artistRepository.deleteById(id);
    }
}
