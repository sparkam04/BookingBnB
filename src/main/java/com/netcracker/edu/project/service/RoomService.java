package com.netcracker.edu.project.service;

import com.netcracker.edu.project.dao.impl.RoomDatabaseDAO;
import com.netcracker.edu.project.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Collection;

@Service
public class RoomService extends AbstractEntityService<Room>{

    @Autowired
    private RoomDatabaseDAO roomDatabaseDAO;

    @Override
    protected RoomDatabaseDAO getDao() {
        return roomDatabaseDAO;
    }

    public Collection<Room> getFreeRoomsByDateByCity(Date checkIn, Date checkOut, Long cityId) {
        return roomDatabaseDAO.getFreeRoomsByDateByCity(checkIn, checkOut, cityId);
    }
}
