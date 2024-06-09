package hcmus.mp3.web.dto;

import java.util.UUID;

public record AudioResponseDto(
        UUID id,
        String name,
        String type,
        String path,
        long size,
        long bitrate,
        String url,
        double duration
) {
}
