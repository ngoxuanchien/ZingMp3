package hcmus.zingmp3;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import hcmus.zingmp3.model.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static hcmus.zingmp3.Main.dir;
import static hcmus.zingmp3.Main.server;

public class CrawData {
    private final String songUrl = "http://" + server + ":3000/test/getFullInfo/";
    private final String artistUrl = "http://" + server + ":3000/test/getArtist/";
    private final String playlistUrl = "http://" + server + ":3000/test/getDetailPlaylist/";

    private final FileManager fileManager = new FileManager();
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final Queue<String> playlistQueue = new LinkedList<>();
    private final Set<String> playlistCraw = new HashSet<>();

    private final Set<Playlist> playlistResult = new HashSet<>();
    private final Set<Artist> artistResult = new HashSet<>();
    private final Set<Song> songResult = new HashSet<>();
    private final Set<Genre> genreResult = new HashSet<>();
    private final Set<Award> awardResult = new HashSet<>();

    private JsonNode getDataFromPlaylist(String playlistId) {
        return getData(playlistUrl + playlistId);
    }

    private JsonNode getDataFromArtist(String alias) {
        return getData(artistUrl + alias);
    }

    private JsonNode getDataFromSong(String songId) {
        return getData(songUrl + songId);
    }

    private JsonNode getData(String url) {
        try {
            String playList = restTemplate.getForObject(url, String.class);
            return objectMapper.readTree(playList);
        } catch (Exception e) {
            System.out.println(url);
            e.printStackTrace();
            return null;
        }
    }

    public void crawData(String playlistId) {
        playlistQueue.add(playlistId);
        playlistCraw.add(playlistId);

        try {
            while (!playlistQueue.isEmpty() && playlistResult.size() < 10) {
                String id = playlistQueue.poll();
                Playlist playlist = getPlaylist(id);
//                if (playlist == null) {
//                    System.out.println("Playlist error: " + id);
//                }
//                break;
//                fileManager.writeJson(playlistResult, "/data/playlist.json");
            }
        } finally {
//        System.out.println(playlistQueue.size());
//        System.out.println(playlistQueue);
            fileManager.writeJson(playlistResult, "/data/playlist.json");
            System.out.println(playlistCraw.size());
            System.out.println(playlistResult.size());
            System.out.println(songResult.size());
            System.out.println(artistResult.size());
        }


    }

