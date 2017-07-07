package com.netcracker.edu.project.model;

import java.sql.Date;

public class Booking extends Model {
    private Long roomId;
    private Long userId;
    private Long statusId;
    private Long paySysId;
    private String code;

    private Integer numPersons;

    private Double cost;

    private Boolean isPaid;

    private String message;

    private Date checkIn;
    private Date checkOut;

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public Long getPaySysId() {
        return paySysId;
    }

    public void setPaySysId(Long paySysId) {
        this.paySysId = paySysId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getNumPersons() {
        return numPersons;
    }

    public void setNumPersons(Integer numPersons) {
        this.numPersons = numPersons;
    }

    public Boolean isPaid() {
        return isPaid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }
}
