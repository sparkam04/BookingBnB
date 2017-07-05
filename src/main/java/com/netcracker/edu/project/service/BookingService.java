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
}
