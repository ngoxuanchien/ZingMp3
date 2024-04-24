package zingmp3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zingmp3.model.Genre;

import java.util.UUID;

@Repository
public interface GenreRepository extends JpaRepository<Genre, UUID> {
    boolean existsByName(String name);
    Genre findByName(String name);
}
