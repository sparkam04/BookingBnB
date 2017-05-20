package com.netcracker.edu.project.Controller;

import com.netcracker.edu.project.Service.CountryService;
import com.netcracker.edu.project.model.Country;
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
    public void addCountry(@RequestBody Country country) {
        countryService.addCountry(country);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/Country/{id}")
    public void updateCountry(@RequestBody Country country, @PathVariable Long id) {
        /* Todo */
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/Country/{id}")
    public void deleteCountry(@PathVariable Long id) {
        /* Todo */
    }
}
