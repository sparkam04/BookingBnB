package com.netcracker.edu.project.Controller;

import com.netcracker.edu.project.Service.CountryService;
import com.netcracker.edu.project.Model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CountryController {

    @Autowired
    private CountryService countryService;

    @RequestMapping("/Country")
    public String getAllCountries() {
        return "TO DO";
    }

    @RequestMapping("/Country/{id}")
    public Country getCountry(@PathVariable Long id) {
        return countryService.getCountry(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/Country")
    public boolean addCountry(@RequestBody Country country) {
        return countryService.addCountry(country);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/Country")
    public boolean updateCountry(@RequestBody Country country) {
        return countryService.updateCountry(country);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/Country/{id}")
    public boolean deleteCountry(@PathVariable Long id) {
        return countryService.removeCountry(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/Country")
    public boolean deleteCountry(@RequestBody Country country) {
        return countryService.removeCountry(country);
    }
}
