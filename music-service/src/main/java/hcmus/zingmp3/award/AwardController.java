package hcmus.zingmp3.award;

import hcmus.zingmp3.award.model.AwardRequest;
import hcmus.zingmp3.award.model.AwardResponse;
import hcmus.zingmp3.award.model.AwardRequest;
import hcmus.zingmp3.award.model.AwardResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/music/award", produces = MediaType.APPLICATION_JSON_VALUE)
public class AwardController {
    private final AwardService awardService;

    @PostMapping
    public ResponseEntity<AwardResponse> createAward(@RequestBody @Valid AwardRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(awardService.createAward(request));
    }

    @GetMapping
    public ResponseEntity<List<AwardResponse>> getAllAwards(
            @RequestParam(defaultValue = "0") @Min(0) Integer page,
            @RequestParam(defaultValue = "10") @Min(1) Integer size,
            @Schema(description = "id, name", allowableValues = {"id", "name"})
            @RequestParam(defaultValue = "id") String sort,
            @Schema(description = "asc or desc", allowableValues = {"asc", "desc"})
            @RequestParam(defaultValue = "asc") String order
    ) {
        Sort sortData = Sort.by(Sort.Direction.fromString(order), sort);
        Pageable pageable = PageRequest.of(page, size, sortData);
        return ResponseEntity.ok(awardService.getAllAwards(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AwardResponse> getAwardById(@PathVariable UUID id) {
        return ResponseEntity.ok(awardService.getAwardById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AwardResponse> updateAward(@PathVariable UUID id, @RequestBody @Valid AwardRequest awardDTO) {
        return ResponseEntity.ok(awardService.updateAward(id, awardDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAward(@PathVariable UUID id) {
        awardService.deleteAward(id);
        return ResponseEntity.noContent().build();
    }
}
