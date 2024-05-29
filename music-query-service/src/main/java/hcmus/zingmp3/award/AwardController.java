package hcmus.zingmp3.award;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/music/award")
@RequiredArgsConstructor
public class AwardController {

    private final AwardService awardService;

    @GetMapping("/{awardName}")
    public ResponseEntity<AwardRestResponse> getAwardByName(@PathVariable String awardName) {
        return ResponseEntity.ok(awardService.getAwardByName(awardName));
    }
}
