package com.netcracker.edu.project.service;

import com.netcracker.edu.project.dao.impl.StatusDatabaseDAO;
import com.netcracker.edu.project.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusService extends AbstractEntityService<Status>{

    @Autowired
    private StatusDatabaseDAO statusDatabaseDAO;

    @Override
    protected StatusDatabaseDAO getDao() {
        return statusDatabaseDAO;
    }
}
