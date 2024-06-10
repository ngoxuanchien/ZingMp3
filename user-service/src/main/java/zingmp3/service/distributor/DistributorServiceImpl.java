package zingmp3.service.distributor;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zingmp3.domain.model.Distributor;
import zingmp3.repository.DistributorRepository;
import zingmp3.service.keycloak.KeycloakService;
import zingmp3.web.dto.DistributorRequest;
import zingmp3.web.dto.mapper.DistributorMapper;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DistributorServiceImpl implements DistributorService {

    private final KeycloakService keycloakService;
    private final DistributorMapper mapper;
    private final DistributorRepository repository;

    @Override
    public void register(DistributorRequest request) {
        String distributorId = keycloakService.createDistributor(
                request.email(),
                request.name(),
                request.password(),
                request.clientId(),
                request.clientSecret()
        );

        Distributor newDistributor = mapper.toEntity(request);
        newDistributor.setId(UUID.fromString(distributorId));

        repository.save(newDistributor);
    }
}
