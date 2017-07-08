package com.netcracker.edu.project.controller;

import com.netcracker.edu.project.model.Hotel;
import com.netcracker.edu.project.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping("/hotel")
    public Collection<Hotel> getAllHotels() {
        return hotelService.getAllEntities();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping("/hotel/location/{locationId}")
    public Collection<Hotel> getHotelsByLocation(@PathVariable Long locationId) {
        return hotelService.getEntitiesByParentId(locationId);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping("/hotel/owner/{ownerId}")
    public Collection<Hotel> getHotelsByOwnerId(@PathVariable Long ownerId) {
        return hotelService.getHotelsByOwnerId(ownerId);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping("/hotel/rating/{id}")
    public Double getRatingsById(@PathVariable Long id) {
        return hotelService.getRatingById(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping("/hotel/{id}")
    public Hotel getHotel(@PathVariable Long id) {
        return hotelService.getEntity(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/hotel")
    public boolean addHotel(@RequestBody Hotel hotel) {
        return hotelService.addEntity(hotel);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping(method = RequestMethod.PUT, value = "/hotel")
    public boolean updateHotel(@RequestBody Hotel hotel) {
        return hotelService.updateEntity(hotel);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping(method = RequestMethod.DELETE, value = "/hotel/{id}")
    public boolean deleteHotel(@PathVariable Long id) {
        return hotelService.removeEntity(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping(method = RequestMethod.DELETE, value = "/hotel")
    public boolean deleteHotel(@RequestBody Hotel hotel) {
        return hotelService.removeEntity(hotel);
    }
}
