package hcmus.zingmp3.artist.service;

import hcmus.zingmp3.artist.ArtistAvro;
import hcmus.zingmp3.artist.dto.ArtistRestResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ArtistService {
    List<ArtistRestResponse> getAllArtists();
    List<ArtistRestResponse> getAllArtists(Pageable pageable);
    ArtistRestResponse getArtistById(UUID id);

    void createArtist(ArtistAvro artist);

    void updateArtist(ArtistAvro artist);

    void deleteArtist(String string);
}
