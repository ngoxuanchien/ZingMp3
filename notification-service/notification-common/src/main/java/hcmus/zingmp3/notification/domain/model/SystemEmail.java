package hcmus.zingmp3.notification.domain.model;

import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SystemEmail {
    private UUID to;

    private String subject;

    @JdbcTypeCode(SqlTypes.LONGVARCHAR)
    private String body;
}
