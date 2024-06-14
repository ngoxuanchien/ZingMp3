package hcmus.zingmp3.domain.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "playlist")
public abstract class AbstractPlaylist {
    @Id
    private UUID id;

    private String title;

    private Set<Artist> artists;

    private Set<Song> songs;

    private boolean isAlbum;
}
