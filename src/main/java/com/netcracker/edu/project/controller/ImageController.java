package com.netcracker.edu.project.controller;

import com.netcracker.edu.project.model.Image;
import com.netcracker.edu.project.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping("/image")
    public Collection<Image> getAllImages() {
        return imageService.getAllEntities();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping("/image/{id}")
    public Image getImage(@PathVariable Long id) {
        return imageService.getEntity(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/image")
    public boolean addImage(@RequestBody Image image) {
        return imageService.addEntity(image);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping(method = RequestMethod.PUT, value = "/image")
    public boolean updateImage(@RequestBody Image image) {
        return imageService.updateEntity(image);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping(method = RequestMethod.DELETE, value = "/image/{id}")
    public boolean deleteImage(@PathVariable Long id) {
        return imageService.removeEntity(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_SYSADMIN')")
    @RequestMapping(method = RequestMethod.DELETE, value = "/image")
    public boolean deleteImage(@RequestBody Image image) {
        return imageService.removeEntity(image);
    }
}
