package hcmus.zingmp3.music_service.user;

import hcmus.zingmp3.music_service.artist.model.Artist;
import hcmus.zingmp3.music_service.song.model.Song;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    private UUID id;

    @OneToMany
    private Set<Song> likes;

    @OneToMany
    private Set<Song> listens;

    @OneToMany
    private Set<Artist> follows;
}
