package hcmus.zingmp3.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import hcmus.zingmp3.model.Song;

import java.io.IOException;

public class SongTypeAdapter extends TypeAdapter<Song> {
    @Override
    public void write(JsonWriter out, Song value) throws IOException {

    }

    @Override
    public Song read(JsonReader in) throws IOException {
        return null;
    }
}
