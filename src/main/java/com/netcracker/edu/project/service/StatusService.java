package com.netcracker.edu.project.service;

import com.netcracker.edu.project.dao.StatusDAO;
import com.netcracker.edu.project.dao.impl.StatusDatabaseDAO;
import com.netcracker.edu.project.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Alexander on 19.06.2017.
 */
@Service
public class StatusService extends AbstractEntityService<Status> {

    @Autowired
    private StatusDatabaseDAO statusDatabaseDAO;

    @Override
    protected StatusDAO getDao() {
        return statusDatabaseDAO;
    }
}
