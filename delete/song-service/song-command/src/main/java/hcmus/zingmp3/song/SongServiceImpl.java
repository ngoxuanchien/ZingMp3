package hcmus.zingmp3.song;

import hcmus.zingmp3.artist.ArtistGrpcClient;
import hcmus.zingmp3.exception.SongExitsException;
import hcmus.zingmp3.exception.SongNotFoundException;
import hcmus.zingmp3.songevent.SongEventService;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {
    private final SongRepository repository;
    private final SongEventService eventService;
    private final SongMapper mapper;
    private final ArtistGrpcClient artistClient;

    private void checkArtist(Set<UUID> artistIds) {
        artistIds.forEach(artistClient::findArtistById);
    }

    @Override
    public SongRestResponse createSong(SongRestRequest request) {
        if (repository.existsByAlias(request.getAlias())) {
            throw new SongExitsException(
                    String.format("Cannot create song: Song with alias %s already exists", request.getAlias())
            );
        }
        checkArtist(request.getArtists());
        checkArtist(request.getComposers());
        // todo check thumbnail
        // todo check audios
        // todo check genres
        var song = repository.save(mapper.toEntity(request));

        eventService.createSong(song);
        return mapper.toDTO(song);
    }

    @Override
    public SongRestResponse updateSong(UUID id, SongRestRequest request) {
        var song = repository.findById(id)
                .orElseThrow(() -> new SongNotFoundException(
                        String.format("Cannot update song: Not song found with the provided Id %s ", id)
                ));
        checkArtist(request.getArtists());
        checkArtist(request.getComposers());
        // todo check thumbnail
        // todo check audios
        // todo check genres

        mergeSong(song, request);
        var newSong  = repository.save(song);
        eventService.updateSong(newSong);

        return mapper.toDTO(newSong);
    }

    private void mergeSong(Song song, SongRestRequest request) {
        song.setOfficial(request.isOfficial());
        song.setWorldWide(request.isWorldWide());
        song.setPrivate(request.isPrivate());

        if (StringUtils.isNotBlank(request.getTitle())) {
            song.setTitle(request.getTitle());
        }

        if (request.getThumbnail() != null) {
            song.setThumbnail(request.getThumbnail());
        }

        if (!request.getDistributor().isBlank()) {
            song.setDistributor(request.getDistributor());
        }

        if (!request.getArtists().isEmpty()) {
            song.setArtists(request.getArtists());
        }

        if (!request.getGenres().isEmpty()) {
            song.setGenres(request.getGenres());
        }

        if (!request.getComposers().isEmpty()) {
            song.setComposers(request.getComposers());
        }

        if (!request.getAudios().isEmpty()) {
            song.setAudios(request.getAudios());
        }
    }

    @Override
    public void deleteSong(UUID id) {
        var song = repository.findById(id)
                .orElseThrow(() -> new SongNotFoundException(
                        String.format("Cannot delete song: Not song found with the provided Id %s ", id)
                ));
        repository.delete(song);
        eventService.deleteSong(song);
    }
}
