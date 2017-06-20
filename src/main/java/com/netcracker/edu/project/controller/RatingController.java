package com.netcracker.edu.project.controller;

import com.netcracker.edu.project.model.Rating;
import com.netcracker.edu.project.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by Alexander on 20.06.2017.
 */
@RestController
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @RequestMapping("/Rating")
    public Collection<Rating> getAllCountries() {
        return ratingService.getAllEntities();
    }

    @RequestMapping("/Rating/{id}")
    public Rating getRating(@PathVariable Long id) {
        return ratingService.getEntity(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/Rating")
    public boolean addRating(@RequestBody Rating rating) {
        return ratingService.addEntity(rating);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/Rating")
    public boolean updateRating(@RequestBody Rating rating) {
        return ratingService.updateEntity(rating);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/Rating/{id}")
    public boolean deleteRating(@PathVariable Long id) {
        return ratingService.removeEntity(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/Rating")
    public boolean deleteRating(@RequestBody Rating rating) {
        return ratingService.removeEntity(rating);
    }
}
