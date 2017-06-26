package com.netcracker.edu.project.controller;

import com.netcracker.edu.project.model.Image;
import com.netcracker.edu.project.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class ImageController {

    @Autowired
    private ImageService imageService;

    @RequestMapping("/image")
    public Collection<Image> getAllImages() {
        return imageService.getAllEntities();
    }

    @RequestMapping("/image/{id}")
    public Image getImage(@PathVariable Long id) {
        return imageService.getEntity(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/image")
    public boolean addImage(@RequestBody Image image) {
        return imageService.addEntity(image);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/image")
    public boolean updateImage(@RequestBody Image image) {
        return imageService.updateEntity(image);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/image/{id}")
    public boolean deleteImage(@PathVariable Long id) {
        return imageService.removeEntity(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/image")
    public boolean deleteImage(@RequestBody Image image) {
        return imageService.removeEntity(image);
    }
}
