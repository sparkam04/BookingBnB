package com.netcracker.edu.project.dao.impl;

import com.netcracker.edu.project.dao.BookingDAO;
import com.netcracker.edu.project.model.Booking;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alexander on 19.06.2017.
 */
@Repository
public class BookingDatabaseDAO extends AbstractDatabaseDAO<Booking> implements BookingDAO {
    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat(Booking.DATE_FORMAT);

    @Override
    protected Booking getNewModel() {
        return new Booking();
    }

    @Override
    protected String getName(Booking model) {
        return model.getCode().toString();
    }

    @Override
    protected Long getParentId(Booking model) {
        return model.getRoomId();
    }

    @Override
    protected Booking setParentId(Booking model, Long parentId) {
        model.setRoomId(parentId);
        return model;
    }

    @Override
    protected Iterator<String> getValues(Booking model) {
        List<String> values = new LinkedList<>();
        values.add(Long.toString(model.getCode()));
        values.add(model.getMessage());
        values.add(DATE_FORMATTER.format(model.getCheckIn()));
        values.add(DATE_FORMATTER.format(model.getCheckOut()));
        values.add(Integer.toString(model.getNumPersons()));
        values.add(Boolean.toString(model.isPaid()));
        return values.iterator();
    }

    @Override
    protected Booking setValues(Booking model, Iterator<String> valuesIterator) {
        model.setCode(Long.parseLong(valuesIterator.next()));
        model.setMessage(valuesIterator.next());
        try {
            model.setCheckIn(DATE_FORMATTER.parse(valuesIterator.next()));
            model.setCheckOut(DATE_FORMATTER.parse(valuesIterator.next()));
        } catch (ParseException ex) {
            //CATCH
        }
        model.setNumPersons(Integer.parseInt(valuesIterator.next()));
        model.setPaid(Boolean.parseBoolean(valuesIterator.next()));
        return model;
    }

    @Override
    protected Iterator<Long> getSingleReferences(Booking model) {
        List<Long> singleReferences = new LinkedList<>();
        singleReferences.add(model.getUserId());
        singleReferences.add(model.getStatusId());
        singleReferences.add(model.getPaySysId());
        return singleReferences.iterator();
    }

    @Override
    protected Booking setSingleReferences(Booking model, Iterator<Long> singldeReferencesIterator) {
        model.setUserId(singldeReferencesIterator.next());
        model.setStatusId(singldeReferencesIterator.next());
        model.setPaySysId(singldeReferencesIterator.next());
        return model;
    }
}
