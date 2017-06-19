package com.netcracker.edu.project.service;

import com.netcracker.edu.project.dao.RatingDAO;
import com.netcracker.edu.project.dao.impl.RatingDatabaseDAO;
import com.netcracker.edu.project.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Alexander on 19.06.2017.
 */
@Service
public class RatingService extends AbstractEntityService<Rating>{

    @Autowired
    private RatingDatabaseDAO ratingDatabaseDAO;

    @Override
    protected RatingDAO getDao() {
        return ratingDatabaseDAO;
    }
}
