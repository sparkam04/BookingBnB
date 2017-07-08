package com.netcracker.edu.project.service;

import com.netcracker.edu.project.dao.impl.UserDatabaseDAO;
import com.netcracker.edu.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractEntityService<User> {

    @Autowired
    private UserDatabaseDAO userDatabaseDAO;

    @Autowired
    private EmailService emailService;

    @Override
    protected UserDatabaseDAO getDao() {
        return userDatabaseDAO;
    }

    public User getUserByEmail(String email) {return getDao().getByEmail(email);}

    @Override
    public boolean addEntity(User user) {
        if (super.addEntity(user)){
            user = userDatabaseDAO.getByEmail(user.getEmail());
            emailService.sendMessageUserCreated(user);
            return true;
        } else {
            return false;
        }
    }
}
