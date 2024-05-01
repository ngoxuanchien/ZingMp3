package hcmus.zingmp3.music_service.controller;

import hcmus.zingmp3.music_service.dto.SongDTO;
import hcmus.zingmp3.music_service.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/music/user")
@SecurityRequirement(name = "Keycloak")
public class UserController {

    private final UserService userService;

    @GetMapping("/history")
    public Flux<SongDTO> getHistory(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort,
            Principal principal
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return userService.getHistory(principal.getName(), pageable);
    }


    @PostMapping("/history/{songId}")
    public Mono<ResponseEntity<Void>> saveHistory(@PathVariable String songId, Principal principal) {
        return userService
                .saveHistory(songId, principal.getName())
                .then(Mono
                        .just(ResponseEntity
                                .ok()
                                .build()));
    }
}
