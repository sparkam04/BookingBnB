package com.netcracker.edu.project.Service;

import com.netcracker.edu.project.DAO.EntityDAO;
import com.netcracker.edu.project.DAO.impl.CityDatabaseDAO;
import com.netcracker.edu.project.Model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService extends AbstractEntityService<City>{

    @Autowired
    private CityDatabaseDAO cityDatabaseDAO;

    @Override
    protected EntityDAO<City> getDao() {
        return cityDatabaseDAO;
    }
}
