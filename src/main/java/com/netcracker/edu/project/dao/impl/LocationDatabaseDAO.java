package com.netcracker.edu.project.dao.impl;

import com.netcracker.edu.project.dao.LocationDAO;
import com.netcracker.edu.project.model.Location;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Repository
public class LocationDatabaseDAO extends AbstractDatabaseDAO<Location> implements LocationDAO {
    @Override
    protected Location getNewModel() {
        return new Location();
    }

    @Override
    protected String getName(Location model) {
        return model.getStreetAddress();
    }

    @Override
    protected Long getParentId(Location model) {
        return model.getCityId();
    }

    @Override
    protected Location setParentId(Location model, Long parentId) {
        model.setCityId(parentId);
        return model;
    }

    @Override
    protected Iterator<String> getValues(Location model) {
        List<String> values = new LinkedList<>();
        values.add(model.getStreetAddress());
        values.add(model.getPostalCode());
        values.add(model.getGPSCoords());
        return values.iterator();
    }

    @Override
    protected Location setValues(Location model, Iterator<String> valuesIterator) {
        model.setStreetAddress(valuesIterator.next());
        model.setPostalCode(valuesIterator.next());
        model.setGPSCoords(valuesIterator.next());
        return model;
    }
}
