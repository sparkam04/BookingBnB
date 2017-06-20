package com.netcracker.edu.project.dao.impl;

import com.netcracker.edu.project.dao.RatingDAO;
import com.netcracker.edu.project.model.Rating;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alexander on 19.06.2017.
 */
@Repository
public class RatingDatabaseDAO extends AbstractDatabaseDAO<Rating> implements RatingDAO {
    @Override
    protected Rating getNewModel() {
        return new Rating();
    }

    @Override
    protected String getName(Rating model) {
        return model.getBookingId().toString();
    }

    @Override
    protected Long getParentId(Rating model) {
        return model.getBookingId();
    }

    @Override
    protected Rating setParentId(Rating model, Long parentId) {
        model.setBookingId(parentId);
        return model;
    }

    @Override
    protected Iterator<String> getValues(Rating model) {
        List<String> values = new LinkedList<>();
        values.add(Integer.toString(model.getValue()));
        values.add(model.getComment());
        return values.iterator();
    }

    @Override
    protected Rating setValues(Rating model, Iterator<String> valuesIterator) {
        model.setValue(Integer.parseInt(valuesIterator.next()));
        model.setComment(valuesIterator.next());
        return model;
    }
}
