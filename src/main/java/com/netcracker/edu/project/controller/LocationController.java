package com.netcracker.edu.project.controller;

import com.netcracker.edu.project.model.Location;
import com.netcracker.edu.project.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class LocationController {

    @Autowired
    private LocationService locationService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping("/location")
    public Collection<Location> getAllLocations() {
        return locationService.getAllEntities();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping("/location/city/{id}")
    public Collection<Location> getLocationsByCity(@PathVariable Long id) {
        return locationService.getEntitiesByParentId(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping("/location/{id}")
    public Location getLocation(@PathVariable Long id) {
        return locationService.getEntity(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/location/streetAddress")
    public Location getLocationByStreetAddress(@RequestBody Location location) {
        return locationService.getByStreetAddress(location);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/location")
    public boolean addLocation(@RequestBody Location location) {
        return locationService.addEntity(location);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping(method = RequestMethod.PUT, value = "/location")
    public boolean updateLocation(@RequestBody Location location) {
        return locationService.updateEntity(location);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping(method = RequestMethod.DELETE, value = "/location/{id}")
    public boolean deleteLocation(@PathVariable Long id) {
        return locationService.removeEntity(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping(method = RequestMethod.DELETE, value = "/location")
    public boolean deleteLocation(@RequestBody Location location) {
        return locationService.removeEntity(location);
    }
}
