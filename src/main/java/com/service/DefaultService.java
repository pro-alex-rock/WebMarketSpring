package com.service;

import java.util.List;
import java.util.Optional;

public interface DefaultService<T> {
    T selectOne(int id);

    Optional<T> getByName(String name);

    List<T> selectAll();

    void create(T t);

    void updateOne(int id, T t);

    void delete(int id);
}
