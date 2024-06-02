package hcmus.zingmp3.song;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "songs")
public class Song {
    @Id
    private UUID id;

    private String title;

    private String alias;

    private boolean isOfficial;

    private boolean isWorldWide;

    @Field(type = FieldType.Keyword)
    private UUID thumbnail;

    private int duration;

    private boolean isPrivate;

    private int releaseDate = 0;

    private String distributor;

    private boolean hasLyric;

    private int likes = 0;

    private int listen = 0;

    private int comment = 0;

    @Field(type = FieldType.Keyword)
    private Set<UUID> artists;

    @Field(type = FieldType.Keyword)
    private Set<UUID> genres;

    @Field(type = FieldType.Keyword)
    private Set<UUID> composers;

    @Field(type = FieldType.Keyword)
    private Set<UUID> audios;
}
