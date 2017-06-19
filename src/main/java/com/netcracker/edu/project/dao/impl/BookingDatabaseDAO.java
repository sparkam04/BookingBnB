package com.netcracker.edu.project.dao.impl;

import com.netcracker.edu.project.dao.BookingDAO;
import com.netcracker.edu.project.model.Booking;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Alexander on 19.06.2017.
 */
@Repository
public class BookingDatabaseDAO extends AbstractDatabaseDAO<Booking> implements BookingDAO{
    @Override
    protected Booking getNewModel() {
        return null;
    }

    @Override
    protected String getName(Booking model) {
        return null;
    }

    @Override
    protected Long getParentId(Booking model) {
        return null;
    }

    @Override
    protected Booking setParentId(Booking model, Long parentId) {
        return null;
    }

    @Override
    protected Iterator<String> getValues(Booking model) {
        return null;
    }

    @Override
    protected Booking setValues(Booking model, Iterator<String> valuesIterator) {
        return null;
    }

    @Override
    protected Iterator<Long> getSingleReferences(Booking model) {
        return null;
    }

    @Override
    protected Booking setSingleReferences(Booking model, Iterator<Long> singldeReferencesIterator) {
        return null;
    }

    @Override
    protected Iterator<List<Long>> getMultipleReferences(Booking model) {
        return null;
    }

    @Override
    protected Booking setMultipleReferences(Booking model, Iterator<List<Long>> multipleReferencesIterator) {
        return null;
    }
}
