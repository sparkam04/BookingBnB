package com.netcracker.edu.project.model;

import java.util.Collection;

public class Room extends Model {
    private String roomName;

    private Long hotelId;

    private Integer roomNumber;
    private Integer numOfPlaces;
    private Double cost;
    private Boolean hasBathroom;

    private Boolean hasTV;
    private Boolean hasExtraBed;
    private Collection<Long> images;

    private Boolean isEnabled;

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
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

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
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

    public Collection<Long> getImages() {
        return images;
    }

    public void setImages(Collection<Long> images) {
        this.images = images;
    }
}
