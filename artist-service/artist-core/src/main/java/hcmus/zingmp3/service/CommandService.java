package hcmus.zingmp3.service;

public interface CommandService<T> {
    void create(T object);
    void update(T object);
    void delete(T object);
}
