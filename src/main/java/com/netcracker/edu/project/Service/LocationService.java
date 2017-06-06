package com.netcracker.edu.project.Service;

import com.netcracker.edu.project.DAO.EntityDAO;
import com.netcracker.edu.project.DAO.impl.LocationDatabaseDAO;
import com.netcracker.edu.project.Model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService extends AbstractEntityService<Location>{

    @Autowired
    private LocationDatabaseDAO locationDatabaseDAO;

    @Override
    protected EntityDAO<Location> getDao() {
        return locationDatabaseDAO;
    }
}
