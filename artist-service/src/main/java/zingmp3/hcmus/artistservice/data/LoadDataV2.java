package zingmp3.hcmus.artistservice.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;
import zingmp3.hcmus.artistservice.model.ArtistOrComposer;
import zingmp3.hcmus.artistservice.model.PlaylistOrAlbum;
import zingmp3.hcmus.artistservice.model.Song;
import zingmp3.hcmus.artistservice.model.error.ArtistError;
import zingmp3.hcmus.artistservice.model.error.PlaylistError;
import zingmp3.hcmus.artistservice.model.error.SongError;
import zingmp3.hcmus.artistservice.repository.*;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

@Service
@Slf4j
public class LoadDataV2 {
    private final FileManager fileManager;

    private final RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper objectMapper = new ObjectMapper();

    private final List<String> songList = new ArrayList<>();
    private final List<String> artistList = new ArrayList<>();
    private final List<String> playlistList = new ArrayList<>();

    private final List<String> songError = new ArrayList<>();
    private final List<String> artistError = new ArrayList<>();
    private final List<String> playlistError = new ArrayList<>();

    private final Queue<String> songQueue = new LinkedList<>();
    private final Queue<String> artistQueue = new LinkedList<>();
    private final Queue<String> playlistQueue = new LinkedList<>();

    private final Set<String> songSet = new HashSet<>();
    private final Set<String> artistSet = new HashSet<>();
    private final Set<String> playlistSet = new HashSet<>();

    public LoadDataV2(FileManager fileManager) {
        this.fileManager = fileManager;
//        this.songList = fileManager.readData("data\\song.txt");
//        this.artistList = fileManager.readData("data\\artist.txt");
//        this.playlistList = fileManager.readData("data\\playlist.txt");
        songSet.addAll(fileManager.readData("data\\song.txt"));
        songSet.addAll(fileManager.readData("data\\song_error.txt"));
        playlistSet.addAll(fileManager.readData("data\\playlist.txt"));
        artistSet.addAll(fileManager.readData("data\\artist.txt"));
    }

    public void getDataFromPlaylist(String playListId) {
        try {
            String playList = restTemplate.getForObject("http://localhost:3000/test/getDetailPlaylist/" + playListId, String.class);
            JsonNode jsonNode = objectMapper.readTree(playList);
            playlistList.add(playListId);

            // get artist
            if (jsonNode.get("artists") != null) {
                jsonNode.get("artists").forEach(artist -> {
//                    log.info(artist.get("alias").asText());
                    String artistAlias = artist.get("alias").asText();
                    if (artistSet.add(artistAlias)) {
                       artistQueue.add(artistAlias);
                    }
                });
            }

            // get song
            if (jsonNode.get("song") != null && jsonNode.get("song").get("items") != null) {
                jsonNode.get("song").get("items").forEach(item -> {
                    String songId = item.get("encodeId").asText();
                    if (songQueue.add(songId)) {
                        songSet.add(songId);
                    }
                });
            }
        } catch (HttpServerErrorException.InternalServerError e) {
            playlistError.add(playListId);
            log.error("Error when get data from playlist: {}", playListId);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void getDataFromArtist(String artistAlias) {
        try {
            String artist = restTemplate.getForObject("http://localhost:3000/test/getArtist/" + artistAlias, String.class);
            JsonNode jsonNode = objectMapper.readTree(artist);
            artistList.add(artistAlias);

            if (jsonNode.get("playlistId") != null) {
                String playlistId = jsonNode.get("playlistId").asText();
                if (playlistSet.add(playlistId)) {
                    playlistQueue.add(playlistId);
                }
            }


            jsonNode.get("sections").forEach(section -> {
                String sectionType = section.get("sectionType").asText();
                switch (sectionType) {
                    case "song":
                        section.get("items").forEach(item -> {
                            String songId = item.get("encodeId").asText();
                            if (!songSet.add(songId)) {
                                songQueue.add(songId);
                            }
                        });
                        break;
                    case "playlist":
                        section.get("items").forEach(item -> {
                            String playlistOrAlbumId = item.get("encodeId").asText();
                            if (!playlistSet.add(playlistOrAlbumId)) {
                                playlistQueue.add(playlistOrAlbumId);
                            }
                        });
                        break;
                }
            });
        } catch (HttpServerErrorException.InternalServerError e) {
            artistError.add(artistAlias);
            log.error("Error when get data from artist: {}", artistAlias);
//            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void getDataFromSong(String songId) {

        try {
            String song = restTemplate.getForObject("http://localhost:3000/test/getFullInfo/" + songId, String.class);
            JsonNode jsonNode = objectMapper.readTree(song);
            songList.add(songId);
            if (jsonNode.get("artists") == null) {
                return;
            }
            jsonNode.get("artists").forEach(artist -> {
                String artistAlias = artist.get("alias").asText();
                if (!artistSet.add(artistAlias)) {
                    artistQueue.add(artistAlias);
                }
            });

            if (jsonNode.get("album") != null && jsonNode.get("album").get("encodeId") != null) {
                String playlistId = jsonNode.get("album").get("encodeId").asText();
                if (!playlistSet.add(playlistId)) {
                    playlistQueue.add(playlistId);
                }
            }
        } catch (HttpServerErrorException.InternalServerError e) {
            songError.add(songId);
            log.error("Error when get data from song: {}", songId);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadData(String playlistId) {
        playlistQueue.add(playlistId);

        while (!playlistQueue.isEmpty() || !songQueue.isEmpty() || !artistQueue.isEmpty()) {
            log.info("Playlist queue: {}", playlistQueue.size());
            log.info("Song queue: {}", songQueue.size());
            log.info("Artist queue: {}", artistQueue.size());
            while (!playlistQueue.isEmpty()) {
                getDataFromPlaylist(playlistQueue.poll());
            }

            while (!songQueue.isEmpty()) {
                getDataFromSong(songQueue.poll());
            }

            while (!artistQueue.isEmpty()) {
                getDataFromArtist(artistQueue.poll());
            }

            fileManager.saveData(artistList, "data\\artist.txt");
            fileManager.saveData(songList, "data\\song.txt");
            fileManager.saveData(playlistList, "data\\playlist.txt");
            fileManager.saveData(artistError, "data\\artist_error.txt");
            fileManager.saveData(songError, "data\\song_error.txt");
            fileManager.saveData(playlistError, "data\\playlist_error.txt");
        }
        log.info("Load data done");
    }
}
