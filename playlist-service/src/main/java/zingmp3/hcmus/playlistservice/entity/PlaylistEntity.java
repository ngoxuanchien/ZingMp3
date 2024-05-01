package zingmp3.hcmus.playlistservice.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Persistent;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.lang.annotation.Documented;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Builder
@Data
@Table(name = "playlist")
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistEntity implements Persistable<String> {
    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();
    private String title;
    private String thumbnail;
    @Column("is_official")
    private boolean isOfficial;
    private String link;
    private LocalDate releaseDate;
    private String sortDescription;
    private int releasedAt;
    private String artistsNames;
    private boolean isPrivate;
    private boolean isAlbum;
    private String textType;
    private String distributor;
    private String description;
    private String aliasTitle;
    private int likes;
    private int listen;

    @Transient
    private boolean newPlaylist = false;

    @Override
    public boolean isNew() {
        return this.newPlaylist;
    }

    public PlaylistEntity setAsNew() {
        this.newPlaylist = true;
        return this;
    }

    public PlaylistEntity setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }
}
