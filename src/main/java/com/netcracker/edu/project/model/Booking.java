package com.netcracker.edu.project.model;

import java.sql.Date;

/**
 * Created by Alexander on 19.05.2017.
 */
public class Booking {
    protected long id;
    protected long roomId;
    protected long userId;
    protected long statusId;
    protected long paySysId;
    protected long code;

    protected int numPersons;

    protected boolean isPaid;

    protected String message;

    protected Date checkIn;
    protected Date checkOut;
}
