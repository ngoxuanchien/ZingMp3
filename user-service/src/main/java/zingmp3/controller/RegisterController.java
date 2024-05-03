package zingmp3.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import zingmp3.dto.UserRegistrationRequest;
import zingmp3.service.keycloak.KeycloakUserService;

@RestController
@RequestMapping("/api/register")
@RequiredArgsConstructor
public class RegisterController {

    private final KeycloakUserService keycloakUserService;

    @PostMapping(

            consumes = {
                    MediaType.APPLICATION_FORM_URLENCODED_VALUE
            }
    )
    @Operation(
            summary = "Register a new user",
            requestBody = @RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                            schema = @Schema(implementation = UserRegistrationRequest.class)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "201", description = "User registered successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid input")
            }
    )
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@ModelAttribute UserRegistrationRequest newUser) {
        System.out.println(newUser);
        keycloakUserService.register(newUser);
    }
}
