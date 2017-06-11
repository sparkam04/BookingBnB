package com.netcracker.edu.project.controller;

import com.netcracker.edu.project.model.Hotel;
import com.netcracker.edu.project.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @RequestMapping("/Hotel")
    public Collection<Hotel> getAllLocations() {
        return hotelService.getAllEntities();
    }

    @RequestMapping("/Hotel/{id}")
    public Hotel getLocation(@PathVariable Long id) {
        return hotelService.getEntity(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/Hotel")
    public boolean addLocation(@RequestBody Hotel hotel) {
        return hotelService.addEntity(hotel);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/Hotel")
    public boolean updateLocation(@RequestBody Hotel hotel) {
        return hotelService.updateEntity(hotel);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/Hotel/{id}")
    public boolean deleteLocation(@PathVariable Long id) {
        return hotelService.removeEntity(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/Hotel")
    public boolean deleteLocation(@RequestBody Hotel hotel) {
        return hotelService.removeEntity(hotel);
    }
}
