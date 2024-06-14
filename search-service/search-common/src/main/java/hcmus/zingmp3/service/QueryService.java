package hcmus.zingmp3.service;

import java.util.List;
import java.util.UUID;

public interface QueryService<T> {
    T getById(UUID id);

    List<T> getAll();
}
