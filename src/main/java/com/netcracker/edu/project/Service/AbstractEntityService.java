package com.netcracker.edu.project.Service;

import com.netcracker.edu.project.DAO.EntityDAO;
import com.netcracker.edu.project.Model.Model;

public abstract class AbstractEntityService<T extends Model> {

    protected abstract EntityDAO<T> getDao();

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
