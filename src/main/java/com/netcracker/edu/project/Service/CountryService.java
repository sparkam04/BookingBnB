package com.netcracker.edu.project.Service;

import com.netcracker.edu.project.dao.impl.CountryDatabaseDAO;
import com.netcracker.edu.project.Model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryService {

    @Autowired
    private CountryDatabaseDAO countryDatabaseDAO;

    public Country getCountry(Long id) {
        return countryDatabaseDAO.getById(id);
    }

    public boolean addCountry(Country country) {
        return countryDatabaseDAO.add(country);
    }

    public boolean removeCountry(Country country) {
        return countryDatabaseDAO.remove(country);
    }

    public boolean removeCountry(Long id) {
        return countryDatabaseDAO.remove(id);
    }

    public boolean updateCountry(Country country) {
        return countryDatabaseDAO.update(country);
    }
}
