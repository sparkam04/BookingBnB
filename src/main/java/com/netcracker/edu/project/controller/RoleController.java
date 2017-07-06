package com.netcracker.edu.project.controller;

import com.netcracker.edu.project.model.Role;
import com.netcracker.edu.project.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping("/role")
    public Collection<Role> getAllRoles() {
        return roleService.getAllEntities();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping("/role/{id}")
    public Role getRole(@PathVariable Long id) {
        return roleService.getEntity(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/role")
    public boolean addRole(@RequestBody Role role) {
        return roleService.addEntity(role);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping(method = RequestMethod.PUT, value = "/role")
    public boolean updateRole(@RequestBody Role role) {
        return roleService.updateEntity(role);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping(method = RequestMethod.DELETE, value = "/role/{id}")
    public boolean deleteRole(@PathVariable Long id) {
        return roleService.removeEntity(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping(method = RequestMethod.DELETE, value = "/role")
    public boolean deleteRole(@RequestBody Role role) {
        return roleService.removeEntity(role);
    }
}
