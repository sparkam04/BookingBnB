package com.netcracker.edu.project.dao.impl;

import com.netcracker.edu.project.dao.RatingDAO;
import com.netcracker.edu.project.model.Rating;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Alexander on 19.06.2017.
 */
@Repository
public class RatingDatabaseDAO extends AbstractDatabaseDAO<Rating> implements RatingDAO{
    @Override
    protected Rating getNewModel() {
        return null;
    }

    @Override
    protected String getName(Rating model) {
        return null;
    }

    @Override
    protected Long getParentId(Rating model) {
        return null;
    }

    @Override
    protected Rating setParentId(Rating model, Long parentId) {
        return null;
    }

    @Override
    protected Iterator<String> getValues(Rating model) {
        return null;
    }

    @Override
    protected Rating setValues(Rating model, Iterator<String> valuesIterator) {
        return null;
    }

    @Override
    protected Iterator<Long> getSingleReferences(Rating model) {
        return null;
    }

    @Override
    protected Rating setSingleReferences(Rating model, Iterator<Long> singldeReferencesIterator) {
        return null;
    }

    @Override
    protected Iterator<List<Long>> getMultipleReferences(Rating model) {
        return null;
    }

    @Override
    protected Rating setMultipleReferences(Rating model, Iterator<List<Long>> multipleReferencesIterator) {
        return null;
    }
}
