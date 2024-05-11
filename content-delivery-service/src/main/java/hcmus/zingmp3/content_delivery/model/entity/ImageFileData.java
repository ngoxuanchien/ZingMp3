package hcmus.zingmp3.content_delivery.model.entity;

import hcmus.zingmp3.content_delivery.model.enums.ObjectType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class ImageFileData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private ObjectType object;
    private String name;
    private String type;
    private String path;
    private long size;

    @Transient
    private MultipartFile file;
}
