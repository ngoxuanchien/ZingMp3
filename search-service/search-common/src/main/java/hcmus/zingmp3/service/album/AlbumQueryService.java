package hcmus.zingmp3.service.album;

import hcmus.zingmp3.domain.model.Album;
import hcmus.zingmp3.service.QueryService;

import java.util.List;

public interface AlbumQueryService extends QueryService<Album> {
    List<Album> getAlbumsByTitle(String title);
}
