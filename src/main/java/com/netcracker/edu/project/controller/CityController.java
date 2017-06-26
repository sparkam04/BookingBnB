package com.netcracker.edu.project.controller;

import com.netcracker.edu.project.model.City;
import com.netcracker.edu.project.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class CityController {

    @Autowired
    private CityService cityService;

    @RequestMapping("/city")
    public Collection<City> getAllCities() {
        return cityService.getAllEntities();
    }

    @RequestMapping("/city/{id}")
    public City getCity(@PathVariable Long id) {
        return cityService.getEntity(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/city")
    public boolean addCity(@RequestBody City city) {
        return cityService.addEntity(city);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/city")
    public boolean updateCity(@RequestBody City city) {
        return cityService.updateEntity(city);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/city/{id}")
    public boolean deleteCity(@PathVariable Long id) {
        return cityService.removeEntity(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/city")
    public boolean deleteCity(@RequestBody City city) {
        return cityService.removeEntity(city);
    }
}
