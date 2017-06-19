package com.netcracker.edu.project.dao.impl;

import com.netcracker.edu.project.dao.RoleDAO;
import com.netcracker.edu.project.model.Role;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Alexander on 19.06.2017.
 */
@Repository
public class RoleDatabaseDAO extends AbstractDatabaseDAO<Role> implements RoleDAO{
    @Override
    protected Role getNewModel() {
        return null;
    }

    @Override
    protected String getName(Role model) {
        return null;
    }

    @Override
    protected Long getParentId(Role model) {
        return null;
    }

    @Override
    protected Role setParentId(Role model, Long parentId) {
        return null;
    }

    @Override
    protected Iterator<String> getValues(Role model) {
        return null;
    }

    @Override
    protected Role setValues(Role model, Iterator<String> valuesIterator) {
        return null;
    }

    @Override
    protected Iterator<Long> getSingleReferences(Role model) {
        return null;
    }

    @Override
    protected Role setSingleReferences(Role model, Iterator<Long> singldeReferencesIterator) {
        return null;
    }

    @Override
    protected Iterator<List<Long>> getMultipleReferences(Role model) {
        return null;
    }

    @Override
    protected Role setMultipleReferences(Role model, Iterator<List<Long>> multipleReferencesIterator) {
        return null;
    }
}
