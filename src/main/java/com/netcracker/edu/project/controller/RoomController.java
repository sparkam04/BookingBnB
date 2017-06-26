package com.netcracker.edu.project.controller;

import com.netcracker.edu.project.model.Room;
import com.netcracker.edu.project.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Collection;

@RestController
public class RoomController {

    @Autowired
    private RoomService roomService;

    @RequestMapping("/room")
    public Collection<Room> getAllRooms() {
        return roomService.getAllEntities();
    }

    @RequestMapping("/room/hotel/{id}")
    public Collection<Room> getRoomsByHotel(@PathVariable Long id) {
        return roomService.getEntitiesByParentId(id);
    }

    @RequestMapping("/room/free/{checkIn}/{checkOut}/{cityid}")
    public Collection<Room> getFreeRooms(@PathVariable("checkIn") Date checkIn,
                                         @PathVariable("checkOut") Date checkOut,
                                         @PathVariable("cityid") Long cityid) {
        return roomService.getFreeRoomsByDateByCity(checkIn, checkOut, cityid);
    }

    @RequestMapping("/room/{id}")
    public Room getRoom(@PathVariable Long id) {
        return roomService.getEntity(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/room")
    public boolean addRoom(@RequestBody Room room) {
        return roomService.addEntity(room);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/room")
    public boolean updateRoom(@RequestBody Room room) {
        return roomService.updateEntity(room);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/room/{id}")
    public boolean deleteRoom(@PathVariable Long id) {
        return roomService.removeEntity(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/room")
    public boolean deleteRoom(@RequestBody Room room) {
        return roomService.removeEntity(room);
    }
}
