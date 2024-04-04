package zingmp3.hcmus.playlistservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import zingmp3.hcmus.playlistservice.entity.Playlist;
import zingmp3.hcmus.playlistservice.entity.Song;

import java.util.List;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    public List<Playlist> findPlaylistByOwnerId(long ownerId);
    public boolean existsByNameIgnoreCase(String name);
}
