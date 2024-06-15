package hcmus.mp3.image.service;

import hcmus.mp3.image.dto.ImageResponseDto;
import hcmus.mp3.image.dto.mapper.ImageMapper;
import hcmus.mp3.image.exception.CannotSaveFile;
import hcmus.mp3.image.exception.NotAnImageException;
import hcmus.mp3.image.exception.ResourceNotFoundException;
import hcmus.mp3.image.model.Image;
import hcmus.mp3.image.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static hcmus.mp3.image.ImageServiceApplication.IMAGE_PATH;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageMapper imageMapper;
    private final ImageRepository imageRepository;

    private boolean isImage(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && contentType.startsWith("image");
    }

    @Override
    public ImageResponseDto uploadImage(MultipartFile file, boolean replace) {

        // todo check the image file
        if (!isImage(file)) {
            throw new NotAnImageException("File is not an image");
        }

        String originalFilename = file.getOriginalFilename();
        String path = IMAGE_PATH + originalFilename;
        File newFile = new File(path);
        if (newFile.exists() && !replace) {
            String filenameWithoutExtension = originalFilename.substring(0, originalFilename.lastIndexOf('.'));
            String extension = originalFilename.substring(originalFilename.lastIndexOf('.'));

            originalFilename = filenameWithoutExtension + System.currentTimeMillis() + extension;
            path = IMAGE_PATH + originalFilename;
            newFile = new File(path);
        }


        try {
            file.transferTo(newFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            throw new CannotSaveFile("Cannot save file");
        }

        var image = Image.builder()
                .name(originalFilename)
                .type(file.getContentType())
                .size(file.getSize())
                .path(path)
                .build();

        return imageMapper.toDto(
                imageRepository.findByPath(path)
                        .orElse(imageRepository.save(image)));
    }

    @Override
    public ImageResponseDto getImageById(UUID imageId) {
        return imageMapper.toDto(
                imageRepository.findById(imageId)
                        .orElseThrow(() -> new ResourceNotFoundException(
                                String.format("Image with the provided id: %s not found", imageId))
                        )
        );
    }

    @Override
    public List<ImageResponseDto> getAllImages() {
        return imageMapper.toDtos(imageRepository.findAll());
    }

    @Override
    public ImageResponseDto updateImage(UUID imageId, MultipartFile file) {
        var image = imageRepository.findById(imageId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Image with the provided id: %s not found", imageId))
                );

        return null;
    }

    @Override
    public Image getByID(UUID uuid) {
        return imageRepository.findById(uuid)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Image with the provided id: %s not found", uuid))
                );
    }

    @Override
    public void deleteById(UUID uuid) {
        var image = imageRepository.findById(uuid)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Image with the provided id: %s not found", uuid))
                );

        imageRepository.delete(image);
    }
}
