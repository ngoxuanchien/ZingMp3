package zingmp3.service.distributor;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import zingmp3.domain.model.Distributor;
import zingmp3.repository.DistributorRepository;
import zingmp3.service.keycloak.KeycloakService;
import zingmp3.service.notification.EmailNotificationService;
import zingmp3.web.dto.DistributorRequest;
import zingmp3.web.dto.mapper.DistributorMapper;

import java.security.SecureRandom;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DistributorServiceImpl implements DistributorService {

    private final KeycloakService keycloakService;
    private final DistributorMapper mapper;
    private final DistributorRepository repository;

    private final EmailNotificationService emailNotificationService;

    private static final SecureRandom random = new SecureRandom();
    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";


    public static String generateRandomString(int length) {
        StringBuilder builder = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            builder.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
        }

        return builder.toString();
    }


    @Override
    public void register(DistributorRequest request) {
        UUID adminId = UUID.fromString(SecurityContextHolder.getContext().getAuthentication().getName());
        var password = generateRandomString(8);
        String distributorId = keycloakService.createDistributor(
                request.email(),
                request.name(),
                password
//                request.password(),
//                request.clientId(),
//                request.clientSecret()
        );

        Distributor newDistributor = mapper.toEntity(request);
        newDistributor.setId(UUID.fromString(distributorId));

        repository.save(newDistributor);
        emailNotificationService.sendEmail(
                adminId,
                "Distributor created",
                "Distributor created with id: " + distributorId + "\nEmail: " + request.email() + "\nPassword: " + password
        );
    }

    @Override
    public void delete(String email) {
        UUID distributorId = keycloakService.getUserId(email);
        repository.deleteById(distributorId);
        keycloakService.deleteUser(distributorId);
    }
}
