package hcmus.zingmp3.domain.section;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.objenesis.instantiator.util.UnsafeUtils;

import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class RecentPlaylistSection extends AbstractSection {
    public RecentPlaylistSection(
            final String title,
            final int index,
            final List<UUID> items
    ) {
        super(title, SectionType.RECENT_PLAYLIST, index, items);
    }
}
