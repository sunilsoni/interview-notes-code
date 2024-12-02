package com.interview.notes.code.year.y2024.oct24.meta;

class Driver {
    int driverId;
    String name;
    double rating;
    boolean available;
    double currentDistanceToRestaurant; // Simulated distance in miles

    public Driver(int driverId, String name, double rating, boolean available, double currentDistanceToRestaurant) {
        this.driverId = driverId;
        this.name = name;
        this.rating = rating;
        this.available = available;
        this.currentDistanceToRestaurant = currentDistanceToRestaurant;
    }
}
