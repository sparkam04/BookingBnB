package com.netcracker.edu.project.controller;

import com.netcracker.edu.project.model.Booking;
import com.netcracker.edu.project.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Collection;

@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @RequestMapping("/booking")
    public Collection<Booking> getAllBookings() {
        return bookingService.getAllEntities();
    }

    @RequestMapping("/booking/room/{id}")
    public Collection<Booking> getBookingsByRoom(@PathVariable Long id) {
        return bookingService.getEntitiesByParentId(id);
    }

    @RequestMapping("/booking/client/{id}")
    public Collection<Booking> getBookingsByClientId(@PathVariable Long id) {
        return bookingService.getBookingsByClientId(id);
    }

    @RequestMapping("/booking/status/{id}")
    public Collection<Booking> getBookingsByStatusId(@PathVariable Long id) {
        return bookingService.getBookingsByStatusId(id);
    }

    @RequestMapping("/booking/date/{from}/{to}")
    public Collection<Booking> getBookingsByDate(@PathVariable Date from, @PathVariable Date to) {
        return bookingService.getBookingsByDate(from, to);
    }

    @RequestMapping("/booking/date/{from}/{to}/hotel/{hotelId}")
    public Collection<Booking> getBookingsByDateAndHotel(@PathVariable Date from, @PathVariable Date to, @PathVariable Long hotelId) {
        return bookingService.getBookingsByDateAndHotel(from, to, hotelId);
    }

    @RequestMapping("/booking/{id}")
    public Booking getBooking(@PathVariable Long id) {
        return bookingService.getEntity(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/booking")
    public boolean addBooking(@RequestBody Booking room) {
        return bookingService.addEntity(room);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/booking")
    public boolean updateBooking(@RequestBody Booking room) {
        return bookingService.updateEntity(room);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/booking/{id}")
    public boolean deleteBooking(@PathVariable Long id) {
        return bookingService.removeEntity(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/booking")
    public boolean deleteBooking(@RequestBody Booking room) {
        return bookingService.removeEntity(room);
    }
}
