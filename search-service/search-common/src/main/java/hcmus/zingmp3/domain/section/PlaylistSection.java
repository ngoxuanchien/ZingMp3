package hcmus.zingmp3.domain.section;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class PlaylistSection extends AbstractSection {
    public PlaylistSection(
            final String title,
            final int index,
            final List<UUID> items
    ) {
        super(title, SectionType.PLAYLIST, index,  items);
    }


}
