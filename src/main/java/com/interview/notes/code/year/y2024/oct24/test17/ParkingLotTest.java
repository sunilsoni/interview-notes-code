package com.interview.notes.code.year.y2024.oct24.test17;

import java.util.ArrayList;
import java.util.List;

// Enums
enum VehicleSize {
    SMALL, MEDIUM, LARGE
}

// Vehicle hierarchy
abstract class Vehicle {
    private String licensePlate;

    public Vehicle(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public abstract VehicleSize getSize();
}

class Motorcycle extends Vehicle {
    public Motorcycle(String licensePlate) {
        super(licensePlate);
    }

    @Override
    public VehicleSize getSize() {
        return VehicleSize.SMALL;
    }
}

class Car extends Vehicle {
    public Car(String licensePlate) {
        super(licensePlate);
    }

    @Override
    public VehicleSize getSize() {
        return VehicleSize.MEDIUM;
    }
}

class Van extends Vehicle {
    public Van(String licensePlate) {
        super(licensePlate);
    }

    @Override
    public VehicleSize getSize() {
        return VehicleSize.LARGE;
    }
}

// Spot hierarchy
abstract class ParkingSpot {
    private final String id;
    private Vehicle vehicle;

    public ParkingSpot(String id) {
        this.id = id;
    }

    public boolean isOccupied() {
        return vehicle != null;
    }

    public void park(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Vehicle removeVehicle() {
        Vehicle v = this.vehicle;
        this.vehicle = null;
        return v;
    }

    public abstract boolean canFitVehicle(Vehicle vehicle);

    public String getId() {
        return id;
    }
}

class SmallSpot extends ParkingSpot {
    public SmallSpot(String id) {
        super(id);
    }

    @Override
    public boolean canFitVehicle(Vehicle vehicle) {
        return vehicle.getSize() == VehicleSize.SMALL;
    }
}

class MediumSpot extends ParkingSpot {
    public MediumSpot(String id) {
        super(id);
    }

    @Override
    public boolean canFitVehicle(Vehicle vehicle) {
        return vehicle.getSize() == VehicleSize.SMALL || vehicle.getSize() == VehicleSize.MEDIUM;
    }
}

class LargeSpot extends ParkingSpot {
    public LargeSpot(String id) {
        super(id);
    }

    @Override
    public boolean canFitVehicle(Vehicle vehicle) {
        return true; // Can fit any vehicle
    }
}

// Level class
class ParkingLevel {
    private final String id;
    private final List<ParkingSpot> spots;

    public ParkingLevel(String id, int smallSpots, int mediumSpots, int largeSpots) {
        this.id = id;
        this.spots = new ArrayList<>();
        for (int i = 0; i < smallSpots; i++) {
            spots.add(new SmallSpot(id + "-S" + i));
        }
        for (int i = 0; i < mediumSpots; i++) {
            spots.add(new MediumSpot(id + "-M" + i));
        }
        for (int i = 0; i < largeSpots; i++) {
            spots.add(new LargeSpot(id + "-L" + i));
        }
    }

    public boolean parkVehicle(Vehicle vehicle) {
        for (ParkingSpot spot : spots) {
            if (!spot.isOccupied() && spot.canFitVehicle(vehicle)) {
                spot.park(vehicle);
                return true;
            }
        }
        return false;
    }

    public boolean isFull() {
        return spots.stream().allMatch(ParkingSpot::isOccupied);
    }

    public boolean isEmpty() {
        return spots.stream().noneMatch(ParkingSpot::isOccupied);
    }

    public int getAvailableSpots() {
        return (int) spots.stream().filter(spot -> !spot.isOccupied()).count();
    }

    public String getId() {
        return id;
    }
}

// ParkingLot class
class ParkingLot {
    private final List<ParkingLevel> levels;
    private int currentLevel;

    public ParkingLot(int numLevels, int smallSpotsPerLevel, int mediumSpotsPerLevel, int largeSpotsPerLevel) {
        levels = new ArrayList<>();
        for (int i = 0; i < numLevels; i++) {
            levels.add(new ParkingLevel("L" + i, smallSpotsPerLevel, mediumSpotsPerLevel, largeSpotsPerLevel));
        }
        currentLevel = 0;
    }

    public boolean parkVehicle(Vehicle vehicle) {
        for (int i = 0; i < levels.size(); i++) {
            if (levels.get(i).parkVehicle(vehicle)) {
                if (i > currentLevel) {
                    currentLevel = i;
                }
                return true;
            }
        }
        return false;
    }

    public int getTotalAvailableSpots() {
        return levels.stream().mapToInt(ParkingLevel::getAvailableSpots).sum();
    }

    public boolean isFull() {
        return levels.stream().allMatch(ParkingLevel::isFull);
    }

    public boolean isEmpty() {
        return levels.stream().allMatch(ParkingLevel::isEmpty);
    }

    public int getCurrentLevel() {
        return currentLevel;
    }
}

// Test class
public class ParkingLotTest {
    public static void main(String[] args) {
        testParkingLot();
    }

    private static void testParkingLot() {
        ParkingLot parkingLot = new ParkingLot(3, 10, 10, 10);

        // Test parking different types of vehicles
        assert parkingLot.parkVehicle(new Motorcycle("M001")) : "Failed to park motorcycle";
        assert parkingLot.parkVehicle(new Car("C001")) : "Failed to park car";
        assert parkingLot.parkVehicle(new Van("V001")) : "Failed to park van";

        // Test filling up first level
        for (int i = 0; i < 27; i++) {
            parkingLot.parkVehicle(new Car("C" + String.format("%03d", i + 2)));
        }

        assert parkingLot.getCurrentLevel() == 1 : "Failed to move to next level";

        // Test parking lot full scenario
        ParkingLot smallLot = new ParkingLot(1, 1, 1, 1);
        assert smallLot.parkVehicle(new Motorcycle("M002")) : "Failed to park motorcycle in small lot";
        assert smallLot.parkVehicle(new Car("C002")) : "Failed to park car in small lot";
        assert smallLot.parkVehicle(new Van("V002")) : "Failed to park van in small lot";
        assert !smallLot.parkVehicle(new Car("C003")) : "Incorrectly parked car in full lot";

        assert smallLot.isFull() : "Small lot should be full";
        assert !smallLot.isEmpty() : "Small lot should not be empty";

        System.out.println("All tests passed successfully!");
    }
}
