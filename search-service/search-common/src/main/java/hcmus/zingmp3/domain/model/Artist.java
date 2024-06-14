package hcmus.zingmp3.domain.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "artist")
public class Artist {
    @Id
    private UUID id;

    private String name;

    private String status;

    @Field(type = FieldType.Keyword)
    private Set<Song> songs;

    @Field(type = FieldType.Keyword)
    private Set<Song> composedSongs;


}
