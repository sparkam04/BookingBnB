package com.netcracker.edu.project.service;

import com.netcracker.edu.project.dao.CityDAO;
import com.netcracker.edu.project.dao.impl.CityDatabaseDAO;
import com.netcracker.edu.project.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService extends AbstractEntityService<City>{

    @Autowired
    private CityDatabaseDAO cityDatabaseDAO;

    @Override
    protected CityDAO getDao() {
        return cityDatabaseDAO;
    }
}
