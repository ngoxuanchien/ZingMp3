package hcmus.zingmp3.domain.section;

import hcmus.zingmp3.domain.model.ObjectConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.UUID;

@Table(name = "sections")
@Entity
@Setter
@Getter
@NoArgsConstructor
public abstract class AbstractSection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID sectionId;

    private String title;

    @Enumerated(EnumType.STRING)
    private SectionType sectionType;

    private int index;

    @JdbcTypeCode(SqlTypes.JSON)
    @Convert(converter = ObjectConverter.class)
    private List<?> items;

    public AbstractSection(
            final String title,
            final SectionType sectionType,
            final int index,
            final List<?> items
    ) {
        this.title = title;
        this.sectionType = sectionType;
        this.index = index;
        this.items = items;
    }
}
