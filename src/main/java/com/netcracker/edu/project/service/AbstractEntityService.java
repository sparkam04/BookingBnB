package com.netcracker.edu.project.service;

import com.netcracker.edu.project.dao.EntityDAO;
import com.netcracker.edu.project.model.Model;

import java.util.Collection;

public abstract class AbstractEntityService<T extends Model> {

    protected abstract EntityDAO<T> getDao();
    public Collection<T> getAllEntities() {
        return getDao().getAll();
    }

    public T getEntity(Long id) {
        return getDao().getById(id);
    }

    public boolean addEntity(T entity) {
        return getDao().add(entity);
    }

    public boolean removeEntity(T entity) {
        return getDao().remove(entity);
    }

    public boolean removeEntity(Long id) {
        return getDao().remove(id);
    }

    public boolean updateEntity(T entity) {
        return getDao().update(entity);
    }
}
