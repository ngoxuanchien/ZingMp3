package hcmus.zingmp3.domain.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "song")
public class Song {
    @Id
    private UUID id;

    private String title;

    @Field(type = FieldType.Nested, includeInParent = true)
    private Set<Artist> artists;

    @Field(type = FieldType.Nested, includeInParent = true)
    private Set<Genre> genres;

    @Field(type = FieldType.Nested, includeInParent = true)
    private Set<Artist> composers;

    private String status;

    private Album album;
}
