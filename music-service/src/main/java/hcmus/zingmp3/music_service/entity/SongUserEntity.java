package hcmus.zingmp3.music_service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("song_user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SongUserEntity implements Persistable<String> {
    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();

    private String songId;
    private String userId;

    @Transient
    private boolean isNew = false;

    @Override
    public boolean isNew() {
        return this.isNew;
    }

    public SongUserEntity setAsNew() {
        this.isNew = true;
        return this;
    }
}
