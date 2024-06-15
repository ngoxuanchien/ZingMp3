package hcmus.zingmp3.service.album;

import hcmus.zingmp3.common.domain.model.Album;
import hcmus.zingmp3.common.domain.model.AlbumStatus;
import hcmus.zingmp3.common.service.QueryService;
import hcmus.zingmp3.service.CommandService;
import hcmus.zingmp3.web.dto.AlbumRequest;
import hcmus.zingmp3.web.dto.AlbumResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface AlbumService extends QueryService<Album>, CommandService<Album> {
    // CREATE
    AlbumResponse createAlbum(AlbumRequest request);

    // READ
    AlbumResponse getAlbumByAlias(String alias);

    AlbumResponse getAlbumById(UUID id);

    List<AlbumResponse> getAllAlbums();

    List<AlbumResponse> getAllAlbums(Pageable pageable);

    // UPDATE
    AlbumResponse updateAlbum(AlbumRequest request);

    void approvedAlbum(String alias);

    void rejectedAlbum(String alias);

    void releasedAlbum(String alias);

    // DELETE
    void deleteAlbum(UUID id);

    Page<AlbumResponse> getMyAlbums(Pageable pageable);

    Page<AlbumResponse> searchAlbum(String title, Pageable pageable);

    Page<AlbumResponse> searchMyAlbums(String title, List<AlbumStatus> status, Pageable pageable);

    Page<AlbumResponse> getMyAlbums(List<AlbumStatus> status, Pageable pageable);
}
