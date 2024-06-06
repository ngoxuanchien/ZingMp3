package hcmus.mp3.image.controller;

import hcmus.mp3.image.dto.ImageResponseDto;
import hcmus.mp3.image.service.ImageService;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ImageResponseDto> uploadImage(
            @RequestPart("image") MultipartFile image
    ) {
        return ResponseEntity.status(CREATED)
                .body(imageService.uploadImage(image));
    }

    @GetMapping("/{image-id}")
    public ResponseEntity<ImageResponseDto> getImage(
            @PathVariable("image-id") UUID imageId
    ) {
        return ResponseEntity.ok(imageService.getImageById(imageId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ImageResponseDto>> getAllImages() {
        return ResponseEntity.ok(imageService.getAllImages());
    }

    @PutMapping(value = "/{image-id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ImageResponseDto> updateImage(
            @PathVariable("image-id") UUID imageId,
            @RequestPart("image") MultipartFile image
    ) {
        return ResponseEntity.status(ACCEPTED)
                .body(imageService.updateImage(imageId, image));
    }
}
