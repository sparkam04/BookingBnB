package com.netcracker.edu.project.service;

import com.netcracker.edu.project.dao.impl.RoleDatabaseDAO;
import com.netcracker.edu.project.dao.impl.UserDatabaseDAO;
import com.netcracker.edu.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractEntityService<User> implements UserDetailsService{

    @Autowired
    private UserDatabaseDAO userDatabaseDAO;

    @Autowired
    private RoleDatabaseDAO roleDatabaseDAO;

    @Override
    protected UserDatabaseDAO getDao() {
        return userDatabaseDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDatabaseDAO.getByEmail(username);
        try {
            user.setAuthoritie(roleDatabaseDAO.getById(user.getRoleId()));
        } catch (NullPointerException e) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }
}
