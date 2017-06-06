package com.netcracker.edu.project.Service;

import com.netcracker.edu.project.DAO.impl.CityDatabaseDAO;
import com.netcracker.edu.project.Model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    @Autowired
    private CityDatabaseDAO cityDatabaseDAO;

    public City getCity(Long id) {
        return cityDatabaseDAO.getById(id);
    }

    public boolean addCity(City city) {
        return cityDatabaseDAO.add(city);
    }

    public boolean removeCity(City city) {
        return cityDatabaseDAO.remove(city);
    }

    public boolean removeCity(Long id) {
        return cityDatabaseDAO.remove(id);
    }

    public boolean updateCity(City city) {
        return cityDatabaseDAO.update(city);
    }
}
