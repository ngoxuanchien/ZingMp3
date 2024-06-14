package hcmus.zingmp3.service.search;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SearchService {
    List<?> search(String query, Pageable pageable);
}
