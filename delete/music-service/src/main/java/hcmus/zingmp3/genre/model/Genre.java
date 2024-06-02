package hcmus.zingmp3.genre.model;

import hcmus.zingmp3.song.model.Song;
import hcmus.zingmp3.song.model.Song;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(columnDefinition = "nvarchar(255) default 'No name'")
    @Builder.Default
    private String name = "No name";

    @Column(columnDefinition = "nvarchar(255) default 'No title'")
    @Builder.Default
    private String title = "No title";

    @Column(unique = true, columnDefinition = "nvarchar(255)", nullable = false)
    private String alias;

    @ManyToMany(cascade ={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "song_genre",
            joinColumns = @JoinColumn(name = "genre_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id")
    )
    private List<Song> songs;
}
