package hcmus.mp3.image.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hcmus.mp3.image.dto.ImageResponseDto;
import hcmus.mp3.image.service.ImageService;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/images")
public class ImageController {

    private final ImageService imageService;
    private final ObjectMapper objectMapper;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @SecurityRequirement(name = "Keycloak")
    public ResponseEntity<ImageResponseDto> uploadImage(
            @RequestParam(value = "replace", required = false, defaultValue = "false") boolean replace,
            @RequestPart("image") MultipartFile image
    ) {

        return ResponseEntity.status(CREATED)
                .body(imageService.uploadImage(image, replace));
    }

    @GetMapping("/{image-id}")
    @SecurityRequirement(name = "Keycloak")
    public ResponseEntity<ImageResponseDto> getImage(
            @PathVariable("image-id") UUID imageId
    ) {
        return ResponseEntity.ok(imageService.getImageById(imageId));
    }

    @GetMapping("/all")
    @SecurityRequirement(name = "Keycloak")
    public ResponseEntity<List<ImageResponseDto>> getAllImages() {
        return ResponseEntity.ok(imageService.getAllImages());
    }

//    @SecurityRequirement(name = "Keycloak")
//    @PutMapping(value = "/{image-id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<ImageResponseDto> updateImage(
//            @PathVariable("image-id") UUID imageId,
//            @RequestPart("image") MultipartFile image
//    ) {
//        return ResponseEntity.status(ACCEPTED)
//                .body(imageService.updateImage(imageId, image));
//    }

    @SecurityRequirement(name = "Keycloak")
    @DeleteMapping("/{image-id}")
    public ResponseEntity<Void> deleteImage(
            @PathVariable("image-id") UUID imageId
    ) {
        imageService.deleteById(imageId);
        return ResponseEntity.noContent().build();
    }
}
