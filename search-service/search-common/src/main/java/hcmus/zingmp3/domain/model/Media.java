package hcmus.zingmp3.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.UUID;

@Document(indexName = "media")
@Getter
@Setter
@Builder
public class Media {
    @Id
    private UUID id;
    private Integer quality;
    private String url;
}
