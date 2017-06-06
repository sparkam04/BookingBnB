package com.netcracker.edu.project.Model;

public class Location extends Model {
    private Long cityId;

    private String streetAddress;
    private String postalCode;
    private String GPSCoords;

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
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
