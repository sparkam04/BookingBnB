package com.netcracker.edu.project.controller;

import com.netcracker.edu.project.model.User;
import com.netcracker.edu.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping("/user")
    public Collection<User> getAllUsers() {
        return userService.getAllEntities();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping("/user/role/{id}")
    public Collection<User> getUsersByRole(@PathVariable Long id) {
        return userService.getEntitiesByParentId(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping("/user/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getEntity(id);
    }

//    @RequestMapping("/user/email/{email:.+}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping("/user/email/{email}/")
    public User getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/user")
    public boolean addUser(@RequestBody User user) {
        return userService.addEntity(user);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping(method = RequestMethod.PUT, value = "/user")
    public boolean updateUser(@RequestBody User user) {
        return userService.updateEntity(user);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping(method = RequestMethod.DELETE, value = "/user/{id}")
    public boolean deleteUser(@PathVariable Long id) {
        return userService.removeEntity(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping(method = RequestMethod.DELETE, value = "/user")
    public boolean deleteUser(@RequestBody User user) {
        return userService.removeEntity(user);
    }
}
