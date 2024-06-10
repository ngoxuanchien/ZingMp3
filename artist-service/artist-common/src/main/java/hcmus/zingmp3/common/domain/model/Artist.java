package hcmus.zingmp3.common.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "artist")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Artist {
    @Id
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID thumbnailId;

    @Column(unique = true)
    private String alias;
    private String name;
    private String realName;

    @Enumerated(EnumType.STRING)
    @Setter(AccessLevel.NONE)
    @Builder.Default
    private ArtistStatus status = ArtistStatus.APPROVAL_PENDING;

    @CreatedBy
    @Column(
            updatable = false,
            nullable = false
    )
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID createdBy;

    @CreatedDate
    @Column(
            updatable = false,
            nullable = false
    )
    private LocalDateTime createDate;

    @LastModifiedBy
    @Column(insertable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID lastModifiedBy;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModifiedDate;

    public void approve() {
        this.status = ArtistStatus.APPROVED;
    }

    public void reject() {
        this.status = ArtistStatus.REJECTED;
    }
}
