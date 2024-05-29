package hcmus.zingmp3.award;

import hcmus.zingmp3.award.consumer.AwardKafkaConsumer;
import hcmus.zingmp3.award.model.AwardRestResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/music/award")
@RequiredArgsConstructor
public class AwardController {

    private final AwardService awardService;
    private final AwardKafkaConsumer consumer;

    @GetMapping("/{awardName}")
    public ResponseEntity<AwardRestResponse> getAwardByName(@PathVariable String awardName) {
        return ResponseEntity.ok(awardService.getAwardByName(awardName));
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
//        Sort sortData = Sort.by(Sort.Direction.fromString(order), sort);
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(awardService.getAllAwards(pageable));
    }

    @GetMapping("/all")
    public ResponseEntity<List<AwardRestResponse>> getAllAwards() {
        return ResponseEntity.ok(awardService.getAllAwards());
    }
}
