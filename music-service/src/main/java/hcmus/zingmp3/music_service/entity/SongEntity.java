package hcmus.zingmp3.music_service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table(name = "song")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SongEntity implements Persistable<String> {
    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();
    private String title;
    private String alias;
    private boolean isOfficial;
    private String artistsNames;
    private boolean isWorldWide;
    private String thumbnail;
    private int duration;
    private boolean isPrivate;
    private int releaseDate;
    private String distributor;
    private boolean hasLyric;
    private int likes;
    private int listen;
    private boolean liked;
    private int comment;

    @Column("streaming")
    private String streamingId;

    @Transient
    private boolean isNew = false;

    @Override
    public boolean isNew() {
        return this.isNew;
    }

    public SongEntity setAsNew() {
        this.isNew = true;
        return this;
    }

    public SongEntity addStreaming(String streamingId) {
        this.streamingId = streamingId;
        return this;
    }

}
