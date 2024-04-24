package zingmp3.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zingmp3.dto.ComposerDto;
import zingmp3.service.ComposerService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/composers")
@RequiredArgsConstructor
public class ComposerController {

    private final ComposerService composerService;



    @GetMapping
    public ResponseEntity<List<ComposerDto>> findAll() {
        return ResponseEntity.ok(composerService.findAll());
    }

    @GetMapping("/{composerId}")
    public ResponseEntity<ComposerDto> findById(@PathVariable UUID composerId) {
        return ResponseEntity.ok(composerService.findById(composerId));
    }

    @GetMapping("/{pageNumber}/{pageSize}")
    public ResponseEntity<List<ComposerDto>> findAll(
            @PathVariable Integer pageNumber,
            @PathVariable Integer pageSize
    ) {
        return ResponseEntity.ok(composerService.findAll(pageNumber, pageSize));
    }

    @PostMapping
    @SecurityRequirement(name = "Keycloak")
    public ResponseEntity<ComposerDto> create(@RequestBody ComposerDto composerDto) {
        return ResponseEntity.ok(composerService.create(composerDto));
    }

    @PutMapping("/{composerId}")
    @SecurityRequirement(name = "Keycloak")
    public ResponseEntity<ComposerDto> update(@PathVariable UUID composerId, @RequestBody ComposerDto composerDto) {
        return ResponseEntity.ok(composerService.update(composerId, composerDto));
    }

    @DeleteMapping("/{composerId}")
    @SecurityRequirement(name = "Keycloak")
    public ResponseEntity<Void> delete(@PathVariable UUID composerId) {
        composerService.delete(composerId);
        return ResponseEntity.noContent().build();
    }
}
