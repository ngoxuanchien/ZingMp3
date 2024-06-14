package hcmus.zingmp3.domain.section;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BannerSection extends AbstractSection {
    public BannerSection(
            final String title,
            final int index,
            final List<UUID> items
    ) {
        super(title, SectionType.BANNER, index, items);
    }
}
