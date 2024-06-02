package hcmus.zingmp3.artist.service;

import hcmus.zingmp3.artist.ArtistAvro;
import hcmus.zingmp3.artist.dto.ArtistRestResponse;
import hcmus.zingmp3.exception.EntityNotFoundException;
import hcmus.zingmp3.artist.mapper.ArtistMapper;
import hcmus.zingmp3.artist.model.Artist;
import hcmus.zingmp3.artist.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService {
    private final ArtistRepository artistRepository;
    private final ArtistMapper artistMapper;

    @Override
    public List<ArtistRestResponse> getAllArtists() {
        List<Artist> artists = StreamSupport.stream(artistRepository.findAll().spliterator(), true).toList();
        List<ArtistRestResponse> artistRestResponses = artistMapper.toDTOs(artists);
        return artistMapper.toDTOs(artistRepository.findAll());
    }

    @Override
    public List<ArtistRestResponse> getAllArtists(Pageable pageable) {
        return artistMapper.toDTOs(artistRepository.findAll(pageable).getContent());
    }

    @Override
    public ArtistRestResponse getArtistById(UUID id) {
        Artist artist = artistRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("No artist found with the provided id: %s", id.toString())
                ));
        return artistMapper.toDTO(artist);
    }

    @Override
    public void createArtist(ArtistAvro artist) {
        artistRepository.save(artistMapper.toEntity(artist));
    }

    @Override
    public void updateArtist(ArtistAvro artist) {
        artistRepository.save(artistMapper.toEntity(artist));
    }

    @Override
    public void deleteArtist(String id) {
        artistRepository.deleteById(UUID.fromString(id));
    }
}
