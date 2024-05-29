package hcmus.zingmp3.award.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "award")
public class Award {
    @Id
    private String id;

    @Field(name = "name")
    private String name;

//    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "artist_award",
//            joinColumns = @JoinColumn(name = "award_id"),
//            inverseJoinColumns = @JoinColumn(name = "artist_id")
//    )
//    @Builder.Default
//    private List<Artist> artists = new ArrayList<>();
}
