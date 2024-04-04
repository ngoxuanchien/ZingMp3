package zingmp3.hcmus.playlistservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "playlist")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", length = 100)
    private String name;
    private long ownerId;
    private boolean isPublic;

    @OneToMany(mappedBy = "playlist", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Song> songs;

    private boolean isRandomPlaying;
    private LocalDateTime updatedAt;
}