    public Artist getArtist(String alias) {
        Optional<Artist> matchingArtist = artistResult.stream()
                .filter(artist -> artist.getAlias().equals(alias))
                .findFirst();

        if (matchingArtist.isPresent()) {
            return matchingArtist.get();
        } else {
            JsonNode artist = getDataFromArtist(alias);
            if (artist == null) {
                return null;
            }

            LocalDate birthday = null;
            if (artist.has("birthday")) {
                try {
                    birthday = LocalDate.parse(artist.get("birthday").asText());
                } catch (DateTimeParseException e) {
//                    birthday = LocalDate.now();
                    // Handle the exception as appropriate for your application
                }
            }

            String thumbnail = "";
            Set<Award> awards = new HashSet<>();
            try {

                String playlistId;
                if (artist.has("playlistId") &&
                        playlistCraw.add(playlistId = artist.get("playlistId").asText())) {
                    playlistQueue.add(playlistId);
                }

                thumbnail = artist.get("alias").asText() + ".jpg";
                String thumbnailUrl = artist.get("thumbnailM").asText();
                if (thumbnailUrl != null) {
                    fileManager.saveFile(thumbnailUrl, thumbnail, dir + "/data/artist/thumbnail/");
                }

                JsonNode awardsNode = artist.get("awards");
                if (artist.has("awards")) {
                    artist.get("awards").forEach(awardNode -> {
                        if (awardNode.isTextual()) {
                            String name = awardNode.asText();
                            if (awardResult.stream().noneMatch(award -> award.getName().equals(name))) {
                                Award award = Award.builder()
                                        .name(name)
                                        .build();
                                awards.add(award);
                                awardResult.add(award);
                            }
                        }
                    });
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            Artist newArtist = Artist.builder()
                    .name(artist.has("name") ? artist.get("name").asText() : "")
                    .link(artist.has("link") ? artist.get("link").asText() : "")
                    .alias(artist.has("alias") ? artist.get("alias").asText() : "")
                    .thumbnail(thumbnail)
//                    .playlistId()
                    .totalFollow(artist.has("totalFollow") ? artist.get("totalFollow").asInt() : 0)
                    .biography(artist.has("biography") ? artist.get("biography").asText() : "")
                    .sortBiography(artist.has("sortBiography") ? artist.get("sortBiography").asText() : "")
                    .national(artist.has("national") ? artist.get("national").asText() : "")
                    .realName(artist.has("realname") ? artist.get("realname").asText() : "")
                    .birthday(birthday)
                    .awards(awards)
                    .build();
            artistResult.add(newArtist);
            return newArtist;


        }
    }

    private Playlist getPlaylist(String playlistId) {
        JsonNode playlist = getDataFromPlaylist(playlistId);
        if (playlist == null) {
            return null;
        }

        Optional<Playlist> matchingPlaylist = playlistResult.stream()
                .filter(pl -> pl.getAliasTitle().equals(playlist.get("aliasTitle").asText()))
                .findFirst();

        if (matchingPlaylist.isPresent()) {
            return matchingPlaylist.get();
        } else {
            LocalDate releaseDate = null;
            if (playlist.has("releaseDate")) {
                try {
                    releaseDate = LocalDate.parse(playlist.get("releaseDate").asText());
                } catch (DateTimeParseException e) {
                    releaseDate = LocalDate.now();
                    // Handle the exception as appropriate for your application
                }
            }


            Set<Genre> genres = new HashSet<>();
            Set<Artist> artistSet = new HashSet<>();
            Set<Song> songSet = new HashSet<>();
            String thumbnail = "";
            try {
                if (playlist.has("artists")) {
                    artistSet = StreamSupport.stream(playlist.get("artists").spliterator(), false)
                            .map(artistNode -> {
                                String alias = artistNode.get("alias").asText();
                                return getArtist(alias);
                            })
                            .filter(Objects::nonNull)
                            .collect(Collectors.toSet());
                }


                if (playlist.has("song")) {
                    songSet = StreamSupport.stream(playlist.get("song").get("items").spliterator(), false)
                            .map(songNode -> {
                                String encodeId = songNode.get("encodeId").asText();
                                return getSong(encodeId);
                            })
                            .filter(Objects::nonNull)
                            .collect(Collectors.toSet());
                }

                // Get genres
                if (playlist.has("genres")) {
                    playlist.get("genres").forEach(genreNode -> {
                        genres.add(getGenre(genreNode));
                    });
                }

                thumbnail = playlist.get("aliasTitle").asText() + ".jpg";
                String thumbnailUrl = playlist.get("thumbnailM").asText();
                if (thumbnailUrl != null) {
                    fileManager.saveFile(thumbnailUrl, thumbnail, dir + "/data/playlist/thumbnail/");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            Playlist newPlaylist = Playlist.builder()
                    .title(playlist.has("title") ? playlist.get("title").asText() : "")
                    .thumbnail(thumbnail)
                    .isOfficial(playlist.has("isoffical") && playlist.get("isoffical").asBoolean())
                    .link(playlist.has("link") ? playlist.get("link").asText() : "")
                    .releaseDate(releaseDate)
                    .sortDescription(playlist.has("sortDescription") ? playlist.get("sortDescription").asText() : "")
                    .releasedAt(playlist.has("releasedAt") ? playlist.get("releasedAt").asInt() : 0)
                    .artistsNames(playlist.has("artistsNames") ? playlist.get("artistsNames").asText() : "")
                    .isPrivate(playlist.has("isPrivate") && playlist.get("isPrivate").asBoolean())
                    .isAlbum(playlist.has("isAlbum") && playlist.get("isAlbum").asBoolean())
                    .textType(playlist.has("textType") ? playlist.get("textType").asText() : "")
                    .distributor(playlist.has("distributor") ? playlist.get("distributor").asText() : "")
                    .description(playlist.has("description") ? playlist.get("description").asText() : "")
                    .aliasTitle(playlist.has("aliasTitle") ? playlist.get("aliasTitle").asText() : "")
                    .like(playlist.has("like") ? playlist.get("like").asInt() : 0)
                    .listen(playlist.has("listen") ? playlist.get("listen").asInt() : 0)
                    .artists(artistSet)
                    .songs(songSet)
                    .genres(genres)
                    .build();
            playlistResult.add(newPlaylist);
            return newPlaylist;
        }
    }

    private Song getSong(String songId) {
        JsonNode song = getDataFromSong(songId);
        if (song == null) {
            return null;
        }

        Optional<Song> matchingSong = songResult.stream()
                .filter(s -> s.getAlias().equals(song.get("alias").asText()))
                .findFirst();

        if (matchingSong.isPresent()) {
            return matchingSong.get();
        } else {
            Set<Artist> artists = new HashSet<>();
            Streaming streaming = null;
            Set<Genre> genres = new HashSet<>();
            Set<Artist> composers = new HashSet<>();
            String thumbnail = "";
            try {
                // Get artists
                if (song.has("artists")) {
                    artists = StreamSupport.stream(song.get("artists").spliterator(), false)
                            .map(artistNode -> {
                                String alias = artistNode.get("alias").asText();
                                return getArtist(alias);
                            })
                            .filter(Objects::nonNull)
                            .collect(Collectors.toSet());
                }

                if (song.has("composers")) {
                    composers = StreamSupport.stream(song.get("composers").spliterator(), false)
                            .map(composerNode -> {
                                String alias = composerNode.get("alias").asText();
                                return getArtist(alias);
                            })
                            .filter(Objects::nonNull)
                            .collect(Collectors.toSet());
//                    System.out.println(composers.size());
                }



                // Get album
                if (song.has("album")) {
                    String album = song.get("album").get("encodeId").asText();
                    if (album != null && playlistCraw.add(album)) {
                        playlistQueue.add(album);
                    }
                }


                // Get streaming
                JsonNode streamingNode = song.get("streaming");
                if (streamingNode != null) {
                    String songFile;
                    if (song.has("alias")) {
                        songFile = song.get("alias").asText() + ".mp3";
                    } else {
                        songFile = UUID.randomUUID() + ".mp3";
                    }
                    String url = streamingNode.has("128") ? streamingNode.get("128").asText() : "";
                    fileManager.saveFile(url, songFile, dir + "/data/song/128/");

                    streaming = Streaming.builder()
                            .url128kps(songFile)
                            .url320kps("VIP")
                            .build();
                }

                // Get genres
                if (song.has("genres")) {
                    song.get("genres").forEach(genreNode -> {
                        genres.add(getGenre(genreNode));
                    });
                }

                thumbnail = song.get("alias").asText() + ".jpg";
                String thumbnailUrl = song.get("thumbnailM").asText();
                if (thumbnailUrl != null) {
                    fileManager.saveFile(thumbnailUrl, thumbnail, dir + "/data/song/thumbnail/");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }


            Song newSong = Song.builder()
                    .title(song.has("title") ? song.get("title").asText() : "")
                    .alias(song.has("alias") ? song.get("alias").asText() : "")
                    .isOfficial(song.has("isOffical") && song.get("isOffical").asBoolean())
                    .artistsNames(song.has("artistsNames") ? song.get("artistsNames").asText() : "")
                    .isWorldWide(song.has("isWorldWide") && song.get("isWorldWide").asBoolean())
                    .thumbnail(song.has("thumbnailM") ? song.get("thumbnailM").asText() : "")
                    .duration(song.has("duration") ? song.get("duration").asInt() : 0)
                    .isPrivate(song.has("isPrivate") && song.get("isPrivate").asBoolean())
                    .releaseDate(song.has("releaseDate") ? song.get("releaseDate").asInt() : 0)
                    .distributor(song.has("distributor") ? song.get("distributor").asText() : "")
                    .hasLyric(song.has("hasLyric") && song.get("hasLyric").asBoolean())
                    .like(song.has("like") ? song.get("like").asInt() : 0)
                    .listen(song.has("listen") ? song.get("listen").asInt() : 0)
                    .liked(song.has("liked") && song.get("liked").asBoolean())
                    .comment(song.has("comment") ? song.get("comment").asInt() : 0)
                    .streaming(streaming)
                    .artists(artists)
                    .genres(genres)
                    .composers(composers)
                    .build();
            songResult.add(newSong);
            return newSong;
        }
    }

    public Genre getGenre(JsonNode jsonNode) {
        Optional<Genre> matchingGenre = genreResult.stream()
                .filter(genre -> genre.getAlias().equals(jsonNode.get("alias").asText()))
                .findFirst();

        if (matchingGenre.isPresent()) {
            return matchingGenre.get();
        } else {
            Genre genre = Genre.builder()
                    .name(jsonNode.has("name") ? jsonNode.get("name").asText() : "")
                    .title(jsonNode.has("title") ? jsonNode.get("title").asText() : "")
                    .alias(jsonNode.has("alias") ? jsonNode.get("alias").asText() : "")
                    .link(jsonNode.has("link") ? jsonNode.get("link").asText() : "")
                    .build();
            genreResult.add(genre);
            return genre;
        }
    }
}
