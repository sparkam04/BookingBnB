package com.netcracker.edu.project.controller;

import com.netcracker.edu.project.model.Status;
import com.netcracker.edu.project.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by Alexander on 20.06.2017.
 */
@RestController
public class StatusController {

    @Autowired
    private StatusService statusService;

    @RequestMapping("/Status")
    public Collection<Status> getAllCountries() {
        return statusService.getAllEntities();
    }

    @RequestMapping("/Status/{id}")
    public Status getStatus(@PathVariable Long id) {
        return statusService.getEntity(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/Status")
    public boolean addStatus(@RequestBody Status status) {
        return statusService.addEntity(status);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/Status")
    public boolean updateStatus(@RequestBody Status status) {
        return statusService.updateEntity(status);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/Status/{id}")
    public boolean deleteStatus(@PathVariable Long id) {
        return statusService.removeEntity(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/Status")
    public boolean deleteStatus(@RequestBody Status status) {
        return statusService.removeEntity(status);
    }
}
