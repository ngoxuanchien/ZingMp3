package hcmus.zingmp3.artist;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public interface ArtistService {

    ArtistRestResponse createArtist(ArtistRestRequest request, MultipartFile thumbnail);

    ArtistRestResponse updateAward(UUID id, ArtistRestRequest request, MultipartFile thumbnail);

    void deleteArtist(UUID id);

    Artist getArtistById(UUID id);

    void updateAward(Set<UUID> artistIds, UUID awardId);

    List<ArtistRestResponse> getAllArtist();

    Artist existsArtist(UUID uuid);

    void removeAward(Set<UUID> artistIds, UUID awardId);

    void removeSong(Set<UUID> artistIds, UUID songId);

    void updateSong(Set<UUID> artistIds, UUID songId);

    void updateComposerSong(Set<UUID> composerIds, UUID songId);

    void removeComposerSong(Set<UUID> composerIds, UUID songId);
}
