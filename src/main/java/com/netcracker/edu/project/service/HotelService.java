package com.netcracker.edu.project.service;

import com.netcracker.edu.project.dao.impl.HotelDatabaseDAO;
import com.netcracker.edu.project.model.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelService extends AbstractEntityService<Hotel> {

    @Autowired
    private HotelDatabaseDAO hotelDatabaseDAO;

    @Override
    protected HotelDatabaseDAO getDao() {
        return hotelDatabaseDAO;
    }

    public Double getRatingById(Long id) {
        return hotelDatabaseDAO.getRatingById(id);
    }
}
