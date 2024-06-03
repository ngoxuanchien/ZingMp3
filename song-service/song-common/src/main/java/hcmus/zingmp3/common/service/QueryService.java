package hcmus.zingmp3.common.service;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface QueryService<T> {
    T getById(UUID id);

    T getByAlias(String alias);

    boolean existsByAlias(String alias);

    List<T> getAll(Pageable pageable);

    List<T> getAll();
}
