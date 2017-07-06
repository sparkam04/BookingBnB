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

    @RequestMapping("/hotel")
    public Collection<Hotel> getAllHotels() {
        return hotelService.getAllEntities();
    }

    @RequestMapping("/hotel/location/{locationId}")
    public Collection<Hotel> getHotelsByLocation(@PathVariable Long locationId) {
        return hotelService.getEntitiesByParentId(locationId);
    }

    @RequestMapping("/hotel/owner/{ownerId}")
    public Collection<Hotel> getHotelsByOwnerId(@PathVariable Long ownerId) {
        return hotelService.getHotelsByOwnerId(ownerId);
    }

    @RequestMapping("/hotel/rating/{id}")
    public Double getRatingsById(@PathVariable Long id) {
        return hotelService.getRatingById(id);
    }

    @RequestMapping("/hotel/{id}")
    public Hotel getHotel(@PathVariable Long id) {
        return hotelService.getEntity(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/hotel")
    public boolean addHotel(@RequestBody Hotel hotel) {
        return hotelService.addEntity(hotel);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/hotel")
    public boolean updateHotel(@RequestBody Hotel hotel) {
        return hotelService.updateEntity(hotel);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/hotel/{id}")
    public boolean deleteHotel(@PathVariable Long id) {
        return hotelService.removeEntity(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/hotel")
    public boolean deleteHotel(@RequestBody Hotel hotel) {
        return hotelService.removeEntity(hotel);
    }
}
