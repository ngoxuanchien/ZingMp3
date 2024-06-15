package hcmus.zingmp3.common.service.album;

import hcmus.zingmp3.common.domain.model.Album;
import hcmus.zingmp3.common.domain.model.AlbumStatus;
import hcmus.zingmp3.common.service.QueryService;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface AlbumQueryService extends QueryService<Album> {
    List<Album> getMyAlbums(UUID userId, Pageable pageable);

    List<Album> searchAlbum(String title, Pageable pageable);

    List<Album> searchMyAlbums(String title, List<AlbumStatus> status, UUID userId, Pageable pageable);
}
