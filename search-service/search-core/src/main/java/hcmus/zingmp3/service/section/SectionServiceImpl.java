package hcmus.zingmp3.service.section;

import hcmus.zingmp3.domain.section.AbstractSection;
import hcmus.zingmp3.repository.jpa.SectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SectionServiceImpl implements SectionService {

    private final SectionRepository repository;

    @Override
    public List<AbstractSection> getSection(int page) {
        Sort sort = Sort.by(Sort.Direction.ASC, "index");
        Pageable pageable = PageRequest.of(page, 3, sort);
        return repository.findAll(pageable).getContent();
    }
}
