package hcmus.zingmp3.artist;

import hcmus.zingmp3.artistEvent.ArtistEventService;
import hcmus.zingmp3.exception.ArtistNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService {

    private final UUID DEFAULT_THUMBNAIL = UUID.fromString("00000000-0000-0000-0000-000000000000");
    private final ArtistRepository artistRepository;
    private final ArtistMapper artistMapper;
    private final ArtistEventService eventService;
    private final EntityManager entityManager;
    @Override
    public ArtistRestResponse createArtist(ArtistRestRequest request, MultipartFile thumbnail) {
        var savedArtist = artistRepository.save(artistMapper.toEntity(request));
        eventService.createArtist(savedArtist);
        return artistMapper.toDto(savedArtist);
    }

    @Override
    public ArtistRestResponse updateAward(UUID id, ArtistRestRequest request, MultipartFile thumbnail) {
        var oldArtist = artistRepository.findById(id)
                .orElseThrow(() -> new ArtistNotFoundException("Artist not found with id: " + id));
        mergeArtist(oldArtist, request);

        var newArtist = artistRepository.save(oldArtist);
        eventService.updateArtist(newArtist);
        return artistMapper.toDto(newArtist);
    }

    private void mergeArtist(Artist artist, ArtistRestRequest request) {
        artist.setAlias(request.getAlias());
        artist.setName(request.getName());
        artist.setAwards(request.getAwards());
        artist.setSortBiography(request.getSortBiography());
        artist.setBiography(request.getBiography());
        artist.setNational(request.getNational());
        artist.setRealName(request.getRealName());
        artist.setBirthday(request.getBirthday());
    }

    @Override
    public void deleteArtist(UUID id) {
        var deletedArtist = artistRepository.findById(id)
                .orElseThrow(() -> new ArtistNotFoundException("Artist not found with id: " + id));

        eventService.deleteArtist(deletedArtist);
        artistRepository.deleteById(id);
    }

    @Override
    public Artist getArtistById(UUID id) {
        return artistRepository.findById(id).orElse(null);
    }

    @Override
    public void updateAward(Set<UUID> artistIds, UUID awardId) {
        Set<UUID> artistIdsList = artistRepository.findAllByAwardsContaining(awardId)
                .stream()
                .map(Artist::getId)
                .collect(Collectors.toSet());

        artistIds.forEach(artistId -> {
            if (!artistIdsList.contains(artistId)) {
                addAward(artistId, awardId);
            }
        });

        artistIdsList.forEach(artistId -> {
            if (!artistIds.contains(artistId)) {
                removeAward(artistId, awardId);
            }
        });

    }

    @Override
    public List<ArtistRestResponse> getAllArtist() {

        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedArtistFilter");
        filter.setParameter("is_deleted", false);

        List<ArtistRestResponse> artists = artistRepository.findAll()
                .stream()
                .map(artistMapper::toDto)
                .toList();

        session.disableFilter("deletedArtistFilter");
        return artists;
    }

    @Override
    public Artist existsArtist(UUID uuid) {
        return artistRepository.findById(uuid).orElse(null);
    }

    @Override
    public void removeAward(Set<UUID> artistIds, UUID awardId) {
        if (artistIds == null || awardId == null) {
            return;
        }

        artistIds.forEach(artistId -> removeAward(artistId, awardId));
    }

    @Override
    public void removeSong(Set<UUID> artistIds, UUID songId) {
        if (artistIds == null || songId == null) {
            return;
        }

        artistIds.forEach(artistId -> removeSong(artistId, songId));
    }

    @Override
    public void updateSong(Set<UUID> artistIds, UUID songId) {
        if (artistIds == null || songId == null) {
            return;
        }

        Set<UUID> artistIdsList = artistRepository.findAllBySongsContaining(songId)
                .stream()
                .map(Artist::getId)
                .collect(Collectors.toSet());

        artistIds.forEach(artistId -> {
            if (!artistIdsList.contains(artistId)) {
                addSong(artistId, songId);
            }
        });

        artistIdsList.forEach(artistId -> {
            if (!artistIds.contains(artistId)) {
                removeSong(artistId, songId);
            }
        });

    }

    @Override
    public void updateComposerSong(Set<UUID> composerIds, UUID songId) {
        if (composerIds == null || songId == null) {
            return;
        }

        Set<UUID> artistsIdsList = artistRepository.findAllByComposedSongsContaining(songId)
                .stream()
                .map(Artist::getId)
                .collect(Collectors.toSet());

        composerIds.forEach(composerId -> {
            if (!artistsIdsList.contains(composerId)) {
                addComposerSong(composerId, songId);
            }
        });

        artistsIdsList.forEach(artistId -> {
            if (!composerIds.contains(artistId)) {
                removeComposerSong(artistId, songId);
            }
        });
    }

    @Override
    public void removeComposerSong(Set<UUID> composerIds, UUID songId) {
        if (composerIds == null || songId == null) {
            return;
        }

        composerIds.forEach(composerId -> removeComposerSong(composerId, songId));
    }

    private void addComposerSong(UUID composerId, UUID songId) {
        Artist artist = artistRepository.findById(composerId)
                .orElseThrow(() -> new ArtistNotFoundException("Artist not found with id: " + composerId));
        artist.getComposedSongs().add(songId);
        artistRepository.save(artist);
        eventService.updateArtist(artist);
    }

    private void removeComposerSong(UUID composerId, UUID songId) {
        Artist artist = artistRepository.findById(composerId)
                .orElseThrow(() -> new ArtistNotFoundException("Artist not found with id: " + composerId));
        artist.getComposedSongs().remove(songId);
        artistRepository.save(artist);
        eventService.updateArtist(artist);
    }

    private void addSong(UUID artistId, UUID songId) {
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new ArtistNotFoundException("Artist not found with id: " + artistId));
        artist.getSongs().add(songId);
        artistRepository.save(artist);
        eventService.updateArtist(artist);
    }

    private void addAward(@NotNull UUID artistId, @NotNull UUID awardId) {
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new ArtistNotFoundException("Artist not found with id: " + artistId));
        artist.getAwards().add(awardId);
        artistRepository.save(artist);
        eventService.updateArtist(artist);
    }

    private void removeAward(@NotNull UUID artistId, @NotNull UUID awardId) {
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new ArtistNotFoundException("Artist not found with id: " + artistId));
        artist.getAwards().remove(awardId);
        artistRepository.save(artist);
        eventService.updateArtist(artist);
    }

    private void removeSong(@NotNull UUID artistId, @NotNull UUID songId) {
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new ArtistNotFoundException("Artist not found with id: " + artistId));
        artist.getSongs().remove(songId);
        artistRepository.save(artist);
        eventService.updateArtist(artist);
    }
}
