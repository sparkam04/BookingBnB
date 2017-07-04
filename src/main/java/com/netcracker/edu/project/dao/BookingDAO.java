package com.netcracker.edu.project.dao;

import com.netcracker.edu.project.model.Booking;

import java.sql.Date;
import java.util.Collection;

public interface BookingDAO extends EntityDAO<Booking> {
    Collection<Booking> getBookingsByStatusId(Long statusId);

    Collection<Booking> getBookingsByDate(Date from, Date to);

    Collection<Booking> getBookingsByDateAndHotel(Date from, Date to, Long hotelId);
}
