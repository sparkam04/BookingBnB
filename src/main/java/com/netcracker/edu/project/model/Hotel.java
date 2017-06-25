package com.netcracker.edu.project.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Collection;
import java.util.Date;

public class Hotel extends Model {
    public static final String TIME_FORMAT = "hh:mm";

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

    @JsonFormat(pattern = TIME_FORMAT)
    private Date checkInTime;
    @JsonFormat(pattern = TIME_FORMAT)
    private Date checkOutTime;

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

    public void setHotelRating(Double hotelRating) {
        this.hotelRating = hotelRating;
    }

    public Boolean isHasWifi() {
        return hasWifi;
    }

    public Boolean isHasShuttle() {
        return hasShuttle;
    }

    public Boolean isHasSmoking() {
        return hasSmoking;
    }

    public Boolean isHasParking() {
        return hasParking;
    }

    public Boolean isHasConditioning() {
        return hasConditioning;
    }

    public Boolean isHasPets() {
        return hasPets;
    }

    public Boolean isHasPool() {
        return hasPool;
    }

    public Boolean isHasKitchen() {
        return hasKitchen;
    }

    public Boolean isHasBreakfast() {
        return hasBreakfast;
    }

    public Boolean isPreorder() {
        return isPreorder;
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

    public void setHasWifi(Boolean hasWifi) {
        this.hasWifi = hasWifi;
    }

    public void setHasShuttle(Boolean hasShuttle) {
        this.hasShuttle = hasShuttle;
    }

    public void setHasSmoking(Boolean hasSmoking) {
        this.hasSmoking = hasSmoking;
    }

    public void setHasParking(Boolean hasParking) {
        this.hasParking = hasParking;
    }

    public void setHasConditioning(Boolean hasConditioning) {
        this.hasConditioning = hasConditioning;
    }

    public void setHasPets(Boolean hasPets) {
        this.hasPets = hasPets;
    }

    public void setHasPool(Boolean hasPool) {
        this.hasPool = hasPool;
    }

    public void setHasKitchen(Boolean hasKitchen) {
        this.hasKitchen = hasKitchen;
    }

    public void setHasBreakfast(Boolean hasBreakfast) {
        this.hasBreakfast = hasBreakfast;
    }

    public void setPreorder(Boolean preorder) {
        isPreorder = preorder;
    }

    public Date getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(Date checkInTime) {
        this.checkInTime = checkInTime;
    }

    public Date getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(Date checkOutTime) {
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
