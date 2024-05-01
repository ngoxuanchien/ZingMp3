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

@Table(name = "streaming")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StreamingEntity implements Persistable<String> {
    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();

    @Column("url_128kps")
    private String url128kps;
    @Column("url_320kps")
    private String url320kps;

    @Transient
    private boolean isNew = false;

    @Override
    public boolean isNew() {
        return this.isNew;
    }

    public StreamingEntity setAsNew() {
        this.isNew = true;
        return this;
    }

    public StreamingEntity setUrl128kps(String url) {
        this.url128kps = url;
        return this;
    }

    public StreamingEntity setUrl320kps(String url) {
        this.url320kps = url;
        return this;
    }
}
