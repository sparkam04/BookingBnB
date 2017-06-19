package com.netcracker.edu.project.service;

import com.netcracker.edu.project.dao.ImageDAO;
import com.netcracker.edu.project.dao.impl.ImageDatabaseDAO;
import com.netcracker.edu.project.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Alexander on 19.06.2017.
 */
@Service
public class ImageService extends AbstractEntityService<Image> {

    @Autowired
    private ImageDatabaseDAO imageDatabaseDAO;

    @Override
    protected ImageDAO getDao() {
        return imageDatabaseDAO;
    }
}
