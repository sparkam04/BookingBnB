package com.netcracker.edu.project.model;

import org.springframework.security.core.GrantedAuthority;

public class Role extends Model implements GrantedAuthority {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        if ("Client".compareTo(name) == 0)
            return "ROLE_CLIENT";
        if ("Role Admin".compareTo(name) == 0)
            return "ROLE_ADMIN";
        if ("Hotel Owner".compareTo(name) == 0)
            return "ROLE_SYSTEM";
        return null;
    }
}
