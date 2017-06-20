package com.netcracker.edu.project.dao.impl;

import com.netcracker.edu.project.dao.ImageDAO;
import com.netcracker.edu.project.model.Image;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alexander on 19.06.2017.
 */
@Repository
public class ImageDatabaseDAO extends AbstractDatabaseDAO<Image> implements ImageDAO {
    @Override
    protected Image getNewModel() {
        return new Image();
    }

    @Override
    protected String getName(Image model) {
        return model.getImageUrl();
    }

    @Override
    protected Iterator<String> getValues(Image model) {
        List<String> values = new LinkedList<>();
        values.add(model.getImageUrl());
        values.add(model.getImageDescription());
        return values.iterator();
    }

    @Override
    protected Image setValues(Image model, Iterator<String> valuesIterator) {
        model.setImageUrl(valuesIterator.next());
        model.setImageDescription(valuesIterator.next());
        return model;
    }
}
