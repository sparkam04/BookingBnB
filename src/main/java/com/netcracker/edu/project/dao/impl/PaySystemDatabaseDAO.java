package com.netcracker.edu.project.dao.impl;

import com.netcracker.edu.project.dao.PaySystemDAO;
import com.netcracker.edu.project.model.PaySystem;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Alexander on 19.06.2017.
 */
@Repository
public class PaySystemDatabaseDAO extends AbstractDatabaseDAO<PaySystem> implements PaySystemDAO{
    @Override
    protected PaySystem getNewModel() {
        return null;
    }

    @Override
    protected String getName(PaySystem model) {
        return null;
    }

    @Override
    protected Long getParentId(PaySystem model) {
        return null;
    }

    @Override
    protected PaySystem setParentId(PaySystem model, Long parentId) {
        return null;
    }

    @Override
    protected Iterator<String> getValues(PaySystem model) {
        return null;
    }

    @Override
    protected PaySystem setValues(PaySystem model, Iterator<String> valuesIterator) {
        return null;
    }

    @Override
    protected Iterator<Long> getSingleReferences(PaySystem model) {
        return null;
    }

    @Override
    protected PaySystem setSingleReferences(PaySystem model, Iterator<Long> singldeReferencesIterator) {
        return null;
    }

    @Override
    protected Iterator<List<Long>> getMultipleReferences(PaySystem model) {
        return null;
    }

    @Override
    protected PaySystem setMultipleReferences(PaySystem model, Iterator<List<Long>> multipleReferencesIterator) {
        return null;
    }
}
