package hcmus.zingmp3.repository.elasticsearch;

import hcmus.zingmp3.domain.model.Artist;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.UUID;

public interface ArtistRepository extends ElasticsearchRepository<Artist, UUID> {
    List<Artist> findAllByNameContaining(String name);
}
