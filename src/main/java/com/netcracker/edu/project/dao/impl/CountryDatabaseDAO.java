package com.netcracker.edu.project.dao.impl;

import com.netcracker.edu.project.dao.CountryDAO;
import com.netcracker.edu.project.model.Country;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Repository
public class CountryDatabaseDAO extends AbstractDatabaseDAO<Country> implements CountryDAO {
    @Override
    protected Country getNewModel() {
        return new Country();
    }

    @Override
    protected String getName(Country model) {
        return model.getName();
    }

    @Override
    protected Long getParentId(Country model) {
        return null;
    }

    @Override
    protected Country setParentId(Country model, Long parentId) {
        return null;
    }

    @Override
    protected Iterator<String> getValues(Country model) {
        List<String> values = new LinkedList<>();
        values.add(model.getCode());
        values.add(model.getName());
        return values.iterator();
    }

    @Override
    protected Country setValues(Country model, Iterator<String> valuesIterator) {
        model.setCode(valuesIterator.next());
        model.setName(valuesIterator.next());
        return model;
    }

    @Override
    protected Iterator<Long> getSingleReferences(Country model) {
        return null;
    }

    @Override
    protected Country setSingleReferences(Country model, Iterator<Long> singleReferencesIterator) {
        return model;
    }

    @Override
    protected Iterator<List<Long>> getMultipleReferences(Country model) {
        return null;
    }

    @Override
    protected Country setMultipleReferences(Country model, Iterator<List<Long>> multipleReferencesIterator) {
        return model;
    }
}
