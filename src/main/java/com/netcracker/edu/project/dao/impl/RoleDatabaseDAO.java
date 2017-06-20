package com.netcracker.edu.project.dao.impl;

import com.netcracker.edu.project.dao.RoleDAO;
import com.netcracker.edu.project.model.Role;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alexander on 19.06.2017.
 */
@Repository
public class RoleDatabaseDAO extends AbstractDatabaseDAO<Role> implements RoleDAO {
    @Override
    protected Role getNewModel() {
        return new Role();
    }

    @Override
    protected String getName(Role model) {
        return model.getName();
    }

    @Override
    protected Iterator<String> getValues(Role model) {
        List<String> values = new LinkedList<>();
        values.add(model.getName());
        return values.iterator();
    }

    @Override
    protected Role setValues(Role model, Iterator<String> valuesIterator) {
        model.setName(valuesIterator.next());
        return model;
    }
}
