package hcmus.zingmp3.content_delivery.controller;

import hcmus.zingmp3.content_delivery.service.component.FileTypeDetector;
import hcmus.zingmp3.content_delivery.service.component.ObjectValidator;
import hcmus.zingmp3.content_delivery.model.dto.ImageFileDataDTO;
import hcmus.zingmp3.content_delivery.model.enums.ObjectType;
import hcmus.zingmp3.content_delivery.service.ImageFileDataService;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/content-delivery/thumbnail/")
public class ImageFileDataController {
    private final String FILE_PATH_FORMAT = "%s/%s/";
    private final ObjectValidator objectValidator;
    private final FileTypeDetector fileTypeDetector;
    private final ImageFileDataService imageFileDataService;

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<ImageFileDataDTO>> getAllImageFileData(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @Schema(description = "Field to sort by", example = "id", allowableValues = {"id", "name", "type", "size", "object"})
            @RequestParam(defaultValue = "id") String sort,
            @Schema(description = "Sort order", example = "asc", allowableValues = {"asc", "desc"})
            @RequestParam(defaultValue = "asc") String order
    ) {
        Sort sortData = Sort.by(Sort.Direction.fromString(order), sort);
        Pageable pageable = PageRequest.of(page, size, sortData);
        return ResponseEntity
                .ok(imageFileDataService
                        .getAllImageFile(pageable));
    }

    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ImageFileDataDTO> getImageFileDataById(
            @PathVariable UUID id
    ) {
        return ResponseEntity
                .ok(imageFileDataService
                        .getImageFileData(id));
    }

    @PostMapping(
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ImageFileDataDTO> createImageFileData(
            @Schema(allowableValues = { "song", "artist", "playlist" })
            @RequestPart String object,
            @RequestPart MultipartFile file
            ) throws IOException {
        objectValidator.validateObject(object);
        if (fileTypeDetector.isImageFile(file)) {
            ImageFileDataDTO imageFileDataDTO = buildImageFileDataDTO(file, object);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(imageFileDataService
                            .createImageFileData(imageFileDataDTO));
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }

    @PutMapping(
            value = "/{id}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ImageFileDataDTO> updateImageFileData(
            @PathVariable UUID id,
            @Schema(allowableValues = { "song", "artist", "playlist" })
            @RequestPart String object,
            @RequestPart MultipartFile file
    ) throws IOException {
        objectValidator.validateObject(object);
        if (fileTypeDetector.isImageFile(file)) {
            ImageFileDataDTO imageFileDataDTO = buildImageFileDataDTO(file, object);
            return ResponseEntity
                    .ok(imageFileDataService
                            .updateImageFileData(id, imageFileDataDTO));
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }

    @DeleteMapping(
            value = "/{id}"
    )
    public ResponseEntity<Void> deleteImageFileData(
            @PathVariable UUID id
    ) {
        imageFileDataService
                .deleteImageFileData(id);
        return ResponseEntity
                .noContent()
                .build();
    }

    private ImageFileDataDTO buildImageFileDataDTO(MultipartFile file, String object) {
        String currentDate = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        String filePath = String.format(FILE_PATH_FORMAT, object, currentDate);
        return ImageFileDataDTO
                .builder()
                .object(ObjectType.valueOf(object.toUpperCase()))
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .size(file.getSize())
                .file(file)
                .path(filePath)
                .build();
    }

    @PostMapping(value = "/test", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> test(
            @RequestPart("files") List<MultipartFile> files
    ) {
        for (MultipartFile file : files) {
            System.out.println(file.getOriginalFilename());
        }
        return ResponseEntity.ok("Test");
    }
}
