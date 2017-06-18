package com.netcracker.edu.project.dao.impl;

import com.netcracker.edu.project.dao.RoomDAO;
import com.netcracker.edu.project.model.Room;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alexander on 18.06.2017.
 */
public class RoomDatabaseDAO extends AbstractDatabaseDAO<Room> implements RoomDAO {
    @Override
    protected Room getNewModel() {
        return new Room();
    }

    @Override
    protected String getName(Room model) {
        return model.getHotelId().toString() + "_" + model.getRoomId().toString();
    }

    @Override
    protected Long getParentId(Room model) {
        return model.getHotelId();
    }

    @Override
    protected Room setParentId(Room model, Long parentId) {
        model.setHotelId(parentId);
        return model;
    }

    @Override
    protected Iterator<String> getValues(Room model) {
        List<String> values = new LinkedList<>();
        values.add(Integer.toString(model.getRoomId()));
        values.add(Integer.toString(model.getNumOfPlaces()));
        values.add(Boolean.toString(model.getHasBathroom()));
        values.add(Boolean.toString(model.getHasTV()));
        values.add(Boolean.toString(model.getHasExtraBed()));
        values.add(Double.toString(model.getCost()));
        return values.iterator();
    }

    @Override
    protected Room setValues(Room model, Iterator<String> valuesIterator) {
        model.setRoomId(Integer.parseInt(valuesIterator.next()));
        model.setNumOfPlaces(Integer.parseInt(valuesIterator.next()));
        model.setHasBathroom(Boolean.parseBoolean(valuesIterator.next()));
        model.setHasTV(Boolean.parseBoolean(valuesIterator.next()));
        model.setHasExtraBed(Boolean.parseBoolean(valuesIterator.next()));
        model.setCost(Double.parseDouble(valuesIterator.next()));
        return model;
    }

    @Override
    protected Iterator<Long> getSingleReferences(Room model) {
        return null;
    }

    @Override
    protected Room setSingleReferences(Room model, Iterator<Long> singldeReferencesIterator) {
        return model;
    }

    @Override
    protected Iterator<List<Long>> getMultipleReferences(Room model) {
        return null;
    }

    @Override
    protected Room setMultipleReferences(Room model, Iterator<List<Long>> multipleReferencesIterator) {
        return model;
    }
}
