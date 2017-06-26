package com.netcracker.edu.project.service;

import com.netcracker.edu.project.dao.impl.RatingDatabaseDAO;
import com.netcracker.edu.project.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService extends AbstractEntityService<Rating>{

    @Autowired
    private RatingDatabaseDAO ratingDatabaseDAO;

    @Override
    protected RatingDatabaseDAO getDao() {
        return ratingDatabaseDAO;
    }
}
