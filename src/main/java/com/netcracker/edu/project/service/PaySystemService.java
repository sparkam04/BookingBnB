package com.netcracker.edu.project.service;

import com.netcracker.edu.project.dao.impl.PaySystemDatabaseDAO;
import com.netcracker.edu.project.model.PaySystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaySystemService extends AbstractEntityService<PaySystem>{

    @Autowired
    private PaySystemDatabaseDAO paySystemDatabaseDAO;

    @Override
    protected PaySystemDatabaseDAO getDao() {
        return paySystemDatabaseDAO;
    }
}
