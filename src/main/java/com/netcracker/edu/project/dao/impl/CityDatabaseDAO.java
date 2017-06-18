package com.netcracker.edu.project.dao.impl;

import com.netcracker.edu.project.dao.CityDAO;
import com.netcracker.edu.project.model.City;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Repository
public class CityDatabaseDAO extends AbstractDatabaseDAO<City> implements CityDAO {
    @Override
    protected City getNewModel() {
        return new City();
    }

    @Override
    protected String getName(City model) {
        return model.getName();
    }

    @Override
    protected Long getParentId(City model) {
        return model.getCountryId();
    }

    @Override
    protected City setParentId(City model, Long parentId) {
        model.setCountryId(parentId);
        return model;
    }

    @Override
    protected Iterator<String> getValues(City model) {
        List<String> values = new LinkedList<>();
        values.add(model.getName());
        return values.iterator();
    }

    @Override
    protected City setValues(City model, Iterator<String> valuesIterator) {
        model.setName(valuesIterator.next());
        return model;
    }

    @Override
    protected Iterator<Long> getSingleReferences(City model) {
        return null;
    }

    @Override
    protected City setSingleReferences(City model, Iterator<Long> singldeReferencesIterator) {
        return model;
    }

    @Override
    protected Iterator<List<Long>> getMultipleReferences(City model) {
        return null;
    }

    @Override
    protected City setMultipleReferences(City model, Iterator<List<Long>> multipleReferencesIterator) {
        return model;
    }
}
