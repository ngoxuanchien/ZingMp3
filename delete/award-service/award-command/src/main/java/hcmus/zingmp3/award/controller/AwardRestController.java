package hcmus.zingmp3.award.controller;

import hcmus.zingmp3.award.dto.AwardRestRequest;
import hcmus.zingmp3.award.dto.AwardRestResponse;
import hcmus.zingmp3.award.service.AwardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/award")
@RequiredArgsConstructor
public class AwardRestController {

    private final AwardService awardService;

    @PostMapping
    public ResponseEntity<AwardRestResponse> createAward(
            @RequestBody @Valid AwardRestRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(awardService.createAward(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AwardRestResponse> updateAward(
            @PathVariable UUID id,
            @RequestBody @Valid AwardRestRequest request
    ) {
        return ResponseEntity.ok(awardService.updateAward(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAward(
            @PathVariable UUID id
    ) {
        awardService.deleteAward(id);
        return ResponseEntity.noContent().build();
    }
}
