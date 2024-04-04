package zingmp3.hcmus.playlistservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;

@Entity
@Table(name = "song")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Song {
    @Id
    private long songId;
    private int songOrder;

    @Id
    @ManyToOne
    @JoinColumn(name = "playlist_id", foreignKey = @ForeignKey(name = "FK_song_playlist"))
    private Playlist playlist;

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Song)) return false;

        return ((Song) other).songId == this.songId;
    }
}
