package hcmus.zingmp3.service;

import hcmus.zingmp3.common.domain.model.Song;

public interface CommandService<T> {
    void create(T object);
    void update(T object);
    void delete(T object);
}
