package com.netcracker.edu.project.service;

import com.netcracker.edu.project.dao.impl.RoleDatabaseDAO;
import com.netcracker.edu.project.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends AbstractEntityService<Role>{

    @Autowired
    private RoleDatabaseDAO roleDatabaseDAO;

    @Override
    protected RoleDatabaseDAO getDao() {
        return roleDatabaseDAO;
    }
}
