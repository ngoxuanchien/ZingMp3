package hcmus.zingmp3.artist;

import hcmus.zingmp3.artist.model.Artist;
import hcmus.zingmp3.exception.NotFoundException;
import hcmus.zingmp3.artist.model.Artist;
import hcmus.zingmp3.artist.model.ArtistRequest;
import hcmus.zingmp3.artist.model.ArtistResponse;
import hcmus.zingmp3.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;
    private final ArtistMapper artistMapper;

    @Override
    public ArtistResponse createArtist(ArtistRequest request) {
        Artist newArtist = artistMapper.toEntity(request);
        return artistMapper.toDTO(artistRepository.save(newArtist));
    }

    @Override
    public ArtistResponse getArtistById(String id) {
        Artist artist = artistRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException("Artist not found with id: " + id));
        return artistMapper.toDTO(artist);
    }

    @Override
    public List<ArtistResponse> getAllArtists(Pageable pageable) {
        return artistMapper.toDTO(artistRepository.findAll(pageable).getContent());
    }

    @Override
    public ArtistResponse updateArtist(String id, ArtistRequest request) {
        UUID uuid = UUID.fromString(id);
        artistRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException("Artist not found with id: " + id));
        Artist newArtist = artistMapper.toEntity(request);
        newArtist.setId(uuid);
        return artistMapper.toDTO(artistRepository.save(newArtist));
    }

    @Override
    public void deleteArtist(String id) {
        UUID uuid = UUID.fromString(id);
        artistRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException("Artist not found with id: " + id));
        artistRepository.deleteById(uuid);
    }

    @Override
    public Artist getOrCreateByAlias(String alias) {
        return artistRepository.findByAlias(alias)
                .orElseGet(() -> artistRepository.save(Artist.builder().alias(alias).build()));
    }
}
