package com.netcracker.edu.project.dao.impl;

import com.netcracker.edu.project.dao.UserDAO;
import com.netcracker.edu.project.model.User;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alexander on 19.06.2017.
 */
@Repository
public class UserDatabaseDAO extends AbstractDatabaseDAO<User> implements UserDAO {
    @Override
    protected User getNewModel() {
        return new User();
    }

    @Override
    protected String getName(User model) {
        return model.getFirstName() + " " + model.getLastName();
    }

    @Override
    protected Long getParentId(User model) {
        return model.getRoleId();
    }

    @Override
    protected User setParentId(User model, Long parentId) {
        model.setRoleId(parentId);
        return model;
    }

    @Override
    protected Iterator<String> getValues(User model) {
        List<String> values = new LinkedList<>();
        values.add(model.getFirstName());
        values.add(model.getLastName());
        values.add(model.getPhone());
        values.add(model.getEmail());
        values.add(model.getPass());
        return values.iterator();
    }

    @Override
    protected User setValues(User model, Iterator<String> valuesIterator) {
        model.setFirstName(valuesIterator.next());
        model.setLastName(valuesIterator.next());
        model.setPhone(valuesIterator.next());
        model.setEmail(valuesIterator.next());
        model.setPass(valuesIterator.next());
        return model;
    }
}
