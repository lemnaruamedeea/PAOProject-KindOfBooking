package com.unibuc.bookingapp.model;

import java.util.List;

// Clasa abstractă pentru reprezentarea unei cazări
public abstract class Accommodation {
    private int id;
    private String name;
    private String country;
    private String city;

    public Accommodation(int id, String name, String country, String city) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public abstract double price(double basePrice);

    // Metoda abstractă pentru a obține rating-ul
    private List<Review> reviews;

    // Metoda pentru a seta lista de recenzii asociate cazării
    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    // Metoda pentru a calcula media rating-urilor
    public double calculateAverageRating() {
        if (reviews == null || reviews.isEmpty()) {
            return 0.0; // Returnăm 0 dacă nu există recenzii sau lista este goală
        }

        double totalRating = 0;
        for (Review review : reviews) {
            totalRating += review.getRating();
        }

        return (double) totalRating / reviews.size();
    }
}
