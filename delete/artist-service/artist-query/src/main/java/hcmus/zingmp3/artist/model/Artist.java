package hcmus.zingmp3.artist.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "artist")
public class Artist {
    @Id
    private UUID id;

    @Field(name = "name")
    private String name;

    @Field(name = "alias")
    private String alias;

    private UUID playlistId;

    @Field(name = "thumbnail")
    private UUID thumbnail;

    @Field(name = "totalFollow")
    private int totalFollow;

    @Field(name = "sortBiography")
    private String sortBiography;

    @Field(name = "biography")
    private String biography;

    @Field(name = "national")
    private String national;

    @Field(name = "realName")
    private String realName;

    @Field(name = "birthday", type = FieldType.Date)
    private LocalDate birthday;

    private Set<UUID> awards;

    private Set<UUID> songs;

    private Set<UUID> composedSongs;

    private UUID createdBy;
    private LocalDateTime createdAt;
    private UUID modifiedBy;
    private LocalDateTime modifiedAt;

}
