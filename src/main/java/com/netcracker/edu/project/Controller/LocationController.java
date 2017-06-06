package com.netcracker.edu.project.Controller;

import com.netcracker.edu.project.Model.Location;
import com.netcracker.edu.project.Service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LocationController {

    @Autowired
    private LocationService locationService;

    @RequestMapping("/Location")
    public String getAllLocations() {
        return "TO DO";
    }

    @RequestMapping("/Location/{id}")
    public Location getLocation(@PathVariable Long id) {
        return locationService.getEntity(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/Location")
    public boolean addLocation(@RequestBody Location location) {
        return locationService.addEntity(location);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/Location")
    public boolean updateLocation(@RequestBody Location location) {
        return locationService.updateEntity(location);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/Location/{id}")
    public boolean deleteLocation(@PathVariable Long id) {
        return locationService.removeEntity(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/Location")
    public boolean deleteLocation(@RequestBody Location location) {
        return locationService.removeEntity(location);
    }
}
