package service;

import java.util.UUID;

public interface ServiceGrpc<S, T> {
    boolean isExist(UUID id);

    T getById(UUID id);
}
