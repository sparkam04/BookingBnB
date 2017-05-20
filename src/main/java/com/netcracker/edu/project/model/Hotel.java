package com.netcracker.edu.project.model;

import java.sql.Time;
import java.util.Collection;

/**
 * Created by Alexander on 19.05.2017.
 */
public class Hotel {
    private long id;
    private long ownerId;
    private long locationId;

    private double hotelRating;

    private boolean hasWifi;
    private boolean hasShuttle;
    private boolean hasSmoking;
    private boolean hasParking;
    private boolean hasConditioning;
    private boolean hasPets;
    private boolean hasPool;
    private boolean hasKitchen;
    private boolean hasBreakfast;

    private boolean isPreorder;

    private String hotelName;
    private String phone;
    private String description;

    private Time checkInTime;
    private Time checkOutTime;

    private Collection<String> images;
    private Collection<Long> paySysIds;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    public double getHotelRating() {
        return hotelRating;
    }

    public void setHotelRating(double hotelRating) {
        this.hotelRating = hotelRating;
    }

    public boolean isHasWifi() {
        return hasWifi;
    }

    public void setHasWifi(boolean hasWifi) {
        this.hasWifi = hasWifi;
    }

    public boolean isHasShuttle() {
        return hasShuttle;
    }

    public void setHasShuttle(boolean hasShuttle) {
        this.hasShuttle = hasShuttle;
    }

    public boolean isHasSmoking() {
        return hasSmoking;
    }

    public void setHasSmoking(boolean hasSmoking) {
        this.hasSmoking = hasSmoking;
    }

    public boolean isHasParking() {
        return hasParking;
    }

    public void setHasParking(boolean hasParking) {
        this.hasParking = hasParking;
    }

    public boolean isHasConditioning() {
        return hasConditioning;
    }

    public void setHasConditioning(boolean hasConditioning) {
        this.hasConditioning = hasConditioning;
    }

    public boolean isHasPets() {
        return hasPets;
    }

    public void setHasPets(boolean hasPets) {
        this.hasPets = hasPets;
    }

    public boolean isHasPool() {
        return hasPool;
    }

    public void setHasPool(boolean hasPool) {
        this.hasPool = hasPool;
    }

    public boolean isHasKitchen() {
        return hasKitchen;
    }

    public void setHasKitchen(boolean hasKitchen) {
        this.hasKitchen = hasKitchen;
    }

    public boolean isHasBreakfast() {
        return hasBreakfast;
    }

    public void setHasBreakfast(boolean hasBreakfast) {
        this.hasBreakfast = hasBreakfast;
    }

    public boolean isPreorder() {
        return isPreorder;
    }

    public void setPreorder(boolean preorder) {
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

    public Collection<String> getImages() {
        return images;
    }

    public void setImages(Collection<String> images) {
        this.images = images;
    }

    public Collection<Long> getPaySysIds() {
        return paySysIds;
    }

    public void setPaySysIds(Collection<Long> paySysIds) {
        this.paySysIds = paySysIds;
    }
}
