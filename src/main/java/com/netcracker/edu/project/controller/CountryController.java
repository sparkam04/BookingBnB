package com.netcracker.edu.project.controller;

import com.netcracker.edu.project.service.CountryService;
import com.netcracker.edu.project.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class CountryController {

    @Autowired
    private CountryService countryService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping("/country")
    public Collection<Country> getAllCountries() {
        return countryService.getAllEntities();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping("/country/{id}")
    public Country getCountry(@PathVariable Long id) {
        return countryService.getEntity(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/country")
    public boolean addCountry(@RequestBody Country country) {
        return countryService.addEntity(country);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping(method = RequestMethod.PUT, value = "/country")
    public boolean updateCountry(@RequestBody Country country) {
        return countryService.updateEntity(country);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping(method = RequestMethod.DELETE, value = "/country/{id}")
    public boolean deleteCountry(@PathVariable Long id) {
        return countryService.removeEntity(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping(method = RequestMethod.DELETE, value = "/country")
    public boolean deleteCountry(@RequestBody Country country) {
        return countryService.removeEntity(country);
    }
}
