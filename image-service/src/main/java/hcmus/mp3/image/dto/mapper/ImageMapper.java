package hcmus.mp3.image.dto.mapper;

import hcmus.mp3.image.dto.ImageResponseDto;
import hcmus.mp3.image.model.Image;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ImageMapper {
    @Value("${image.url}")
    private String url;

    public ImageResponseDto toDto(Image image) {
        return new ImageResponseDto(
                image.getId(),
                image.getName(),
                image.getType(),
                image.getPath(),
                image.getSize(),
                url + image.getName()
        );
    }

    public List<ImageResponseDto> toDtos(List<Image> images) {
        return images
                .stream()
                .map(this::toDto)
                .toList();
    }
}
