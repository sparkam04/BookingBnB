package com.netcracker.edu.project.service;

import com.netcracker.edu.project.dao.impl.BookingDatabaseDAO;
import com.netcracker.edu.project.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Collection;

@Service
public class BookingService extends AbstractEntityService<Booking> {

    @Autowired
    private BookingDatabaseDAO bookingDatabaseDAO;

    @Autowired
    private EmailService emailService;

    @Override
    protected BookingDatabaseDAO getDao() {
        return bookingDatabaseDAO;
    }

    public Collection<Booking> getBookingsByClientId(Long clientId) {
        return getDao().getBookingsByClientId(clientId);
    }

    public Collection<Booking> getBookingsByStatusId(Long statusId) {
        return getDao().getBookingsByStatusId(statusId);
    }

    public Collection<Booking> getBookingsByDate(Date from, Date to) {
        return getDao().getBookingsByDate(from, to);
    }

    public Collection<Booking> getBookingsByDateAndHotel(Date from, Date to, Long hotelId) {
        return getDao().getBookingsByDateAndHotel(from, to, hotelId);
    }

    public Collection<Booking> getBookingsByDateAndClientAndHotel(Date from, Date to, Long clientId, Long hotelId, Long statusId) {
        return getDao().getBookingsByDateAndClientIdAndHotelId(from, to, clientId, hotelId, statusId);
    }

    @Override
    public boolean addEntity(Booking booking) {
        if (super.addEntity(booking)) {
            emailService.sendMessageBookingCreated(booking);
            return true;
        } else {
            return false;
        }
    }
}
