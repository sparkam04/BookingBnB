package com.netcracker.edu.project.controller;

import com.netcracker.edu.project.model.Rating;
import com.netcracker.edu.project.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping("/rating")
    public Collection<Rating> getAllRatings() {
        return ratingService.getAllEntities();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping("/rating/booking/{id}")
    public Collection<Rating> getRatingByBooking(@PathVariable Long id) {
        return ratingService.getEntitiesByParentId(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping("/rating/{id}")
    public Rating getRating(@PathVariable Long id) {
        return ratingService.getEntity(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/rating")
    public boolean addRating(@RequestBody Rating rating) {
        return ratingService.addEntity(rating);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping(method = RequestMethod.PUT, value = "/rating")
    public boolean updateRating(@RequestBody Rating rating) {
        return ratingService.updateEntity(rating);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping(method = RequestMethod.DELETE, value = "/rating/{id}")
    public boolean deleteRating(@PathVariable Long id) {
        return ratingService.removeEntity(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping(method = RequestMethod.DELETE, value = "/rating")
    public boolean deleteRating(@RequestBody Rating rating) {
        return ratingService.removeEntity(rating);
    }
}
