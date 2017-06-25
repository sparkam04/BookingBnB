package com.netcracker.edu.project.dao.impl;

import com.netcracker.edu.project.dao.HotelDAO;
import com.netcracker.edu.project.model.Hotel;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Repository
public class HotelDatabaseDAO extends AbstractDatabaseDAO<Hotel> implements HotelDAO {
    private static final SimpleDateFormat TIME_FORMATTER = new SimpleDateFormat(Hotel.TIME_FORMAT);

    @Override
    protected Hotel getNewModel() {
        return new Hotel();
    }

    @Override
    protected String getName(Hotel model) {
        return model.getHotelName();
    }

    @Override
    protected Long getParentId(Hotel model) {
        return model.getLocationId();
    }

    @Override
    protected Hotel setParentId(Hotel model, Long parentId) {
        model.setLocationId(parentId);
        return model;
    }

    @Override
    protected Iterator<String> getValues(Hotel model) {
        List<String> values = new LinkedList<>();
        values.add(model.getHotelName());
        values.add(model.getPhone());
        values.add(model.getDescription());
        values.add(Double.toString(model.getHotelRating()));
        values.add(Boolean.toString(model.isHasWifi()));
        values.add(Boolean.toString(model.isHasShuttle()));
        values.add(Boolean.toString(model.isHasSmoking()));
        values.add(Boolean.toString(model.isHasParking()));
        values.add(Boolean.toString(model.isHasConditioning()));
        values.add(Boolean.toString(model.isHasPets()));
        values.add(Boolean.toString(model.isHasPool()));
        values.add(Boolean.toString(model.isHasKitchen()));
        values.add(Boolean.toString(model.isHasBreakfast()));
        values.add(TIME_FORMATTER.format(model.getCheckInTime()));
        values.add(TIME_FORMATTER.format(model.getCheckOutTime()));
        values.add(Boolean.toString(model.isPreorder()));
        return values.iterator();
    }

    @Override
    protected Hotel setValues(Hotel model, Iterator<String> valuesIterator) {
        model.setHotelName(valuesIterator.next());
        model.setPhone(valuesIterator.next());
        model.setDescription(valuesIterator.next());
        model.setHotelRating(Double.valueOf(valuesIterator.next()));
        model.setHasWifi(Boolean.getBoolean(valuesIterator.next()));
        model.setHasShuttle(Boolean.getBoolean(valuesIterator.next()));
        model.setHasSmoking(Boolean.getBoolean(valuesIterator.next()));
        model.setHasParking(Boolean.getBoolean(valuesIterator.next()));
        model.setHasConditioning(Boolean.getBoolean(valuesIterator.next()));
        model.setHasPets(Boolean.getBoolean(valuesIterator.next()));
        model.setHasPool(Boolean.getBoolean(valuesIterator.next()));
        model.setHasKitchen(Boolean.getBoolean(valuesIterator.next()));
        model.setHasBreakfast(Boolean.getBoolean(valuesIterator.next()));
        try {
            model.setCheckInTime(TIME_FORMATTER.parse(valuesIterator.next()));
            model.setCheckOutTime(TIME_FORMATTER.parse(valuesIterator.next()));
        } catch (ParseException ex) {
            //CATCH
        }
        model.setPreorder(Boolean.getBoolean(valuesIterator.next()));
        return model;
    }

    @Override
    protected Iterator<Long> getSingleReferences(Hotel model) {
        List<Long> singleReferences = new LinkedList<>();
        singleReferences.add(model.getOwnerId());
        return singleReferences.iterator();
    }

    @Override
    protected Hotel setSingleReferences(Hotel model, Iterator<Long> singldeReferencesIterator) {
        model.setOwnerId(singldeReferencesIterator.next());
        return model;
    }

    @Override
    protected Iterator<List<Long>> getMultipleReferences(Hotel model) {
        List<List<Long>> multipleReferences = new LinkedList<>();
        multipleReferences.add((List) model.getPaySysIds());
        multipleReferences.add((List) model.getImages());
        return multipleReferences.iterator();
    }

    @Override
    protected Hotel setMultipleReferences(Hotel model, Iterator<List<Long>> multipleReferencesIterator) {
        model.setImages(multipleReferencesIterator.next());
        model.setPaySysIds(multipleReferencesIterator.next());
        return model;
    }
}
