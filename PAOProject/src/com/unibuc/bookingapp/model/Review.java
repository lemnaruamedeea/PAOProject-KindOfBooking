package com.unibuc.bookingapp.model;

// Clasa pentru reprezentarea unei recenzii
public class Review {
    private int id;
    private int accommodationId;
    private int userId;
    private String comment;
    private double rating;

    public Review(int id, int accommodationId, int userId, String comment, double rating) {
        this.id = id;
        this.accommodationId = accommodationId;
        this.userId = userId;
        this.comment = comment;
        this.rating = rating;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}