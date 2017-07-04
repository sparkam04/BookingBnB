package com.netcracker.edu.project.dao;

import com.netcracker.edu.project.model.User;

public interface UserDAO extends EntityDAO<User> {
    User getByEmail(String email);
}
