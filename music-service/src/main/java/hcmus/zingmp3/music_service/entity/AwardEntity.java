package hcmus.zingmp3.music_service.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.util.function.Predicate;

@Table("award")
@Builder
@Data
public class AwardEntity implements Persistable<String> {
    @Id
    @Builder.Default
    private String id;

    private String artistId;
    private String name;

    @Transient
    private boolean isNew = false;

    @Override
    public boolean isNew() {
        return this.isNew;
    }

    public AwardEntity setAsNew() {
        this.isNew = true;
        return this;
    }
}
