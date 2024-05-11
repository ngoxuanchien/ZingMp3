package hcmus.zingmp3.content_delivery.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hcmus.zingmp3.content_delivery.model.enums.ObjectType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AudioFileDataDTO {
    private UUID id;
    private ObjectType object;
    private String name;
    private String type;
    private String path;
    private long size;
    private String url;

    @JsonIgnore
    private MultipartFile file;
    private long bitrate;

}
