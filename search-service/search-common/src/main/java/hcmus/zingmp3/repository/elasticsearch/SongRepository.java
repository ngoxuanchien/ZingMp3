package hcmus.zingmp3.repository.elasticsearch;

import hcmus.zingmp3.domain.model.Song;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.UUID;

public interface SongRepository extends ElasticsearchRepository<Song, UUID> {
//    @Nonnull
//    @Query("{\"bool\": {\"must\": {\"term\": {\"id\": \"?0\"}}, \"must_not\": {\"terms\": {\"status\": [\"REJECT\", \"APPROVED_PENDING\"]}}]}}")
//    @Override
//    Optional<Song> findById(@Nonnull UUID id);
//
//    @Override
//    @Nonnull
//    @Query("{\"bool\": {\"must_not\": {\"term\": {\"status\": [\"REJECT\", \"APPROVED_PENDING\"]}}}}")
//    List<Song> findAll();

    List<Song> findByTitleContaining(String name);
}
