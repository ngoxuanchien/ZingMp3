package hcmus.zingmp3.artist.repository;

import hcmus.zingmp3.artist.model.Artist;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ArtistRepository extends ElasticsearchRepository<Artist, UUID> {
}
