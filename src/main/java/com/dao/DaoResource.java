package com.dao;

import java.util.List;
import java.util.Optional;

public interface DaoResource<T> {
    Optional<T> selectOne(int id);

    Optional<T> getByName(String name);

    List<T> selectAll();

    void create(T t);

    void updateOne(T t);

    void delete(int id);
}
