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
    public String getAllCountries() {
        return "TO DO";
    }

    @RequestMapping("/City/{id}")
    public City getCountry(@PathVariable Long id) {
        return cityService.getCity(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/City")
    public boolean addCountry(@RequestBody City city) {
        return cityService.addCity(city);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/City")
    public boolean updateCountry(@RequestBody City city) {
        return cityService.updateCity(city);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/City/{id}")
    public boolean deleteCountry(@PathVariable Long id) {
        return cityService.removeCity(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/City")
    public boolean deleteCountry(@RequestBody City city) {
        return cityService.removeCity(city);
    }
}
