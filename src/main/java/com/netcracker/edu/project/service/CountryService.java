package com.netcracker.edu.project.service;

import com.netcracker.edu.project.dao.impl.CountryDatabaseDAO;
import com.netcracker.edu.project.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryService extends AbstractEntityService<Country>{

    @Autowired
    private CountryDatabaseDAO countryDatabaseDAO;

    @Override
    protected CountryDatabaseDAO getDao() {
        return countryDatabaseDAO;
    }
}
