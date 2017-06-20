package com.netcracker.edu.project.dao.impl;

import com.netcracker.edu.project.dao.StatusDAO;
import com.netcracker.edu.project.model.Status;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alexander on 19.06.2017.
 */
@Repository
public class StatusDatabaseDAO extends AbstractDatabaseDAO<Status> implements StatusDAO {
    @Override
    protected Status getNewModel() {
        return new Status();
    }

    @Override
    protected String getName(Status model) {
        return model.getName();
    }

    @Override
    protected Iterator<String> getValues(Status model) {
        List<String> values = new LinkedList<>();
        values.add(model.getName());
        return values.iterator();
    }

    @Override
    protected Status setValues(Status model, Iterator<String> valuesIterator) {
        model.setName(valuesIterator.next());
        return model;
    }
}
