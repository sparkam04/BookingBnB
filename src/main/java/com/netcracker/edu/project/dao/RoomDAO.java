package com.netcracker.edu.project.dao;

import com.netcracker.edu.project.model.Room;

import java.sql.Date;
import java.util.Collection;

public interface RoomDAO extends EntityDAO<Room> {
    Collection<Room> getFreeRoomsByDateByCity(Date checkIn, Date checkOut, Long cityId);
}
