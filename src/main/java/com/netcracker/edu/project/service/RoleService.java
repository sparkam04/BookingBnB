package com.netcracker.edu.project.service;

import com.netcracker.edu.project.dao.RoleDAO;
import com.netcracker.edu.project.dao.impl.RoleDatabaseDAO;
import com.netcracker.edu.project.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Alexander on 19.06.2017.
 */
@Service
public class RoleService extends AbstractEntityService<Role>{

    @Autowired
    private RoleDatabaseDAO roleDatabaseDAO;

    @Override
    protected RoleDAO getDao() {
        return roleDatabaseDAO;
    }
}
