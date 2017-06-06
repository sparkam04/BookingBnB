package com.netcracker.edu.project.Controller;

import com.netcracker.edu.project.Model.City;
import com.netcracker.edu.project.Service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CityController {

    @Autowired
    private CityService cityService;

    @RequestMapping("/City")
    public String getAllCities() {
        return "TO DO";
    }

    @RequestMapping("/City/{id}")
    public City getCity(@PathVariable Long id) {
        return cityService.getEntity(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/City")
    public boolean addCity(@RequestBody City city) {
        return cityService.addEntity(city);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/City")
    public boolean updateCity(@RequestBody City city) {
        return cityService.updateEntity(city);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/City/{id}")
    public boolean deleteCity(@PathVariable Long id) {
        return cityService.removeEntity(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/City")
    public boolean deleteCity(@RequestBody City city) {
        return cityService.removeEntity(city);
    }
}
