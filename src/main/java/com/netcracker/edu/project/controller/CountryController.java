package com.netcracker.edu.project.controller;

import com.netcracker.edu.project.service.CountryService;
import com.netcracker.edu.project.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class CountryController {

    @Autowired
    private CountryService countryService;

    @RequestMapping("/country")
    public Collection<Country> getAllCountries() {
        return countryService.getAllEntities();
    }

    @RequestMapping("/country/{id}")
    public Country getCountry(@PathVariable Long id) {
        return countryService.getEntity(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/country")
    public boolean addCountry(@RequestBody Country country) {
        return countryService.addEntity(country);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/country")
    public boolean updateCountry(@RequestBody Country country) {
        return countryService.updateEntity(country);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/country/{id}")
    public boolean deleteCountry(@PathVariable Long id) {
        return countryService.removeEntity(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/country")
    public boolean deleteCountry(@RequestBody Country country) {
        return countryService.removeEntity(country);
    }
}
