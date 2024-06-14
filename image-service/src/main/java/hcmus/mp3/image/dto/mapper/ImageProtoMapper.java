package hcmus.mp3.image.dto.mapper;

import hcmus.mp3.image.model.Image;
import hcmus.zingmp3.ImageResponseGrpc;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ImageProtoMapper {

    @Value("${image.url}")
    private String url;

    public UUID toUUID(String id) {
        return UUID.fromString(id);
    }

    public ImageResponseGrpc toProto(Image image) {
        return ImageResponseGrpc.newBuilder()
                .setId(image.getId().toString())
                .setUrl(url + image.getName())
                .build();
    }
}
