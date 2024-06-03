package hcmus.mp3.image.dto;

import java.util.UUID;

public record ImageResponseDto(
        UUID id,
        String name,
        String type,
        String path,
        long size,
        String url
) {
}
