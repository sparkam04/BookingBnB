package com.netcracker.edu.project.service;

import com.netcracker.edu.project.dao.impl.LocationDatabaseDAO;
import com.netcracker.edu.project.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService extends AbstractEntityService<Location>{

    @Autowired
    private LocationDatabaseDAO locationDatabaseDAO;

    @Override
    protected LocationDatabaseDAO getDao() {
        return locationDatabaseDAO;
    }
}
