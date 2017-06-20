package com.netcracker.edu.project.controller;

import com.netcracker.edu.project.model.Role;
import com.netcracker.edu.project.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by Alexander on 20.06.2017.
 */
@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/Role")
    public Collection<Role> getAllCountries() {
        return roleService.getAllEntities();
    }

    @RequestMapping("/Role/{id}")
    public Role getRole(@PathVariable Long id) {
        return roleService.getEntity(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/Role")
    public boolean addRole(@RequestBody Role role) {
        return roleService.addEntity(role);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/Role")
    public boolean updateRole(@RequestBody Role role) {
        return roleService.updateEntity(role);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/Role/{id}")
    public boolean deleteRole(@PathVariable Long id) {
        return roleService.removeEntity(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/Role")
    public boolean deleteRole(@RequestBody Role role) {
        return roleService.removeEntity(role);
    }
}
