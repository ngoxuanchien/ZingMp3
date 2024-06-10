package zingmp3.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public ResponseEntity<?> register(
            @ModelAttribute @Validated(OnCreate.class) DistributorRequest newDistributor
    ) {
        distributorService.register(newDistributor);
        return ResponseEntity.ok().build();
    }

}
