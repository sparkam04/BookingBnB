package com.netcracker.edu.project.dao;

import com.netcracker.edu.project.model.Model;

import java.util.Collection;

public interface EntityDAO<T extends Model> {

    Collection<T> getAll();

    T getById(Long id);

    void add(T model);

    void update(T model);

    void remove(Long id);

    void remove(T model);
}
