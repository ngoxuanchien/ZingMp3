package hcmus.zingmp3.service.section;

import hcmus.zingmp3.domain.section.AbstractSection;

import java.util.List;

public interface SectionService {
    List<AbstractSection> getSection(int page);
}
