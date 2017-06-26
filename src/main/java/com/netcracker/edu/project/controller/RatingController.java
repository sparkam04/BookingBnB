package com.netcracker.edu.project.controller;

import com.netcracker.edu.project.model.Rating;
import com.netcracker.edu.project.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @RequestMapping("/rating")
    public Collection<Rating> getAllRatings() {
        return ratingService.getAllEntities();
    }

    @RequestMapping("/rating/{id}")
    public Rating getRating(@PathVariable Long id) {
        return ratingService.getEntity(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/rating")
    public boolean addRating(@RequestBody Rating rating) {
        return ratingService.addEntity(rating);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/rating")
    public boolean updateRating(@RequestBody Rating rating) {
        return ratingService.updateEntity(rating);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/rating/{id}")
    public boolean deleteRating(@PathVariable Long id) {
        return ratingService.removeEntity(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/rating")
    public boolean deleteRating(@RequestBody Rating rating) {
        return ratingService.removeEntity(rating);
    }
}
