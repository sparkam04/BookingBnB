package com.netcracker.edu.project.model;

import java.sql.Time;
import java.util.ArrayList;

/**
 * Created by Alexander on 19.05.2017.
 */
public class Hotel {
    protected long id;
    protected long ownerId;
    protected long locationId;

    protected double hotelRating;

    protected boolean hasWifi;
    protected boolean hasShuttle;
    protected boolean hasSmoking;
    protected boolean hasParking;
    protected boolean hasConditioning;
    protected boolean hasPets;
    protected boolean hasPool;
    protected boolean hasKitchen;
    protected boolean hasBreakfast;

    protected boolean isPreorder;

    protected String hotelName;
    protected String phone;
    protected String description;

    protected Time checkInTime;
    protected Time checkOutTime;

    protected ArrayList<String> images;
    protected ArrayList<Long> paySystems;
}
