package hcmus.zingmp3.service.song;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import hcmus.zingmp3.dto.song.SongRequest;

import java.util.List;
import java.util.UUID;

public interface SongCloneService {

    SongRequest cloneSong(SongRequest songRequest);

    List<UUID> cloneSong(JsonArray jsonArray);

    UUID cloneSong(JsonObject jsonObject);

    void cloneSong(String songId);
}
