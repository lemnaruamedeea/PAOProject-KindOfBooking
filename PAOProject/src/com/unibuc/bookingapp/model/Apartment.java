package com.unibuc.bookingapp.model;

// Clasa care reprezintă un apartament și moștenește clasa Accommodation
public class Apartment extends Accommodation {
    private int numberOfRooms;
    private boolean hasAC;

    public Apartment(int id, String name, String country, String city, int numberOfRooms, boolean hasAC) {
        super(id, name, country, city);
        this.hasAC = hasAC;
        this.numberOfRooms = numberOfRooms;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public boolean hasAC() {
        return hasAC;
    }

    public void setHasAC(boolean hasAC) {
        this.hasAC = hasAC;
    }

    // Implementarea metodei abstracte pentru a calcula prețul în funcție de existența aerului condiționat
    @Override
    public double price(double basePrice) {
        double extra = 50.0;
        // Dacă apartamentul are aer condiționat, adăugăm un cost suplimentar
        if (hasAC) {
            return basePrice + extra; // Adăugăm 50 la prețul de bază
        } else {
            return basePrice; // Returnăm doar prețul de bază
        }
    }
}
