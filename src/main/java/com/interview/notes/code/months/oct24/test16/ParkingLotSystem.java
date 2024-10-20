package com.interview.notes.code.months.oct24.test16;

import java.util.ArrayList;
import java.util.List;

enum VehicleSize {
    MOTORCYCLE, COMPACT, LARGE
}

// Vehicle Class Hierarchy
abstract class Vehicle {
    public abstract VehicleSize getSize();
}

class Motorcycle extends Vehicle {
    public VehicleSize getSize() {
        return VehicleSize.MOTORCYCLE;
    }
}

class Car extends Vehicle {
    public VehicleSize getSize() {
        return VehicleSize.COMPACT;
    }
}

class Van extends Vehicle {
    public VehicleSize getSize() {
        return VehicleSize.LARGE;
    }
}

// Spot class representing a parking spot
class Spot {
    private VehicleSize size;
    private boolean isAvailable;

    public Spot(VehicleSize size) {
        this.size = size;
        this.isAvailable = true;
    }

    public boolean canFitVehicle(Vehicle vehicle) {
        return isAvailable && vehicle.getSize().ordinal() <= this.size.ordinal();
    }

    public void park() {
        isAvailable = false;
    }

    public void leave() {
        isAvailable = true;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public VehicleSize getSize() {
        return size;
    }
}

// Level class representing a parking level
class Level {
    private List<Spot> spots;

    public Level(int motorcycleSpots, int carSpots, int largeSpots) {
        spots = new ArrayList<>();
        for (int i = 0; i < motorcycleSpots; i++) {
            spots.add(new Spot(VehicleSize.MOTORCYCLE));
        }
        for (int i = 0; i < carSpots; i++) {
            spots.add(new Spot(VehicleSize.COMPACT));
        }
        for (int i = 0; i < largeSpots; i++) {
            spots.add(new Spot(VehicleSize.LARGE));
        }
    }

    public boolean parkVehicle(Vehicle vehicle) {
        for (Spot spot : spots) {
            if (spot.canFitVehicle(vehicle)) {
                spot.park();
                return true;
            }
        }
        return false;
    }

    public boolean isFull() {
        return spots.stream().noneMatch(Spot::isAvailable);
    }

    public int getAvailableSpots() {
        return (int) spots.stream().filter(Spot::isAvailable).count();
    }
}

// ParkingLot class representing the entire parking structure
class ParkingLot {
    private List<Level> levels;

    public ParkingLot(List<Level> levels) {
        this.levels = levels;
    }

    public boolean parkVehicle(Vehicle vehicle) {
        for (Level level : levels) {
            if (level.parkVehicle(vehicle)) {
                return true;
            }
        }
        return false; // No available spots
    }

    public boolean isFull() {
        return levels.stream().allMatch(Level::isFull);
    }

    public int getAvailableSpots() {
        return levels.stream().mapToInt(Level::getAvailableSpots).sum();
    }
}

// Main class to test parking
public class ParkingLotSystem {

    public static void main(String[] args) {
        // Create a parking lot with 3 levels
        List<Level> levels = new ArrayList<>();
        levels.add(new Level(10, 20, 5));
        levels.add(new Level(5, 10, 3));
        levels.add(new Level(3, 6, 2));

        ParkingLot parkingLot = new ParkingLot(levels);

        // Test Cases
        testParkingLot(parkingLot);
    }

    public static void testParkingLot(ParkingLot parkingLot) {
        Vehicle motorcycle = new Motorcycle();
        Vehicle car = new Car();
        Vehicle van = new Van();

        // Park a motorcycle
        System.out.println(parkingLot.parkVehicle(motorcycle)); // Should return true

        // Park a car
        System.out.println(parkingLot.parkVehicle(car)); // Should return true

        // Park a van
        System.out.println(parkingLot.parkVehicle(van)); // Should return true

        // Large data test
        for (int i = 0; i < 1000; i++) {
            parkingLot.parkVehicle(new Car()); // Simulate large number of cars
        }

        // Check if parking lot is full
        System.out.println("Is Parking Lot Full: " + parkingLot.isFull());

        // Check available spots
        System.out.println("Available Spots: " + parkingLot.getAvailableSpots());
    }
}
