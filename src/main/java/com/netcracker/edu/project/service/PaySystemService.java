package com.netcracker.edu.project.service;

import com.netcracker.edu.project.dao.PaySystemDAO;
import com.netcracker.edu.project.dao.impl.PaySystemDatabaseDAO;
import com.netcracker.edu.project.model.PaySystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Alexander on 19.06.2017.
 */
@Service
public class PaySystemService extends AbstractEntityService<PaySystem>{

    @Autowired
    private PaySystemDatabaseDAO paySystemDatabaseDAO;

    @Override
    protected PaySystemDAO getDao() {
        return paySystemDatabaseDAO;
    }
}
