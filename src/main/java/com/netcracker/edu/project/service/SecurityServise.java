package com.netcracker.edu.project.service;

import com.netcracker.edu.project.dao.impl.RoleDatabaseDAO;
import com.netcracker.edu.project.dao.impl.UserDatabaseDAO;
import com.netcracker.edu.project.model.Role;
import com.netcracker.edu.project.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SecurityServise {

    @Autowired
    private UserDatabaseDAO userDatabaseDAO;

    @Autowired
    private RoleDatabaseDAO roleDatabaseDAO;

    @Autowired
    private EmailService emailService;

    public Map<String, Object> registerUser(User user) {
        Map<String, Object> map = new HashMap<>();
        Boolean isRegistered;
        String message;
        try {
            userDatabaseDAO.getByEmail(user.getEmail());
            message = "This user already exist!";
            isRegistered = false;
        } catch (EmptyResultDataAccessException e) {
            user.setRoleId(20L);
            userDatabaseDAO.add(user);
            emailService.sendMessageUserCreated(user);
            message = "Registered successful!";
            isRegistered = true;
        }
        map.put("isRegistered", isRegistered);
        map.put("message", message);
        return map;
    }

    public Map<String, Object> login(String email, String password) {
        User user = userDatabaseDAO.getByEmail(email);
        if (user != null && user.getPass().equals(password)) {
            Map<String, Object> tokenMap = new HashMap<String, Object>();
            tokenMap.put("userId", user.getId());
            tokenMap.put("firstName", user.getFirstName());
            tokenMap.put("lastName", user.getLastName());
            tokenMap.put("email", user.getEmail());
            tokenMap.put("phone", user.getPhone());

            Role role = roleDatabaseDAO.getById(user.getRoleId());
            List<String> roles = new ArrayList<String>();
            String rolename = null;
            if ("Client".equals(role.getName())) {
                rolename = "USER";
            }
            if ("Hotel Owner".equals(role.getName())) {
                rolename = "ADMIN";
            }
            if ("Admin".equals(role.getName())) {
                rolename = "SYSADMIN";
            }
            roles.add(rolename);

            tokenMap.put("role", rolename);

            String token = Jwts.builder().setSubject(email).claim("roles", roles).setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
            tokenMap.put("token", token);
            return tokenMap;
        } else {
            return null;
        }

    }
}
