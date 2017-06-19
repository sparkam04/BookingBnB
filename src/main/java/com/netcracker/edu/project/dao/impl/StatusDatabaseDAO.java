package com.netcracker.edu.project.dao.impl;

import com.netcracker.edu.project.dao.StatusDAO;
import com.netcracker.edu.project.model.Status;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Alexander on 19.06.2017.
 */
@Repository
public class StatusDatabaseDAO extends AbstractDatabaseDAO<Status> implements StatusDAO {
    @Override
    protected Status getNewModel() {
        return null;
    }

    @Override
    protected String getName(Status model) {
        return null;
    }

    @Override
    protected Long getParentId(Status model) {
        return null;
    }

    @Override
    protected Status setParentId(Status model, Long parentId) {
        return null;
    }

    @Override
    protected Iterator<String> getValues(Status model) {
        return null;
    }

    @Override
    protected Status setValues(Status model, Iterator<String> valuesIterator) {
        return null;
    }

    @Override
    protected Iterator<Long> getSingleReferences(Status model) {
        return null;
    }

    @Override
    protected Status setSingleReferences(Status model, Iterator<Long> singldeReferencesIterator) {
        return null;
    }

    @Override
    protected Iterator<List<Long>> getMultipleReferences(Status model) {
        return null;
    }

    @Override
    protected Status setMultipleReferences(Status model, Iterator<List<Long>> multipleReferencesIterator) {
        return null;
    }
}
