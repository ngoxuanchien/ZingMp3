//package hcmus.zingmp3.adapter;
//
//import com.google.gson.TypeAdapter;
//import com.google.gson.stream.JsonReader;
//import com.google.gson.stream.JsonToken;
//import com.google.gson.stream.JsonWriter;
//import hcmus.zingmp3.craw.CrawDataV2;
//import hcmus.zingmp3.model.Genre;
//
//import java.io.IOException;
//
//public class GenreTypeAdapter extends TypeAdapter<Genre> {
//
//    private final CrawDataV2 crawDataV2;
//
//    public GenreTypeAdapter(CrawDataV2 crawDataV2) {
//        this.crawDataV2 = crawDataV2;
//    }
//
//    @Override
//    public void write(JsonWriter out, Genre value) throws IOException {
//
//    }
//
//    @Override
//    public Genre read(JsonReader in) throws IOException {
//        if (in.peek() == JsonToken.NULL) {
//            in.nextNull();
//            return null;
//        }
//
//        Genre genre = new Genre();
//        in.beginObject();
//        while (in.hasNext()) {
//            String name = in.nextName();
//            switch (name) {
//                case "name":
//                    genre.setName(in.nextString());
//                    break;
//                case "link":
//                    genre.setLink(in.nextString());
//                    break;
//                case "thumbnail":
//                    genre.setTitle(in.nextString());
//                    break;
//                case "alias":
//                    String alias = in.nextString();
//                    Genre newGenre = crawDataV2.findGenreByAlias(alias);
//                    if (newGenre != null) {
//                        return newGenre;
//                    }
//                    genre.setAlias(in.nextString());
//                    break;
//                default:
//                    in.skipValue();
//                    break;
//            }
//        }
//        in.endObject();
//        return genre;
//    }
//}
