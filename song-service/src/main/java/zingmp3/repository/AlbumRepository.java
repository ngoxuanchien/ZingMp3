package zingmp3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zingmp3.model.Album;

import java.util.UUID;

public interface AlbumRepository extends JpaRepository<Album, UUID> {
    boolean existsAlbumByTitle(String title);

    Album findAlbumByTitle(String title);
}
