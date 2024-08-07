package com.unibuc.bookingapp.model;

import java.time.LocalDate;

// Clasa pentru reprezentarea unei rezervări
public class Reservation {
    private int id;
    private int accommodationId;
    private int userId;
    private LocalDate startDate;
    private LocalDate endDate;
    private int numberOfRooms;

    public Reservation(int id, int accommodationId, int userId, LocalDate startDate, LocalDate endDate, int numberOfRooms) {
        this.id = id;
        this.accommodationId = accommodationId;
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfRooms = numberOfRooms;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccommodationId() {
        return accommodationId;
    }

    public void setAccommodationId(int accommodationId) {
        this.accommodationId = accommodationId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}