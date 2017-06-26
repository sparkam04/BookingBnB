package com.netcracker.edu.project.service;

import com.netcracker.edu.project.dao.impl.ImageDatabaseDAO;
import com.netcracker.edu.project.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService extends AbstractEntityService<Image>{

    @Autowired
    private ImageDatabaseDAO imageDatabaseDAO;

    @Override
    protected ImageDatabaseDAO getDao() {
        return imageDatabaseDAO;
    }
}
