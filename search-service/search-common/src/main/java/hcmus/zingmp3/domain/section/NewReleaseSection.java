package hcmus.zingmp3.domain.section;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class NewReleaseSection extends AbstractSection {
    public NewReleaseSection(
            final String title,
            final int index,
            final List<NewReleaseItem> items
    ) {
        super(title, SectionType.NEW_RELEASE, index, items);
    }
}
