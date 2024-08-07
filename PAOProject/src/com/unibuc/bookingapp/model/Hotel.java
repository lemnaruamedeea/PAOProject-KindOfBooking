package com.unibuc.bookingapp.model;

// Clasa pentru reprezentarea unui hotel
public class Hotel extends Accommodation {
    private int starRating;
    private boolean pool;
    private int freeRooms;

    public Hotel(int id, String name, String country, String city, int starRating, boolean pool, int freeRooms) {
        super(id, name, country, city);
        this.starRating = starRating;
        this.pool = pool;
        this.freeRooms = freeRooms;
    }

    public int getStarRating() {
        return starRating;
    }

    public void setStarRating(int starRating) {
        this.starRating = starRating;
    }

    public boolean hasPool() {
        return pool;
    }

    public void setPool(boolean pool) {
        this.pool = pool;
    }

    public int getFreeRooms() {
        return freeRooms;
    }

    public void setFreeRooms(int freeRooms) {
        this.freeRooms = freeRooms;
    }

    @Override
    public double price(double basePrice) {
        double extra = 100.0;
        // Dacă hotelul are piscină, adăugăm un cost suplimentar
        if (pool) {
            return basePrice + extra; // Adăugăm 100 la prețul de bază
        } else {
            return basePrice; // Returnăm doar prețul de bază
        }
    }

}
