package hcmus.zingmp3.service.genre;

import hcmus.zingmp3.domain.model.Genre;
import hcmus.zingmp3.service.QueryService;

import java.util.List;

public interface GenreQueryService extends QueryService<Genre> {
    List<Genre> getAllByNameContaining(String name);
}
