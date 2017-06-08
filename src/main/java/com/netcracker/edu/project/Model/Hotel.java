package com.netcracker.edu.project.Model;

import java.sql.Time;
import java.util.Collection;

public class Hotel extends Model {
    private Long ownerId;
    private Long locationId;

    private Double hotelRating;

    private Boolean hasWifi;
    private Boolean hasShuttle;
    private Boolean hasSmoking;
    private Boolean hasParking;
    private Boolean hasConditioning;
    private Boolean hasPets;
    private Boolean hasPool;
    private Boolean hasKitchen;
    private Boolean hasBreakfast;

    private Boolean isPreorder;

    private String hotelName;
    private String phone;
    private String description;

    private Time checkInTime;
    private Time checkOutTime;

    private Collection<Long> images;
    private Collection<Long> paySysIds;

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Double getHotelRating() {
        return hotelRating;
    }

    public void setHotelRating(double hotelRating) {
        this.hotelRating = hotelRating;
    }

    public Boolean isHasWifi() {
        return hasWifi;
    }

    public void setHasWifi(Boolean hasWifi) {
        this.hasWifi = hasWifi;
    }

    public Boolean isHasShuttle() {
        return hasShuttle;
    }

    public void setHasShuttle(Boolean hasShuttle) {
        this.hasShuttle = hasShuttle;
    }

    public Boolean isHasSmoking() {
        return hasSmoking;
    }

    public void setHasSmoking(Boolean hasSmoking) {
        this.hasSmoking = hasSmoking;
    }

    public Boolean isHasParking() {
        return hasParking;
    }

    public void setHasParking(Boolean hasParking) {
        this.hasParking = hasParking;
    }

    public Boolean isHasConditioning() {
        return hasConditioning;
    }

    public void setHasConditioning(Boolean hasConditioning) {
        this.hasConditioning = hasConditioning;
    }

    public Boolean isHasPets() {
        return hasPets;
    }

    public void setHasPets(Boolean hasPets) {
        this.hasPets = hasPets;
    }

    public Boolean isHasPool() {
        return hasPool;
    }

    public void setHasPool(Boolean hasPool) {
        this.hasPool = hasPool;
    }

    public Boolean isHasKitchen() {
        return hasKitchen;
    }

    public void setHasKitchen(Boolean hasKitchen) {
        this.hasKitchen = hasKitchen;
    }

    public Boolean isHasBreakfast() {
        return hasBreakfast;
    }

    public void setHasBreakfast(Boolean hasBreakfast) {
        this.hasBreakfast = hasBreakfast;
    }

    public Boolean isPreorder() {
        return isPreorder;
    }

    public void setPreorder(Boolean preorder) {
        isPreorder = preorder;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Time getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(Time checkInTime) {
        this.checkInTime = checkInTime;
    }

    public Time getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(Time checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public Collection<Long> getImages() {
        return images;
    }

    public void setImages(Collection<Long> images) {
        this.images = images;
    }

    public Collection<Long> getPaySysIds() {
        return paySysIds;
    }

    public void setPaySysIds(Collection<Long> paySysIds) {
        this.paySysIds = paySysIds;
    }
}
