package com.netcracker.edu.project.controller;

import com.netcracker.edu.project.model.Location;
import com.netcracker.edu.project.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class LocationController {

    @Autowired
    private LocationService locationService;

    @RequestMapping("/location")
    public Collection<Location> getAllLocations() {
        return locationService.getAllEntities();
    }

    @RequestMapping("/location/city/{id}")
    public Collection<Location> getLocationsByCity(@PathVariable Long id) {
        return locationService.getEntitiesByParentId(id);
    }

    @RequestMapping("/location/{id}")
    public Location getLocation(@PathVariable Long id) {
        return locationService.getEntity(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/location")
    public boolean addLocation(@RequestBody Location location) {
        return locationService.addEntity(location);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/location")
    public boolean updateLocation(@RequestBody Location location) {
        return locationService.updateEntity(location);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/location/{id}")
    public boolean deleteLocation(@PathVariable Long id) {
        return locationService.removeEntity(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/location")
    public boolean deleteLocation(@RequestBody Location location) {
        return locationService.removeEntity(location);
    }
}
