package com.netcracker.edu.project.model;

import java.util.ArrayList;

/**
 * Created by Alexander on 19.05.2017.
 */
public class Room {
    protected long id;
    protected long hotelId;

    protected int numOfPlaces;

    protected double cost;

    protected boolean hasBathroom;
    protected boolean hasTV;
    protected boolean hasExtraBed;

    protected ArrayList<String> images;
}
