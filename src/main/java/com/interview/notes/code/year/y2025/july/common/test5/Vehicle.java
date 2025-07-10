package com.interview.notes.code.year.y2025.july.common.test5;

// Step 1: Define an interface
public interface Vehicle {
    void drive();
}

// Step 2: Create concrete implementations
  class Car implements Vehicle {
    @Override
    public void drive() {
        System.out.println("Driving a car...");
    }
}

  class Bike implements Vehicle {
    @Override
    public void drive() {
        System.out.println("Riding a bike...");
    }
}

// Step 3: Create the Factory
  class VehicleFactory {
    public static Vehicle getVehicle(String type) {
        return switch (type.toLowerCase()) {
            case "car" -> new Car();
            case "bike" -> new Bike();
            default -> throw new IllegalArgumentException("Unknown vehicle type");
        };
    }
}

// Step 4: Use the factory
  class Main1 {
    public static void main(String[] args) {
        Vehicle vehicle = VehicleFactory.getVehicle("car");
        vehicle.drive(); // Output: Driving a car...
    }
}
