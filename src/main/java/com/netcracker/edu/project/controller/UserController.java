package com.netcracker.edu.project.controller;

import com.netcracker.edu.project.model.User;
import com.netcracker.edu.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/user")
    public Collection<User> getAllUsers() {
        return userService.getAllEntities();
    }

    @RequestMapping("/user/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getEntity(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/user")
    public boolean addUser(@RequestBody User user) {
        return userService.addEntity(user);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/user")
    public boolean updateUser(@RequestBody User user) {
        return userService.updateEntity(user);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/user/{id}")
    public boolean deleteUser(@PathVariable Long id) {
        return userService.removeEntity(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/user")
    public boolean deleteUser(@RequestBody User user) {
        return userService.removeEntity(user);
    }
}
