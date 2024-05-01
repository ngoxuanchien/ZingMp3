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

@Table(name = "artist_image")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArtistImageEntity implements Persistable<String> {
    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();

    private String artistId;
    private String name;
    private String type;
    private String filePath;

    @Transient
    @Builder.Default
    private boolean isNew = false;

    @Override
    public boolean isNew() {
        return this.isNew;
    }

    public ArtistImageEntity setAsNew() {
        this.isNew = true;
        return this;
    }
}
