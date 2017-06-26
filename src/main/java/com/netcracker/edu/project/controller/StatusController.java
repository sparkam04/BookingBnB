package com.netcracker.edu.project.controller;

import com.netcracker.edu.project.model.Status;
import com.netcracker.edu.project.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class StatusController {

    @Autowired
    private StatusService statusService;

    @RequestMapping("/status")
    public Collection<Status> getAllStatuses() {
        return statusService.getAllEntities();
    }

    @RequestMapping("/status/{id}")
    public Status getStatus(@PathVariable Long id) {
        return statusService.getEntity(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/status")
    public boolean addStatus(@RequestBody Status status) {
        return statusService.addEntity(status);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/status")
    public boolean updateStatus(@RequestBody Status status) {
        return statusService.updateEntity(status);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/status/{id}")
    public boolean deleteStatus(@PathVariable Long id) {
        return statusService.removeEntity(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/status")
    public boolean deleteStatus(@RequestBody Status status) {
        return statusService.removeEntity(status);
    }
}
