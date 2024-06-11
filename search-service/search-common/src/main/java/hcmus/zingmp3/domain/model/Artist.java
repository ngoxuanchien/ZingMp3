package hcmus.zingmp3.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.UUID;

@Document(indexName = "artist")
public class Artist {
    @Id
    private UUID id;

    private String alias;
    private String name;
    private String realName;


    @Field(type = FieldType.Keyword)
    private ArtistStatus status;


}
