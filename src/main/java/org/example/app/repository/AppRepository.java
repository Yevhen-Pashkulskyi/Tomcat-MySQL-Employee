package org.example.app.repository;

import java.util.List;

public interface AppRepository <T>{
    void create(T odj);
    List<T> read();
    T readById(Long id);
    void update(T odj);
    void delete(Long id);
}
