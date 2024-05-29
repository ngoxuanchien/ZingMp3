package hcmus.zingmp3.award;

import hcmus.zingmp3.award.model.Award;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AwardRepository extends ElasticsearchRepository<Award, String> {
    Optional<Award> findByName(String name);
}
