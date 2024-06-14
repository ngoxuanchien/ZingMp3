package zingmp3.web.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import zingmp3.service.distributor.DistributorService;
import zingmp3.web.dto.DistributorRequest;
import zingmp3.web.dto.OnCreate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/distributors")
@Validated
public class DistributorController {

    private final DistributorService distributorService;

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @SecurityRequirement(name = "Keycloak")
    public ResponseEntity<?> register(
           @ModelAttribute @Validated(OnCreate.class) DistributorRequest newDistributor
    ) {
        distributorService.register(newDistributor);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    @SecurityRequirement(name = "Keycloak")
    public ResponseEntity<?> delete(@RequestParam @Email String email) {
        distributorService.delete(email);
        return ResponseEntity.ok().build();
    }

}
