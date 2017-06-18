package com.netcracker.edu.project.model;

import java.util.Collection;

public class Room extends Model {
    private Long hotelId;

    private Integer roomId;
    private Integer numOfPlaces;

    private Double cost;

    private Boolean hasBathroom;
    private Boolean hasTV;
    private Boolean hasExtraBed;

    private Collection<String> images;

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Boolean getHasBathroom() {
        return hasBathroom;
    }

    public void setHasBathroom(Boolean hasBathroom) {
        this.hasBathroom = hasBathroom;
    }

    public Boolean getHasTV() {
        return hasTV;
    }

    public void setHasTV(Boolean hasTV) {
        this.hasTV = hasTV;
    }

    public Boolean getHasExtraBed() {
        return hasExtraBed;
    }

    public void setHasExtraBed(Boolean hasExtraBed) {
        this.hasExtraBed = hasExtraBed;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public Integer getNumOfPlaces() {
        return numOfPlaces;
    }

    public void setNumOfPlaces(Integer numOfPlaces) {
        this.numOfPlaces = numOfPlaces;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Boolean isHasBathroom() {
        return hasBathroom;
    }

    public Boolean isHasTV() {
        return hasTV;
    }

    public Boolean isHasExtraBed() {
        return hasExtraBed;
    }

    public Collection<String> getImages() {
        return images;
    }

    public void setImages(Collection<String> images) {
        this.images = images;
    }
}
