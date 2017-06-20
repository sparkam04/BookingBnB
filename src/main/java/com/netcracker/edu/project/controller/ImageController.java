package com.netcracker.edu.project.controller;

import com.netcracker.edu.project.model.Image;
import com.netcracker.edu.project.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by Alexander on 20.06.2017.
 */
@RestController
public class ImageController {

    @Autowired
    private ImageService imageService;

    @RequestMapping("/Image")
    public Collection<Image> getAllCountries() {
        return imageService.getAllEntities();
    }

    @RequestMapping("/Image/{id}")
    public Image getImage(@PathVariable Long id) {
        return imageService.getEntity(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/Image")
    public boolean addImage(@RequestBody Image image) {
        return imageService.addEntity(image);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/Image")
    public boolean updateImage(@RequestBody Image image) {
        return imageService.updateEntity(image);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/Image/{id}")
    public boolean deleteImage(@PathVariable Long id) {
        return imageService.removeEntity(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/Image")
    public boolean deleteImage(@RequestBody Image image) {
        return imageService.removeEntity(image);
    }
}

