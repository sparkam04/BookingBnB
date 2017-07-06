package com.netcracker.edu.project.controller;

import com.netcracker.edu.project.model.City;
import com.netcracker.edu.project.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class CityController {

    @Autowired
    private CityService cityService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping("/city")
    public Collection<City> getAllCities() {
        return cityService.getAllEntities();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping("/city/country/{id}")
    public Collection<City> getCitiesByCountry(@PathVariable Long id) {
        return cityService.getEntitiesByParentId(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping("/city/{id}")
    public City getCity(@PathVariable Long id) {
        return cityService.getEntity(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/city")
    public boolean addCity(@RequestBody City city) {
        return cityService.addEntity(city);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping(method = RequestMethod.PUT, value = "/city")
    public boolean updateCity(@RequestBody City city) {
        return cityService.updateEntity(city);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping(method = RequestMethod.DELETE, value = "/city/{id}")
    public boolean deleteCity(@PathVariable Long id) {
        return cityService.removeEntity(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping(method = RequestMethod.DELETE, value = "/city")
    public boolean deleteCity(@RequestBody City city) {
        return cityService.removeEntity(city);
    }
}
