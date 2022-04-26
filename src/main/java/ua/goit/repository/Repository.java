package ua.goit.repository;

import java.util.Optional;

public interface Repository<T> {

    void save(T t);

    Optional<T> findById(int id);

    int update(T t);

    void remove(T t);
}
