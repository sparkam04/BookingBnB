package com.netcracker.edu.project.Service;

import com.netcracker.edu.project.DAO.EntityDAO;
import com.netcracker.edu.project.DAO.impl.HotelDatabaseDAO;
import com.netcracker.edu.project.Model.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelService extends AbstractEntityService<Hotel>{

    @Autowired
    private HotelDatabaseDAO hotelDatabaseDAO;

    @Override
    protected EntityDAO<Hotel> getDao() {
        return hotelDatabaseDAO;
    }
}
