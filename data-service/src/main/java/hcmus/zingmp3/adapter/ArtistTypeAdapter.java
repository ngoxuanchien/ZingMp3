//package hcmus.zingmp3.adapter;
//
//import com.google.gson.TypeAdapter;
//import com.google.gson.stream.JsonReader;
//import com.google.gson.stream.JsonToken;
//import com.google.gson.stream.JsonWriter;
//import hcmus.zingmp3.craw.CrawDataV2;
//import hcmus.zingmp3.model.Artist;
//import hcmus.zingmp3.model.Award;
//
//import java.io.IOException;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.*;
//
//public class ArtistTypeAdapter extends TypeAdapter<Artist> {
//
//    private final CrawDataV2 crawDataV2;
//
//    public ArtistTypeAdapter(CrawDataV2 crawDataV2) {
//        this.crawDataV2 = crawDataV2;
//    }
//
//
//    @Override
//    public void write(JsonWriter out, Artist value) throws IOException {
//
//    }
//
//    @Override
//    public Artist read(JsonReader in) throws IOException {
//        if (in.peek() == JsonToken.NULL) {
//            in.nextNull();
//            return null;
//        }
//
//        Artist artist = new Artist();
//        in.beginObject();
//        while (in.hasNext()) {
//            String name = in.nextName();
//            switch (name) {
//                case "playlistId":
//                    crawDataV2.addPlaylist(in.nextString());
//                    break;
//                case "name":
//                    artist.setName(in.nextString());
//                    break;
//                case "link":
//                    artist.setLink(in.nextString());
//                    break;
//                case "alias":
//                    String alias = in.nextString();
//                    Artist newArtist = crawDataV2.findArtistByAlias(alias);
//                    if (newArtist != null) {
//                        return newArtist;
//                    }
//                    artist.setAlias(alias);
//                    break;
//                case "thumbnail":
//                    artist.setThumbnail(in.nextString());
//                    break;
//                case "totalFollow":
//                    artist.setTotalFollow(in.nextInt());
//                    break;
//                case "awards":
//                    Set<Award> awards = new HashSet<>();
//                    in.beginArray();
//                    while (in.hasNext()) {
//                        awards.add(Award.builder().name(in.nextString()).build());
//                    }
//                    in.endArray();
//                    artist.setAwards(awards);
//                    break;
//                case "biography":
//                    artist.setBiography(in.nextString());
//                    break;
//                case "sortBiography":
//                    artist.setSortBiography(in.nextString());
//                    break;
//                case "national":
//                    artist.setNational(in.nextString());
//                    break;
//                case "realname":
//                    artist.setRealName(in.nextString());
//                    break;
//                case "birthday":
//                    String birthday = in.nextString();
//                    if (birthday != null && !birthday.isEmpty()) {
//                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//                        LocalDate date = LocalDate.parse(birthday, formatter);
//                        artist.setBirthday(date);
//                    }
//                    break;
//                default:
//                    in.skipValue();
//                    break;
//            }
//        }
//        in.endObject();
//        return artist;
//    }
//}
