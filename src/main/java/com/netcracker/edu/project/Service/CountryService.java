package com.netcracker.edu.project.Service;

import com.netcracker.edu.project.dao.impl.CountryDatabaseDAO;
import com.netcracker.edu.project.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryService {

    @Autowired
    private CountryDatabaseDAO countryDatabaseDAO;

    public Country getCountry(Long id) {
        return countryDatabaseDAO.getById(id);
    }

    public void addCountry(Country country) {
        countryDatabaseDAO.add(country);
    }
}
