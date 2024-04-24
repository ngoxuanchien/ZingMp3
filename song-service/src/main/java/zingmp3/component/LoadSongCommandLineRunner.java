package zingmp3.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import zingmp3.model.*;
import zingmp3.repository.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@RequiredArgsConstructor
@Slf4j
public class LoadSongCommandLineRunner implements CommandLineRunner {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final RestTemplate restTemplate = new RestTemplate();
    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;
    private final PreviewInfoRepository previewInfoRepository;
    private final ArtistRepository artistRepository;
    private final StreamingRepository streamingRepository;
    private final ComposerRepository composerRepository;
    private final GenreRepository genreRepository;


    private List<Artist> convertToArtists(JsonNode jsonNode) {
        if (jsonNode == null) {
            return List.of();
        }
        List<Artist> artists = new ArrayList<>();
        jsonNode.forEach(artistNode -> {
            Artist artist = Artist.builder()
                    .name(artistNode.get("name").asText())
                    .link(artistNode.get("link").asText())
                    .spotlight(artistNode.get("spotlight").asBoolean())
                    .alias(artistNode.get("alias").asText())
                    .thumbnail(artistNode.get("thumbnail").asText())
                    .thumbnailM(artistNode.get("thumbnailM").asText())
                    .isOA(artistNode.get("isOA").asBoolean())
                    .totalFollow(artistNode.get("totalFollow").asInt())
                    .build();

            if (!artistRepository.existsArtistByName(artist.getName())) {
                artists.add(artistRepository.save(artist));
            } else {
                artists.add(artistRepository.findArtistByName(artist.getName()));
            }
        });
        return artists;
    }


    private Song convertToSong(JsonNode jsonNode) {
        if (songRepository.existsByTitle(jsonNode.get("title").asText())) {
            return null;
        } else {
            return songRepository.save(
                    Song.builder()
                            .title(jsonNode.get("title").asText())
                            .alias(jsonNode.get("alias").asText())
                            .isOfficial(jsonNode.get("isOffical").asBoolean())
                            .username(jsonNode.get("username").asText())
                            .artistsNames(jsonNode.get("artistsNames").asText())
                            .artists(convertToArtists(jsonNode.get("artists")))
                            .isWorldWide(jsonNode.get("isWorldWide").asBoolean())
                            .previewInfo(
                                    convertToPreviewInfo(jsonNode.get("previewInfo"))
                            )
                            .thumbnailM(jsonNode.get("thumbnailM").asText())
                            .link(jsonNode.get("link").asText())
                            .thumbnail(jsonNode.get("thumbnail").asText())
                            .duration(jsonNode.get("duration").asInt())
                            .zingChoice(jsonNode.get("zingChoice").asBoolean())
                            .isPrivate(jsonNode.get("isPrivate").asBoolean())
                            .preRelease(jsonNode.get("preRelease").asBoolean())
                            .releaseDate(convertToLocalDateTime(jsonNode.get("releaseDate").asInt()))
                            .distributor(jsonNode.get("distributor").asText())
                            .indicators(convertToIndicator(jsonNode.get("indicators")))
                            .isIndie(jsonNode.get("isIndie").asBoolean())
                            .streamingStatus(jsonNode.get("streamingStatus").asInt())
                            .allowAudioAds(jsonNode.get("allowAudioAds").asBoolean())
                            .hasLyric(
                                    jsonNode.get("hasLyric") != null &&
                                            jsonNode.get("hasLyric").asBoolean())
                            .userId(jsonNode.get("userid").asInt())
                            .genres(convertToGenres(jsonNode.get("genres")))
                            .composers(convertToComposers(jsonNode.get("composers")))
                            .album(convertToAlbum(jsonNode.get("album")))
                            .isRBT(jsonNode.get("isRBT").asBoolean())
                            .like(jsonNode.get("like").asInt())
                            .listen(jsonNode.get("listen").asInt())
                            .comment(jsonNode.get("comment").asInt())
                            .streaming(convertToStreaming(jsonNode.get("streaming")))
                            .build()
            );
        }




    }

    private Streaming convertToStreaming(JsonNode streaming) {
        return streamingRepository.save(
                Streaming.builder()
                    .url128kps(streaming.get("128").asText())
                    .url320kps(streaming.get("320").asText())
                    .build()
        );
    }

    private Album convertToAlbum(JsonNode albumNode) {
        if (albumNode == null) {
            return null;
        }

        Album album = Album.builder()
                    .title(albumNode.get("title").asText())
                    .thumbnail(albumNode.get("thumbnail").asText())
                    .isOfficial(albumNode.get("isoffical").asBoolean())
                    .link(albumNode.get("link").asText())
                    .isIndie(albumNode.get("isIndie").asBoolean())
                    .releaseDate(convertToLocalDateTime(albumNode.get("releaseDate").asText()))
                    .sortDescription(albumNode.get("sortDescription").asText())
                    .releasedAt(convertToLocalDateTime(albumNode.get("releasedAt").asInt()))
                    //                    .genres(convertToGenres(albumNode.get("genres")))
                    .PR(albumNode.get("PR").asBoolean())
                    .artists(convertToArtists(albumNode.get("artists")))
                    .artistsNames(albumNode.get("artistsNames").asText())
                    .build();
        if (albumRepository.existsAlbumByTitle(album.getTitle())) {
            return albumRepository.findAlbumByTitle(album.getTitle());
        } else {
            return albumRepository.save(album);
        }

    }

