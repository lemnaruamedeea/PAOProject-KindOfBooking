package com.unibuc.bookingapp.model;

// Clasa pentru reprezentarea unei case de vacanță
public class VacationHouse extends Accommodation {
    private boolean parkingSpot;
    private int floors;
    private int freeRooms;

    public VacationHouse(int id, String name, String country, String city, boolean parkingSpot, int floors, int freeRooms) {
        super(id, name, country, city);
        this.parkingSpot = parkingSpot;
        this.floors = floors;
        this.freeRooms = freeRooms;
    }

    public boolean hasParkingSpot() {
        return parkingSpot;
    }

    public void setParkingLot(boolean parkingLot) {
        this.parkingSpot = parkingSpot;
    }

    public int getFloors() {
        return floors;
    }

    public void setFloors(int floors) {
        this.floors = floors;
    }

    public int getFreeRooms() {
        return freeRooms;
    }

    public void setFreeRooms(int freeRooms) {
        this.freeRooms = freeRooms;
    }

    @Override
    public double price(double basePrice) {
        double extra = 20.0;
        // Dacă casa de vacanță are loc de parcare, adăugăm un cost suplimentar
        if (parkingSpot) {
            return basePrice + extra; // Adăugăm 50 la prețul de bază
        } else {
            return basePrice; // Returnăm doar prețul de bază
        }
    }

}
