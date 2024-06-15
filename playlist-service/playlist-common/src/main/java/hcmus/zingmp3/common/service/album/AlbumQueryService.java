package hcmus.zingmp3.common.service.album;

import hcmus.zingmp3.common.domain.model.Album;
import hcmus.zingmp3.common.domain.model.AlbumStatus;
import hcmus.zingmp3.common.service.QueryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface AlbumQueryService extends QueryService<Album> {
    Page<Album> getMyAlbums(UUID userId, Pageable pageable);

    Page<Album> searchAlbum(String title, Pageable pageable);

    Page<Album> searchMyAlbums(String title, List<AlbumStatus> status, UUID userId, Pageable pageable);

    Page<Album> getMyAlbums(List<AlbumStatus> status, UUID userId, Pageable pageable);
}
