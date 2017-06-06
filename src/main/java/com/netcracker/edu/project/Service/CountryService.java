package com.netcracker.edu.project.Service;

import com.netcracker.edu.project.DAO.EntityDAO;
import com.netcracker.edu.project.DAO.impl.CountryDatabaseDAO;
import com.netcracker.edu.project.Model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryService extends AbstractEntityService<Country>{

    @Autowired
    private CountryDatabaseDAO countryDatabaseDAO;

    @Override
    protected EntityDAO<Country> getDao() {
        return countryDatabaseDAO;
    }
}
