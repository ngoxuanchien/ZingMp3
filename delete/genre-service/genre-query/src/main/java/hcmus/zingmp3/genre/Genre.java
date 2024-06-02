package hcmus.zingmp3.genre;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "genres")
public class Genre {
    @Id
    private UUID id;
    private String name;
    private String title;
    private String alias;
    private Set<UUID> songs;
    private UUID createdBy;
    private long createdAt;
    private UUID modifiedBy;
    private long modifiedAt;
}
