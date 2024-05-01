//package hcmus.zingmp3.adapter;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.TypeAdapter;
//import com.google.gson.stream.JsonReader;
//import com.google.gson.stream.JsonToken;
//import com.google.gson.stream.JsonWriter;
//import hcmus.zingmp3.craw.CrawDataV2;
//import hcmus.zingmp3.model.Artist;
//import hcmus.zingmp3.model.Genre;
//import hcmus.zingmp3.model.Playlist;
//import hcmus.zingmp3.model.Song;
//
//import java.io.IOException;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.HashSet;
//import java.util.Set;
//
//public class PlaylistTypeAdapter extends TypeAdapter<Playlist> {
//
//    private final CrawDataV2 crawDataV2;
//    private final Gson gson;
//
//    public PlaylistTypeAdapter(CrawDataV2 crawDataV2) {
//        this.crawDataV2 = crawDataV2;
//        gson = new GsonBuilder()
//                .registerTypeAdapter(Artist.class, new ArtistTypeAdapter(crawDataV2))
//                .registerTypeAdapter(Genre.class, new GenreTypeAdapter(crawDataV2))
//                .create();
//    }
//
//    @Override
//    public void write(JsonWriter out, Playlist value) throws IOException {
//
//    }
//
//    @Override
//    public Playlist read(JsonReader in) throws IOException {
//        if (in.peek() == JsonToken.NULL) {
//            in.nextNull();
//            return null;
//        }
//
//        Playlist playlist = new Playlist();
//        in.beginObject();
//        while (in.hasNext()) {
//            String name = in.nextName();
//            switch (name) {
//                case "title":
//                    playlist.setTitle(in.nextString());
//                    break;
//                case "thumbnailM":
//                    playlist.setThumbnail(in.nextString());
//                    break;
//                case "isoffical":
//                    playlist.setOfficial(in.nextBoolean());
//                    break;
//                case "link":
//                    playlist.setLink(in.nextString());
//                    break;
//                case "releaseDate":
//                    String releaseDate = in.nextString();
//                    if (releaseDate != null && !releaseDate.isEmpty()) {
//                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//                        LocalDate date = LocalDate.parse(releaseDate, formatter);
//                        playlist.setReleaseDate(date);
//                    }
//                    break;
//                case "sortDescription":
//                    playlist.setSortDescription(in.nextString());
//                    break;
//                case "releasedAt":
//                    playlist.setReleasedAt(in.nextInt());
//                    break;
//                case "artists":
//                    in.beginArray();
//                    Set<Artist> artists = new HashSet<>();
//                    while (in.hasNext()) {
//                        in.beginObject();
//                        Artist artist = gson.fromJson(in, Artist.class);
//                        if (artist != null) {
//                            artists.add(artist);
//                        }
//                        in.endObject();
//                    }
//                    playlist.setArtists(artists);
//                    in.endArray();
//                    break;
//                case "artistsNames":
//                    playlist.setArtistsNames(in.nextString());
//                    break;
//                case "genres":
//                    in.beginArray();
//                    Set<Genre> genres = new HashSet<>();
//                    while (in.hasNext()) {
//                        Genre genre = crawDataV2.getGenre(in.nextString());
//                        if (genre != null) {
//                            genres.add(genre);
//                        }
//                    }
//                    in.endArray();
//                    playlist.setGenres(genres);
//                    break;
//                case "isPrivate":
//                    playlist.setPrivate(in.nextBoolean());
//                    break;
//                case "isAlbum":
//                    playlist.setAlbum(in.nextBoolean());
//                    break;
//                case "textType":
//                    playlist.setTextType(in.nextString());
//                    break;
//                case "distributor":
//                    playlist.setDistributor(in.nextString());
//                    break;
//                case "description":
//                    playlist.setDescription(in.nextString());
//                    break;
//                case "aliasTitle":
//                    String alias = in.nextString();
//                    Playlist newPlaylist = crawDataV2.findPlaylistByAlias(alias);
//                    if (newPlaylist != null) {
//                        return newPlaylist;
//                    }
//                    playlist.setAliasTitle(alias);
//                    break;
//                case "like":
//                    playlist.setLike(in.nextInt());
//                    break;
//                case "listen":
//                    playlist.setListen(in.nextInt());
//                    break;
//                case "song":
//                    in.beginObject();
//                    Set<Song> songs = new HashSet<>();
//                    while (in.hasNext()) {
//                        String item = in.nextString();
//                        if (item.equals("items")) {
//                            in.beginArray();
//                            while (in.hasNext()) {
//                                Song song = gson.fromJson(in, Song.class);
//                                if (song != null) {
//                                    songs.add(song);
//                                }
//                            }
//                            in.endArray();
//                            break;
//                        } else {
//                            in.skipValue();
//                        }
//                    }
//                    in.endObject();
//                    playlist.setSongs(songs);
//                    break;
//                default:
//                    in.skipValue();
//                    break;
//            }
//        }
//
//        return null;
//    }
//}
