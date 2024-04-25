package zingmp3.hcmus.playlistservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import zingmp3.hcmus.playlistservice.entity.Playlist;
import zingmp3.hcmus.playlistservice.entity.Song;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PlaylistRepository extends JpaRepository<Playlist, UUID> {
//    public List<Playlist> findPlaylistByOwnerId(long ownerId);
//    public boolean existsByNameIgnoreCase(String name);

    List<Playlist> findAllByCreatedBy(UUID userId);
}
