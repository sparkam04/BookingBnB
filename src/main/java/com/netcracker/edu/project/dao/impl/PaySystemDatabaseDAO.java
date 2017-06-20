package com.netcracker.edu.project.dao.impl;

import com.netcracker.edu.project.dao.PaySystemDAO;
import com.netcracker.edu.project.model.PaySystem;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alexander on 19.06.2017.
 */
@Repository
public class PaySystemDatabaseDAO extends AbstractDatabaseDAO<PaySystem> implements PaySystemDAO {
    @Override
    protected PaySystem getNewModel() {
        return new PaySystem();
    }

    @Override
    protected String getName(PaySystem model) {
        return model.getName();
    }

    @Override
    protected Iterator<String> getValues(PaySystem model) {
        List<String> values = new LinkedList<>();
        values.add(model.getName());
        return values.iterator();
    }

    @Override
    protected PaySystem setValues(PaySystem model, Iterator<String> valuesIterator) {
        model.setName(valuesIterator.next());
        return model;
    }
}
