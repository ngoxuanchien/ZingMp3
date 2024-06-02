package hcmus.zingmp3.song;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SongRepository extends ElasticsearchRepository<Song, UUID> {

}
