package hcmus.mp3.image.service;

import hcmus.mp3.image.dto.ImageResponseDto;
import hcmus.mp3.image.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface ImageService {

    ImageResponseDto uploadImage(MultipartFile image, boolean replace);

    ImageResponseDto getImageById(UUID imageId);

    List<ImageResponseDto> getAllImages();

    ImageResponseDto updateImage(UUID imageId, MultipartFile image);

    Image getByID(UUID uuid);

    void deleteById(UUID uuid);
}
