package hcmus.zingmp3.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Document(indexName = "genre")
public class Genre {
    @Id
    private UUID id;

    private String name;
}
