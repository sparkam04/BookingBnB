package com.netcracker.edu.project.controller;

import com.netcracker.edu.project.model.Booking;
import com.netcracker.edu.project.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by Alexander on 20.06.2017.
 */
@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @RequestMapping("/Booking")
    public Collection<Booking> getAllCountries() {
        return bookingService.getAllEntities();
    }

    @RequestMapping("/Booking/{id}")
    public Booking getBooking(@PathVariable Long id) {
        return bookingService.getEntity(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/Booking")
    public boolean addBooking(@RequestBody Booking booking) {
        return bookingService.addEntity(booking);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/Booking")
    public boolean updateBooking(@RequestBody Booking booking) {
        return bookingService.updateEntity(booking);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/Booking/{id}")
    public boolean deleteBooking(@PathVariable Long id) {
        return bookingService.removeEntity(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/Booking")
    public boolean deleteBooking(@RequestBody Booking booking) {
        return bookingService.removeEntity(booking);
    }
}

