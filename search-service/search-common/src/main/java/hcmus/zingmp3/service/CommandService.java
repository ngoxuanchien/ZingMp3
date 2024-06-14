package hcmus.zingmp3.service;

public interface CommandService<T> {
    T create(T object);

    T update(T object);

    void delete(T object);
}
