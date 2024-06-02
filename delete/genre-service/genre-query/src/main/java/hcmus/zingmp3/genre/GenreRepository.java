package hcmus.zingmp3.genre;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GenreRepository extends ElasticsearchRepository<Genre, UUID> {
}
