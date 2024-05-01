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

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoadData {
    private final FileManager fileManager;

    private final RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper objectMapper = new ObjectMapper();

    private final SongRepository songRepository;
    private final ArtistOrComposerRepository artistRepository;
    private final PlaylistOrAlbumRepository playlistRepository;

    private final SongErrorRepository songErrorRepository;
    private final ArtistErrorRepository artistErrorRepository;
    private final PlaylistErrorRepository playlistErrorRepository;


    public void getDataFromPlaylist(String playListId) {
        if (playlistErrorRepository.existsById(playListId)) {
            return;
        }

        if (playlistRepository.existsById(playListId)) {
            return;
        } else {
            playlistRepository.save(
                    PlaylistOrAlbum
                        .builder()
                        .id(playListId)
                        .build()
            );
        }

        try {
            String playList = restTemplate.getForObject("http://localhost:3000/test/getDetailPlaylist/" + playListId, String.class);
            JsonNode jsonNode = objectMapper.readTree(playList);

            // get artist
            if (jsonNode.get("artists") != null) {
                jsonNode.get("artists").forEach(artist -> {
//                    log.info(artist.get("alias").asText());
                    String artistAlias = artist.get("alias").asText();
                    if (!artistRepository.existsById(artistAlias)) {
                        getDataFromArtist(artistAlias);
                    }
                });
            }

            // get song
            if (jsonNode.get("song") != null) {
                jsonNode.get("song").get("items").forEach(item -> {
                    String songId = item.get("encodeId").asText();
                    if (!songRepository.existsById(songId)) {
                        getDataFromSong(songId);
                    }
                });
            }
        } catch (HttpServerErrorException.InternalServerError e) {
            playlistErrorRepository.save(
                    PlaylistError
                        .builder()
                        .id(playListId)
                        .build()
            );
            log.error("Error when get data from playlist: {}", playListId);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } finally {
//            fileManager.saveData(errorPlaylistId, "data\\errorPlaylistId.txt");
//            fileManager.saveData(playlistOrAlbumList, "data\\playlistOrAlbum.txt");
        }
    }

    public void getDataFromArtist(String artistAlias) {
        if (artistErrorRepository.existsById(artistAlias)) {
            return;
        }

        if (artistRepository.existsById(artistAlias)) {
            return;
        } else {
            artistRepository.save(
                    ArtistOrComposer.builder()
                            .id(artistAlias)
                            .build()
            );
        }

        try {
            String artist = restTemplate.getForObject("http://localhost:3000/test/getArtist/" + artistAlias, String.class);
            JsonNode jsonNode = objectMapper.readTree(artist);

            if (jsonNode.get("playlistId") != null) {
                String playlistId = jsonNode.get("playlistId").asText();
                if (!playlistRepository.existsById(playlistId)) {
                    getDataFromPlaylist(playlistId);
                }
            }


            jsonNode.get("sections").forEach(section -> {
                String sectionType = section.get("sectionType").asText();
                switch (sectionType) {
                    case "song":
                        section.get("items").forEach(item -> {
                            String songId = item.get("encodeId").asText();
                            if (!songRepository.existsById(songId)) {
                                getDataFromSong(songId);
                            }
                        });
                        break;
                    case "playlist":
                        section.get("items").forEach(item -> {
                            String playlistOrAlbumId = item.get("encodeId").asText();
                            if (!playlistRepository.existsById(playlistOrAlbumId)) {
                                playlistRepository.save(
                                        PlaylistOrAlbum
                                                .builder()
                                                .id(playlistOrAlbumId)
                                                .build()
                                );
                                getDataFromPlaylist(playlistOrAlbumId);
                            }
                        });
                        break;
                }
            });
        } catch (HttpServerErrorException.InternalServerError e) {
            artistErrorRepository.save(
                    ArtistError
                        .builder()
                        .id(artistAlias)
                        .build()
            );
            log.error("Error when get data from artist: {}", artistAlias);
//            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } finally {
//            fileManager.saveData(songList, "data\\artistOrComposer.txt");
//            fileManager.saveData(errorArtistAlias, "data\\errorArtistAlias.txt");
        }
    }

    public void getDataFromSong(String songId) {

        if (songErrorRepository.existsById(songId)) {
            return;
        }

        if (songRepository.existsById(songId)) {
            return;
        } else {
            songRepository.save(
                    Song.builder()
                            .id(songId)
                            .build()
            );
        }

        try {
            String song = restTemplate.getForObject("http://localhost:3000/test/getFullInfo/" + songId, String.class);
            JsonNode jsonNode = objectMapper.readTree(song);

            jsonNode.get("artists").forEach(artist -> {
                String artistAlias = artist.get("alias").asText();
                if (!artistRepository.existsById(artistAlias)) {
                    getDataFromArtist(artistAlias);
                }
            });

            if (jsonNode.get("album") != null && jsonNode.get("album").get("encodeId") != null) {
                getDataFromPlaylist(jsonNode.get("album").get("encodeId").asText());
            }
        } catch (HttpServerErrorException.InternalServerError e) {
            songErrorRepository.save(
                    SongError.builder()
                            .id(songId)
                            .build()
            );
            log.error("Error when get data from song: {}", songId);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } finally {
//            fileManager.saveData(songList, "data\\song.txt");
//            fileManager.saveData(errorSongId, "data\\errorSongId.txt");
        }
    }
}
