package hcmus.zingmp3.common.repository;

import hcmus.zingmp3.common.domain.model.AbstractPlaylist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AbstractPlaylistRepository extends JpaRepository<AbstractPlaylist, UUID> {
    boolean existsByAlias(String alias);
}
