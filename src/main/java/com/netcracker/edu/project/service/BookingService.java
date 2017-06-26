package com.netcracker.edu.project.service;

import com.netcracker.edu.project.dao.impl.BookingDatabaseDAO;
import com.netcracker.edu.project.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService extends AbstractEntityService<Booking>{

    @Autowired
    private BookingDatabaseDAO bookingDatabaseDAO;

    @Override
    protected BookingDatabaseDAO getDao() {
        return bookingDatabaseDAO;
    }
}
