package hcmus.zingmp3.playbackservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table(name = "song_image")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SongImageEntity implements Persistable<String> {
    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();

    private String songId;
    private String name;
    private String type;
    private String filePath;

    @Transient
    private boolean isNew = false;

    @Override
    public boolean isNew() {
        return this.isNew;
    }

    public SongImageEntity setAsNew() {
        this.isNew = true;
        return this;
    }
}
