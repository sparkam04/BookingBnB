package com.netcracker.edu.project.model;

/**
 * Created by Alexander on 19.05.2017.
 */
public class Location {
    private long id;
    private long cityId;

    private String streetAdress;
    private String postalCode;
    private String GPSCoords;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    public String getStreetAdress() {
        return streetAdress;
    }

    public void setStreetAdress(String streetAdress) {
        this.streetAdress = streetAdress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getGPSCoords() {
        return GPSCoords;
    }

    public void setGPSCoords(String GPSCoords) {
        this.GPSCoords = GPSCoords;
    }
}
