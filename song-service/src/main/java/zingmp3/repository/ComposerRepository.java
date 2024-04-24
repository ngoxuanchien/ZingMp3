package zingmp3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zingmp3.model.Composer;

import java.util.UUID;

public interface ComposerRepository extends JpaRepository<Composer, UUID> {
    boolean existsComposerByNameAndAlias(String name, String Alias);

    Composer findComposerByNameAndAlias(String name, String Alias);
}
