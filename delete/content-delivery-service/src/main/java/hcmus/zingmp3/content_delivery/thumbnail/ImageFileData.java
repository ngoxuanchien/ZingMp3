package hcmus.zingmp3.content_delivery.thumbnail;

import hcmus.zingmp3.content_delivery.model.enums.ObjectType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
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

    @CreatedBy
    @Column(
            nullable = false,
            updatable = false
    )
    private UUID createdBy;

    @CreatedDate
    @Column(
            nullable = false,
            updatable = false
    )
    private LocalDateTime createdDate;

    @LastModifiedBy
    @Column(insertable = false)
    private UUID modifiedBy;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime modifiedDate;
}
