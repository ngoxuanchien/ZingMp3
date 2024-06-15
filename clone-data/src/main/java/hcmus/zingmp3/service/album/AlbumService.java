package hcmus.zingmp3.service.album;

import hcmus.zingmp3.dto.album.AlbumRequest;
import hcmus.zingmp3.dto.album.AlbumResponse;

import java.util.UUID;

public interface AlbumService {
    AlbumResponse createAlbum(AlbumRequest albumRequest);

    void deleteAlbum(UUID albumId);
}
