package com.netcracker.edu.project.dao.impl;

import com.netcracker.edu.project.dao.ImageDAO;
import com.netcracker.edu.project.model.Image;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Alexander on 19.06.2017.
 */
@Repository
public class ImageDatabaseDAO extends AbstractDatabaseDAO<Image> implements ImageDAO{
    @Override
    protected Image getNewModel() {
        return null;
    }

    @Override
    protected String getName(Image model) {
        return null;
    }

    @Override
    protected Long getParentId(Image model) {
        return null;
    }

    @Override
    protected Image setParentId(Image model, Long parentId) {
        return null;
    }

    @Override
    protected Iterator<String> getValues(Image model) {
        return null;
    }

    @Override
    protected Image setValues(Image model, Iterator<String> valuesIterator) {
        return null;
    }

    @Override
    protected Iterator<Long> getSingleReferences(Image model) {
        return null;
    }

    @Override
    protected Image setSingleReferences(Image model, Iterator<Long> singldeReferencesIterator) {
        return null;
    }

    @Override
    protected Iterator<List<Long>> getMultipleReferences(Image model) {
        return null;
    }

    @Override
    protected Image setMultipleReferences(Image model, Iterator<List<Long>> multipleReferencesIterator) {
        return null;
    }
}
