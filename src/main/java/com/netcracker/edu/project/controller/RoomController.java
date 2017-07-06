package com.netcracker.edu.project.controller;

import com.netcracker.edu.project.model.Room;
import com.netcracker.edu.project.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Collection;

@RestController
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping("/room")
    public Collection<Room> getAllRooms() {
        return roomService.getAllEntities();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping("/room/hotel/{id}")
    public Collection<Room> getRoomsByHotel(@PathVariable Long id) {
        return roomService.getEntitiesByParentId(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping("/room/free/{checkIn}/{checkOut}/{cityid}")
    public Collection<Room> getFreeRooms(@PathVariable("checkIn") Date checkIn,
                                         @PathVariable("checkOut") Date checkOut,
                                         @PathVariable("cityid") Long cityid) {
        return roomService.getFreeRoomsByDateByCity(checkIn, checkOut, cityid);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping("/room/busy/{checkIn}/{checkOut}/{hotelId}")
    public Collection<Room> getBusyRooms(@PathVariable("checkIn") Date checkIn,
                                         @PathVariable("checkOut") Date checkOut,
                                         @PathVariable("hotelId") Long hotelId) {
        return roomService.getBusyRoomsByDateByHotel(checkIn, checkOut, hotelId);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping("/room/{id}")
    public Room getRoom(@PathVariable Long id) {
        return roomService.getEntity(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/room")
    public boolean addRoom(@RequestBody Room room) {
        return roomService.addEntity(room);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping(method = RequestMethod.PUT, value = "/room")
    public boolean updateRoom(@RequestBody Room room) {
        return roomService.updateEntity(room);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping(method = RequestMethod.DELETE, value = "/room/{id}")
    public boolean deleteRoom(@PathVariable Long id) {
        return roomService.removeEntity(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping(method = RequestMethod.DELETE, value = "/room")
    public boolean deleteRoom(@RequestBody Room room) {
        return roomService.removeEntity(room);
    }
}
