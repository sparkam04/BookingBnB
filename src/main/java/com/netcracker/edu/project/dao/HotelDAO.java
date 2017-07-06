package com.netcracker.edu.project.dao;

import com.netcracker.edu.project.model.Hotel;

import java.util.Collection;

public interface HotelDAO extends EntityDAO<Hotel> {
    Double getRatingById(Long id);

    Collection<Hotel> getHotelsByOwnerId(Long ownerId);
}
