package hcmus.zingmp3.award.controller;

import hcmus.zingmp3.award.service.AwardKafkaConsumer;
import hcmus.zingmp3.award.dto.AwardRestResponse;
import hcmus.zingmp3.award.service.AwardService;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/award")
@RequiredArgsConstructor
public class AwardController {

    private final AwardService awardService;

    @GetMapping("/name/{awardName}")
    public ResponseEntity<AwardRestResponse> getAwardByName(@PathVariable String awardName) {
        return ResponseEntity.ok(awardService.getAwardByName(awardName));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AwardRestResponse> getAwardById(@PathVariable String id) {
        return ResponseEntity.ok(awardService.getAwardById(id));
    }

    @GetMapping
    public ResponseEntity<List<AwardRestResponse>> getAllAwards(
            @RequestParam(defaultValue = "0") @Min(0) Integer page,
            @RequestParam(defaultValue = "10") @Min(1) Integer size,
            @Schema(description = "id, name", allowableValues = {"id", "name"})
            @RequestParam(defaultValue = "name") String sort,
            @Schema(description = "asc or desc", allowableValues = {"asc", "desc"})
            @RequestParam(defaultValue = "asc") String order
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(awardService.getAllAwards(pageable));
    }

    @GetMapping("/all")
    public ResponseEntity<List<AwardRestResponse>> getAllAwards() {
        return ResponseEntity.ok(awardService.getAllAwards());
    }
}
