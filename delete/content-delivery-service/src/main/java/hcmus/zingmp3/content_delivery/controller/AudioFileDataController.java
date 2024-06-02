package hcmus.zingmp3.content_delivery.controller;

import hcmus.zingmp3.content_delivery.service.component.AudioQualityChecker;
import hcmus.zingmp3.content_delivery.service.component.FileTypeDetector;
import hcmus.zingmp3.content_delivery.model.dto.AudioFileDataDTO;
import hcmus.zingmp3.content_delivery.model.enums.ObjectType;
import hcmus.zingmp3.content_delivery.service.AudioFileDataService;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/content-delivery/audio/")
public class AudioFileDataController {

    private static final String FILE_PATH_FORMAT = "song/%d/%s/";

    private final FileTypeDetector fileTypeDetector;
    private final AudioQualityChecker audioQualityChecker;
    private final AudioFileDataService audioFileDataService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AudioFileDataDTO>> getAllAudioFileData(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @Schema(description = "Field to sort by", example = "id", allowableValues = {"id", "name", "type", "size", "bitrate"})
            @RequestParam(defaultValue = "id") String sort,
            @Schema(description = "Sort order", example = "asc", allowableValues = {"asc", "desc"})
            @RequestParam(defaultValue = "asc") String order
    ) {
        Sort.Direction direction = Sort.Direction.fromString(order);
        PageRequest pageable = PageRequest.of(page, size, Sort.by(direction, sort));
        return ResponseEntity.ok(audioFileDataService.getAllAudioFile(pageable));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AudioFileDataDTO> getAudioFileData(@PathVariable UUID id) {
        return ResponseEntity.ok(audioFileDataService.getAudioFileData(id));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AudioFileDataDTO> createAudioFileData(@RequestPart("file") MultipartFile file) {
        if (!fileTypeDetector.isAudioFile(file)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        AudioFileDataDTO audioFileDataDTO = buildAudioFileDataDTO(file);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(audioFileDataService.createAudioFileData(audioFileDataDTO));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AudioFileDataDTO> updateAudioFileData(@PathVariable UUID id, @RequestPart("file") MultipartFile file) {
        if (!fileTypeDetector.isAudioFile(file)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        AudioFileDataDTO audioFileDataDTO = buildAudioFileDataDTO(file);
        return ResponseEntity.ok(audioFileDataService.updateAudioFileData(id, audioFileDataDTO));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteAudioFileData(@PathVariable UUID id) {
        audioFileDataService.deleteAudioFileData(id);
        return ResponseEntity.noContent().build();
    }

    private AudioFileDataDTO buildAudioFileDataDTO(MultipartFile file) {
        String currentDate = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        long bitrate = audioQualityChecker.getBitrate(file);
        String filePath = String.format(FILE_PATH_FORMAT, bitrate, currentDate);
        return AudioFileDataDTO.builder()
                .object(ObjectType.AUDIO)
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .size(file.getSize())
                .file(file)
                .path(filePath)
                .bitrate(bitrate)
                .build();
    }
}