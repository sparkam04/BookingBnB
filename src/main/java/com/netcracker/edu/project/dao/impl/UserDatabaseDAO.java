package com.netcracker.edu.project.dao.impl;

import com.netcracker.edu.project.dao.UserDAO;
import com.netcracker.edu.project.model.User;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Alexander on 19.06.2017.
 */
@Repository
public class UserDatabaseDAO extends AbstractDatabaseDAO<User> implements UserDAO {
    @Override
    protected User getNewModel() {
        return null;
    }

    @Override
    protected String getName(User model) {
        return null;
    }

    @Override
    protected Long getParentId(User model) {
        return null;
    }

    @Override
    protected User setParentId(User model, Long parentId) {
        return null;
    }

    @Override
    protected Iterator<String> getValues(User model) {
        return null;
    }

    @Override
    protected User setValues(User model, Iterator<String> valuesIterator) {
        return null;
    }

    @Override
    protected Iterator<Long> getSingleReferences(User model) {
        return null;
    }

    @Override
    protected User setSingleReferences(User model, Iterator<Long> singldeReferencesIterator) {
        return null;
    }

    @Override
    protected Iterator<List<Long>> getMultipleReferences(User model) {
        return null;
    }

    @Override
    protected User setMultipleReferences(User model, Iterator<List<Long>> multipleReferencesIterator) {
        return null;
    }
}
