//package hcmus.zingmp3.craw;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import hcmus.zingmp3.adapter.ArtistTypeAdapter;
//import hcmus.zingmp3.adapter.GenreTypeAdapter;
//import hcmus.zingmp3.adapter.PlaylistTypeAdapter;
//import hcmus.zingmp3.model.*;
//import org.springframework.web.client.HttpServerErrorException;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.*;
//
//import static hcmus.zingmp3.Main.server;
//
//public class CrawDataV2 {
//    private final RestTemplate restTemplate = new RestTemplate();
//
//    private final String songUrl = "http://" + server + "/test/getFullInfo/";
//    private final String artistUrl = "http://" + server + "/test/getArtist/";
//    private final String playlistUrl = "http://" + server + "/test/getDetailPlaylist/";
//
//    private final Queue<String> playlistQueue = new LinkedList<>();
//    private final Set<String> playlistCraw = new HashSet<>();
//
//    private final Set<Playlist> playlistResult = new HashSet<>();
//    private final Set<Artist> artistResult = new HashSet<>();
//    private final Set<Song> songResult = new HashSet<>();
//    private final Set<Genre> genreResult = new HashSet<>();
//    private final Set<Award> awardResult = new HashSet<>();
//    private final Gson gson = new GsonBuilder()
//            .registerTypeAdapter(Artist.class, new ArtistTypeAdapter())
//            .registerTypeAdapter(Genre.class, new GenreTypeAdapter())
//            .registerTypeAdapter(Playlist.class, new PlaylistTypeAdapter(this))
//            .create();
//
//    public String getData(String url) {
//        try {
//            return restTemplate.getForObject(url, String.class);
//        } catch (HttpServerErrorException.InternalServerError e) {
//            System.out.println("Error: " + url);
//            return null;
//        }
//    }
//
//    public void craw(String playlistId) {
//        playlistCraw.add(playlistId);
//        playlistQueue.add(playlistId);
//
//        while (playlistQueue.isEmpty()) {
//
//        }
//    }
//
//    public void addPlaylist(String playlistId) {
//        if (playlistCraw.add(playlistId)) {
//            playlistQueue.add(playlistId);
//        }
//    }
//
//    public Artist getArtist(String alias) {
//        return artistResult.stream()
//                .filter(artist -> artist.getAlias().equals(alias))
//                .findFirst()
//                .orElseGet(() -> {
//                    String response = getData(artistUrl + alias);
//                    return gson.fromJson(response, Artist.class);
//                });
//    }
//
//    public Genre getGenre(String genre) {
//        Genre result = gson.fromJson(genre, Genre.class);
//        return genreResult.stream()
//                .filter(g -> g.getAlias().equals(result.getAlias()))
//                .findFirst()
//                .orElseGet(() -> {
//                    genreResult.add(result);
//                    return result;
//                });
//    }
//
//    public Playlist findPlaylistByAlias(String alias) {
//        return playlistResult.stream()
//                .filter(playlist -> playlist.getAliasTitle().equals(alias))
//                .findFirst()
//                .orElse(null);
//    }
//
//    public Genre findGenreByAlias(String alias) {
//        return genreResult.stream()
//                .filter(genre -> genre.getAlias().equals(alias))
//                .findFirst()
//                .orElse(null);
//    }
//
//    public Artist findArtistByAlias(String alias) {
//        return artistResult.stream()
//                .filter(artist -> artist.getAlias().equals(alias))
//                .findFirst()
//                .orElse(null);
//    }
//}
