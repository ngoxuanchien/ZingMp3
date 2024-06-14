package hcmus.zingmp3.repository.elasticsearch;

import hcmus.zingmp3.domain.model.Genre;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.UUID;

public interface GenreRepository extends ElasticsearchRepository<Genre, UUID> {
    List<Genre> findAllByNameContaining(String name);
}
