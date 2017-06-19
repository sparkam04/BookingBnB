package com.netcracker.edu.project.service;

import com.netcracker.edu.project.dao.UserDAO;
import com.netcracker.edu.project.dao.impl.UserDatabaseDAO;
import com.netcracker.edu.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Alexander on 19.06.2017.
 */
@Service
public class UserService extends AbstractEntityService<User>{

    @Autowired
    private UserDatabaseDAO userDatabaseDAO;

    @Override
    protected UserDAO getDao() {
        return userDatabaseDAO;
    }
}
