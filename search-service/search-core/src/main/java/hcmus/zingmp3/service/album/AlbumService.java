package hcmus.zingmp3.service.album;

import hcmus.zingmp3.web.model.response.AlbumResponse;

import java.util.List;
import java.util.UUID;

public interface AlbumService {
    AlbumResponse getAlbumById(UUID id);

    List<AlbumResponse> getAllAlbums();

    List<AlbumResponse> getAllAlbumsByTitle(String title);

}
