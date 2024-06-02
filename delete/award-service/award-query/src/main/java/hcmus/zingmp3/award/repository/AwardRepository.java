package hcmus.zingmp3.award.repository;

import hcmus.zingmp3.award.model.Award;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AwardRepository extends ElasticsearchRepository<Award, UUID> {
    Optional<Award> findByName(String name);

    Optional<Award> findById(String id);
}
