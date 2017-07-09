package com.netcracker.edu.project.controller;

import com.netcracker.edu.project.model.Booking;
import com.netcracker.edu.project.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Collection;

@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping("/booking")
    public Collection<Booking> getAllBookings() {
        return bookingService.getAllEntities();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping("/booking/room/{id}")
    public Collection<Booking> getBookingsByRoom(@PathVariable Long id) {
        return bookingService.getEntitiesByParentId(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping("/booking/client/{id}")
    public Collection<Booking> getBookingsByClientId(@PathVariable Long id) {
        return bookingService.getBookingsByClientId(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping("/booking/status/{id}")
    public Collection<Booking> getBookingsByStatusId(@PathVariable Long id) {
        return bookingService.getBookingsByStatusId(id);
    }

    @RequestMapping(value = "/booking/getall/date/client/hotel/status", method = RequestMethod.POST)
    public Collection<Booking> getBookingsByDateAndClientAndHotel(@RequestParam Date from,
                                                                  @RequestParam Date to,
                                                                  @RequestParam Long clientId,
                                                                  @RequestParam Long hotelId,
                                                                  @RequestParam Long statusId) {
        return bookingService.getBookingsByDateAndClientAndHotel(from, to, clientId, hotelId, statusId);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping("/booking/date/{from}/{to}")
    public Collection<Booking> getBookingsByDate(@PathVariable Date from, @PathVariable Date to) {
        return bookingService.getBookingsByDate(from, to);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping("/booking/date/{from}/{to}/hotel/{hotelId}")
    public Collection<Booking> getBookingsByDateAndHotel(@PathVariable Date from, @PathVariable Date to, @PathVariable Long hotelId) {
        return bookingService.getBookingsByDateAndHotel(from, to, hotelId);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping("/booking/{id}")
    public Booking getBooking(@PathVariable Long id) {
        return bookingService.getEntity(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/booking")
    public boolean addBooking(@RequestBody Booking room) {
        return bookingService.addEntity(room);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping(method = RequestMethod.PUT, value = "/booking")
    public boolean updateBooking(@RequestBody Booking room) {
        return bookingService.updateEntity(room);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping(method = RequestMethod.DELETE, value = "/booking/{id}")
    public boolean deleteBooking(@PathVariable Long id) {
        return bookingService.removeEntity(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping(method = RequestMethod.DELETE, value = "/booking")
    public boolean deleteBooking(@RequestBody Booking room) {
        return bookingService.removeEntity(room);
    }
}
