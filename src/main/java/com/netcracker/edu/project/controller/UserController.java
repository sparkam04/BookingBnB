package com.netcracker.edu.project.controller;

import com.netcracker.edu.project.model.User;
import com.netcracker.edu.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by Alexander on 20.06.2017.
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/User")
    public Collection<User> getAllCountries() {
        return userService.getAllEntities();
    }

    @RequestMapping("/User/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getEntity(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/User")
    public boolean addUser(@RequestBody User user) {
        return userService.addEntity(user);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/User")
    public boolean updateUser(@RequestBody User user) {
        return userService.updateEntity(user);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/User/{id}")
    public boolean deleteUser(@PathVariable Long id) {
        return userService.removeEntity(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/User")
    public boolean deleteUser(@RequestBody User user) {
        return userService.removeEntity(user);
    }
}
