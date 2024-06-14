package hcmus.zingmp3.repository.elasticsearch;

import hcmus.zingmp3.domain.model.Playlist;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.UUID;

public interface PlaylistRepository extends ElasticsearchRepository<Playlist, UUID> {
}
