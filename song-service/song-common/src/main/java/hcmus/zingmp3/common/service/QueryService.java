package hcmus.zingmp3.common.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface QueryService<T> {
    T getById(UUID id);

    T getByAlias(String alias);

    boolean existsById(UUID id);

    boolean existsByAlias(String alias);

    Page<T> getAll(Pageable pageable);

    List<T> getAll();
}
