package zingmp3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zingmp3.domain.model.Distributor;

import java.util.UUID;

public interface DistributorRepository extends JpaRepository<Distributor, UUID> {
}
