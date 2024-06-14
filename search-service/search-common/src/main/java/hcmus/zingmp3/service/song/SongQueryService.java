package hcmus.zingmp3.service.song;

import hcmus.zingmp3.domain.model.Song;
import hcmus.zingmp3.service.QueryService;

import java.util.List;

public interface SongQueryService extends QueryService<Song> {

    List<Song> getAllByTitle(String title);

}
