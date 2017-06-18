package com.netcracker.edu.project.controller;

import com.netcracker.edu.project.model.Room;
import com.netcracker.edu.project.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by Alexander on 18.06.2017.
 */
@RestController
public class RoomController {

    @Autowired
    private RoomService roomService;

    @RequestMapping("/Room")
    public Collection<Room> getAllCities() {
        return roomService.getAllEntities();
    }

    @RequestMapping("/Room/{id}")
    public Room getRoom(@PathVariable Long id) {
        return roomService.getEntity(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/Room")
    public boolean addRoom(@RequestBody Room room) {
        return roomService.addEntity(room);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/Room")
    public boolean updateRoom(@RequestBody Room room) {
        return roomService.updateEntity(room);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/Room/{id}")
    public boolean deleteRoom(@PathVariable Long id) {
        return roomService.removeEntity(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/Room")
    public boolean deleteRoom(@RequestBody Room room) {
        return roomService.removeEntity(room);
    }
}
