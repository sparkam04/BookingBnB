package com.netcracker.edu.project.dao;

import com.netcracker.edu.project.model.Model;

import java.util.Collection;

public interface EntityDAO<T extends Model> {

    Collection<T> getAll();

    T getById(Long id);

    boolean add(T model);

    boolean update(T model);

    boolean remove(Long id);

    boolean remove(T model);
}