    private List<Composer> convertToComposers(JsonNode composers) {
        if (composers == null) {
            return List.of();
        }
        List<Composer> composerList = new ArrayList<>();
        composers.forEach(composerNode -> {
            Composer composer = Composer.builder()
                    .name(composerNode.get("name").asText())
                    .link(composerNode.get("link").asText())
                    .spotlight(composerNode.get("spotlight").asBoolean())
                    .alias(composerNode.get("alias").asText())
                    .cover(composerNode.get("cover").asText())
                    .thumbnail(composerNode.get("thumbnail").asText())
                    .totalFollow(
                            composerNode.get("totalFollow") == null ? 0 : composerNode.get("totalFollow").asInt())
                    .build();

            if (composerRepository.existsComposerByNameAndAlias(composer.getName(), composer.getAlias())) {
                 composerList.add(composerRepository.findComposerByNameAndAlias(composer.getName(), composer.getAlias()));
            } else {
                composerList.add(composerRepository.save(composer));
            }
        });
        return composerList;
    }

    private List<Genre> convertToGenres(JsonNode genreIds) {
        List<Genre> genres = new ArrayList<>();
        genreIds.forEach(genreNode -> {
            Genre genre = Genre.builder()
                    .name(genreNode.get("name").asText())
                    .title(genreNode.get("title").asText())
                    .alias(genreNode.get("alias").asText())
                    .link(genreNode.get("link").asText())
                    .build();
            if (genreRepository.existsByName(genre.getName())) {
                genres.add(genreRepository.findByName(genre.getName()));
            } else {
                genres.add(genreRepository.save(genre));
            }
        });
        return genres;
    }

    private List<Indicator> convertToIndicator(JsonNode jsonNode) {
        if (jsonNode.has(0)) {
            log.info("Indicator has value");
        }
        return new ArrayList<>();
    }

    private LocalDateTime convertToLocalDateTime(String date) {
        if (date.isEmpty()) {
            return null;
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate localDate = LocalDate.parse(date, dateTimeFormatter);
        return localDate.atStartOfDay();
    }

    private LocalDateTime convertToLocalDateTime(int date) {
        Instant instant = Instant.ofEpochSecond(date);
        return LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
    }

    private PreviewInfo convertToPreviewInfo(JsonNode previewInfo) {
        if (previewInfo == null) {
            return null;
        }
        return previewInfoRepository.save(
                PreviewInfo.builder()
                    .startTime(previewInfo.get("startTime").asInt())
                    .endTime(previewInfo.get("endTime").asInt())
                    .build()
        );
    }

    @Override
    public void run(String... args) throws Exception {

        boolean run = false;
        AtomicInteger errorMap = new AtomicInteger();
        AtomicInteger errorPlaylist = new AtomicInteger();
        AtomicInteger errorSong = new AtomicInteger();
        if (run) {
            String top100 = restTemplate.getForObject("http://localhost:3000/test/getPlaylist", String.class);
            objectMapper.readTree(top100).forEach(
                    item -> {
                        item.get("items").forEach(playlist -> {
                            try {
                                String playList = restTemplate.getForObject("http://localhost:3000/test/getDetailPlaylist/" + playlist.get("encodeId").asText(), String.class);
                                JsonNode jsonNode = objectMapper.readTree(playList).get("song").get("items");
                                jsonNode.forEach(songNode -> {
                                    try {
                                        String responseSong = restTemplate.getForObject("http://localhost:3000/test/getFullInfo/" + songNode.get("encodeId").asText(), String.class);

                                        JsonNode song = null;
                                        try {
                                            song = objectMapper.readTree(responseSong);
                                            convertToSong(song);
                                        } catch (JsonProcessingException e) {
                                            errorMap.getAndIncrement();
                                            System.out.println("Error mapping");
                                        }

                                    } catch (Exception e) {
                                        System.out.println("Error song: " + songNode.get("encodeId").asText());
                                        errorSong.getAndIncrement();

                                    }
                                });
                            } catch (Exception e) {
                                System.out.println("Error playlist: " + playlist.get("encodeId").asText());
                                errorPlaylist.getAndIncrement();
                            }

                        });
                    }
            );
            System.out.println("Done");
            System.out.println("Error map: " + errorMap.get());
            System.out.println("Error playlist: " + errorPlaylist.get());
            System.out.println("Error song: " + errorSong.get());


        }
    }
}
