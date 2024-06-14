package hcmus.zingmp3.repository.elasticsearch;

import hcmus.zingmp3.domain.model.Album;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.UUID;

public interface AlbumRepository extends ElasticsearchRepository<Album, UUID> {
    List<Album> findByTitleContaining(String title);
}
