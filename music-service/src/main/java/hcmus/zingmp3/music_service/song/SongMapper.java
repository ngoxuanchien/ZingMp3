package hcmus.zingmp3.music_service.song;

import hcmus.zingmp3.AudioFileInfo;
import hcmus.zingmp3.ImageUploadResponse;
import hcmus.zingmp3.SongId;
import hcmus.zingmp3.music_service.artist.model.Artist;
import hcmus.zingmp3.music_service.artist.model.ArtistResponse;
import hcmus.zingmp3.music_service.audio.*;
import hcmus.zingmp3.music_service.artist.ArtistService;
import hcmus.zingmp3.music_service.audio.model.Audio;
import hcmus.zingmp3.music_service.genre.model.Genre;
import hcmus.zingmp3.music_service.genre.GenreService;
import hcmus.zingmp3.music_service.genre.model.GenreResponse;
import hcmus.zingmp3.music_service.song.model.Song;
import hcmus.zingmp3.music_service.song.model.SongRequest;
import hcmus.zingmp3.music_service.song.model.SongResponse;
import hcmus.zingmp3.music_service.thumbnail.ThumbnailClientService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SongMapper {
    private final static UUID DEFAULT_THUMBNAIL_ID = UUID.fromString("00000000-0000-0000-0000-000000000000");
    private final GenreService genreService;
    private final ThumbnailClientService thumbnailClientService;
    private final AudioClientService audioClientService;
    private final AudioService audioService;
    private final ArtistService artistService;


    public SongResponse toDTO(Song entity) {
        SongResponse dto = new SongResponse();
        BeanUtils.copyProperties(entity, dto);
        // Todo get thumbnail and streaming
        System.out.println(entity.getArtists().size());
        dto.setStreaming(getStreamingUrls(entity.getAudios()));
        dto.setThumbnail(thumbnailClientService.getById(entity.getThumbnail()).getUrl());
        dto.setArtists(entity.getArtists().stream().map(this::toDTO).collect(Collectors.toSet()));
        dto.setGenres(entity.getGenres().stream().map(this::toDTO).collect(Collectors.toSet()));
        dto.setComposers(entity.getComposers().stream().map(this::toDTO).collect(Collectors.toSet()));
        return dto;
    }

    private GenreResponse toDTO(Genre genre) {
        GenreResponse dto = new GenreResponse();
        dto.setId(genre.getId());
        dto.setName(genre.getName());
        dto.setTitle(genre.getTitle());
        dto.setAlias(genre.getAlias());
        return dto;
    }

    private ArtistResponse toDTO(Artist artist) {
//        System.out.println(artist);
        ArtistResponse dto = new ArtistResponse();
        dto.setId(artist.getId());
        dto.setName(artist.getName());
        dto.setAlias(artist.getAlias());
        dto.setPlaylistId(artist.getPlaylistId());
        dto.setThumbnail(thumbnailClientService.getById(artist.getThumbnail()).getUrl());
        dto.setTotalFollow(artist.getTotalFollow());
        return dto;
    }

    private Map<Long, String> getStreamingUrls(List<Audio> audios) {
        if (audios == null) {
            return new HashMap<>();
        }

        Map<Long, String> streamingUrls = new HashMap<>();
        audios.forEach(audio -> {
            // Todo get streaming url
            AudioFileInfo audioResponse = audioClientService.getById(audio.getId());
            streamingUrls.put(audioResponse.getBitrate(), audioResponse.getUrl());
        });
        return streamingUrls;
    }

    public Song toEntity(SongRequest dto) {
        Song entity = new Song();
        BeanUtils.copyProperties(dto, entity);

        // Todo get or create artist, genre, thumbnail, audio
        // Create or get thumbnail
        entity.setThumbnail(getOrCreateThumbnail(dto.getThumbnailFile()));

        // Get artist
        entity.setArtists(getArtist(dto.getArtists()));

        // Get genre
        entity.setGenres(getGenre(dto.getGenres()));

        // Get composer
        entity.setComposers(getArtist(dto.getComposers()));

        // Get or create audio
        entity.setAudios(getAllAudio(dto.getAudios(), dto.getAudioFiles()));

        return entity;
    }

    private List<Audio> getAllAudio(Set<String> audios, Set<MultipartFile> audioFiles) {
        List<Audio> audioSet = new ArrayList<>();
        audioSet.addAll(getAudio(audios));
        audioSet.addAll(getOrCreateAudio(audioFiles));
        return audioSet;
    }

    private List<Audio> getAudio(Set<String> audios) {
        if (audios == null) {
            return List.of();
        }

        List<Audio> audioSet = new ArrayList<>();
        audios.forEach(audio -> {
            Audio audioEntity = audioService.getById(audio);
            if (audioEntity != null) {
                audioSet.add(audioEntity);
            }
        });
        return audioSet;
    }

    private List<Genre> getGenre(Set<String> genres) {
        if (genres == null) {
            return List.of();
        }

        return genres.stream().map(genreService::getOrCreateByAlias).toList();
    }

    private List<Artist> getArtist(Set<String> artists) {
        if (artists == null) {
            return List.of();
        }

        return artists.stream().map(artistService::getOrCreateByAlias).toList();
    }

    public List<SongResponse> toDTO(List<Song> entities) {
        return entities.stream().map(this::toDTO).toList();
    }


    private UUID getOrCreateThumbnail(MultipartFile file) {
        if (file == null) {
            return DEFAULT_THUMBNAIL_ID;
        }

        ImageUploadResponse thumbnail = thumbnailClientService.getOrCreateIfNotExist(file, "song");
        return UUID.fromString(thumbnail.getId());

    }

    private Set<Audio> getOrCreateAudio(Set<MultipartFile> requests) {
        if (requests == null) {
            return new HashSet<>();
        }
        System.out.println(requests.size());

        Set<Audio> audios = new HashSet<>();
        requests.forEach(request -> {
            Audio audio = Audio
                    .builder()
                    .id(UUID
                            .fromString(audioClientService
                                    .getOrCreateIfNotExist(request)
                                    .getId()))
                    .build();
            audios.add(audio);
        });

        return audios;
    }

    @Transactional
    public hcmus.zingmp3.SongResponse toResponse(Song song) {
        System.out.println(song.isOfficial());
        System.out.println(song.isWorldWide());
        System.out.println();
        hcmus.zingmp3.SongResponse response = hcmus.zingmp3.SongResponse.newBuilder()
                .setId(song.getId().toString())
                .setTitle(song.getTitle())
                .setAlias(song.getAlias())
                .setIsOfficial(song.isOfficial())
                .setIsWorldWide(song.isWorldWide())
                .setReleaseDate(song.getReleaseDate())
                .setDistributor(song.getDistributor())
                .build();
        System.out.println(response);
        System.out.println(response.getIsOfficial());
        return response;
    }

    public SongId toSongId(Song song) {
        return SongId.newBuilder()
                .setId(song.getId().toString())
                .build();
    }
}
