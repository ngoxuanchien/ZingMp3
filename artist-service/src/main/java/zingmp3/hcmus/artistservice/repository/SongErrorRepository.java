package zingmp3.hcmus.artistservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import zingmp3.hcmus.artistservice.model.error.SongError;

@Repository
public interface SongErrorRepository extends JpaRepository<SongError, String> {
}
