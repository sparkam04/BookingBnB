package com.netcracker.edu.project.controller;

import com.netcracker.edu.project.model.Role;
import com.netcracker.edu.project.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/role")
    public Collection<Role> getAllRoles() {
        return roleService.getAllEntities();
    }

    @RequestMapping("/role/{id}")
    public Role getRole(@PathVariable Long id) {
        return roleService.getEntity(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/role")
    public boolean addRole(@RequestBody Role role) {
        return roleService.addEntity(role);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/role")
    public boolean updateRole(@RequestBody Role role) {
        return roleService.updateEntity(role);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/role/{id}")
    public boolean deleteRole(@PathVariable Long id) {
        return roleService.removeEntity(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/role")
    public boolean deleteRole(@RequestBody Role role) {
        return roleService.removeEntity(role);
    }
}
