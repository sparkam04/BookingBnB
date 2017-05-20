package com.netcracker.edu.project.model;

public class Location extends Model {
    private Long cityId;

    private String streetAdress;
    private String postalCode;
    private String GPSCoords;

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
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
