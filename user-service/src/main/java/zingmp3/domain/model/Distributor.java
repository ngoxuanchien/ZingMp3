package zingmp3.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Distributor {

    @Id
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    private String name;
    private String address;
    private String website;

    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID thumbnailId;

    private LocalDateTime timestamp;

    private String contract;

    public void setThumbnailId(UUID thumbnailId) {
        this.thumbnailId = Objects.requireNonNullElse(thumbnailId, UUID.fromString("00000000-0000-0000-0000-000000000000"));
    }
}
