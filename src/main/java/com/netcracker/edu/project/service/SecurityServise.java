package com.netcracker.edu.project.service;

import com.netcracker.edu.project.dao.impl.RoleDatabaseDAO;
import com.netcracker.edu.project.dao.impl.UserDatabaseDAO;
import com.netcracker.edu.project.model.Role;
import com.netcracker.edu.project.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SecurityServise {

    @Autowired
    private UserDatabaseDAO userDatabaseDAO;

    @Autowired
    private RoleDatabaseDAO roleDatabaseDAO;

    public boolean registerUser(User user) {
        if (userDatabaseDAO.getByEmail(user.getEmail()) != null) {
            return false;
        }
        user.setRoleId(20L);
        userDatabaseDAO.add(user);
        return true;
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
            Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            String rolename = null;
            if ("Client".equals(role.getName())) {
                rolename = "CLIENT";
            }
            if ("Hotel Owner".equals(role.getName())) {
                rolename = "ADMIN";
            }
            if ("Role Admin".equals(role.getName())) {
                rolename = "SYSADMIN";
            }
            authorities.add(new SimpleGrantedAuthority(rolename));

            tokenMap.put("role", rolename);

            String token = Jwts.builder().setSubject(email).claim("roles", authorities).setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
            tokenMap.put("token", token);
            return tokenMap;
        } else {
            return null;
        }

    }
}
