package hcmus.zingmp3.service.genre;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import hcmus.zingmp3.dto.genre.GenreRequest;
import hcmus.zingmp3.dto.genre.GenreResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static hcmus.zingmp3.Main.genreMapper;
import static hcmus.zingmp3.Main.genreService;

public class GenreCloneServiceImpl implements GenreCloneService {
    @Override
    public List<UUID> cloneGenre(JsonArray genres) {
        List<UUID> result = new ArrayList<>();

        genres.asList().forEach(
                jsonElement ->
                        result.add(cloneGenre(jsonElement.getAsJsonObject()))
        );
        return result;
    }

    @Override
    public UUID cloneGenre(JsonObject jsonObject) {
        GenreRequest request = genreMapper.toRequest(jsonObject);

        GenreResponse response = genreService.getOrCreateIfNotExist(request);


        return response.id();
    }
}
