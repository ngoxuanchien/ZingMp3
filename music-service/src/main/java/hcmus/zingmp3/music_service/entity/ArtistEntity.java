package hcmus.zingmp3.music_service.entity;

import hcmus.zingmp3.music_service.dto.AwardDTO;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("artist")
public class ArtistEntity implements Persistable<String> {
    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();
    private String name;
    private String link;
    private String alias;
    private String playlistId;
    private String thumbnail;
    private int totalFollow;
    private String biography;
    private String sortBiography;
    private String national;
    private String realName;
    private LocalDate birthday;

    @Transient
    private boolean isNew = false;

    @Override
    public boolean isNew() {
        return this.isNew;
    }

    public ArtistEntity setAsNew() {
        this.isNew = true;
        return this;
    }

    public ArtistEntity setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }
}
