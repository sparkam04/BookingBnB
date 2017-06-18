package com.netcracker.edu.project.service;

import com.netcracker.edu.project.dao.impl.RoomDatabaseDAO;
import com.netcracker.edu.project.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Alexander on 18.06.2017.
 */
@Service
public class RoomService extends AbstractEntityService<Room> {

    @Autowired
    private RoomDatabaseDAO roomDatabaseDAO;

    @Override
    protected RoomDatabaseDAO getDao() {
        return roomDatabaseDAO;
    }
}
