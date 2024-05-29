package hcmus.zingmp3.artist;

import hcmus.zingmp3.artist.model.ArtistRestRequest;
import hcmus.zingmp3.artist.model.ArtistRestResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public interface ArtistService {

    ArtistRestResponse createArtist(ArtistRestRequest request, MultipartFile thumbnail);

    ArtistRestResponse updateArtist(UUID id, ArtistRestRequest request, MultipartFile thumbnail);

    void deleteArtist(UUID id);
}
