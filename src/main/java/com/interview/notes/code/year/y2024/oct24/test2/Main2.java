package com.interview.notes.code.year.y2024.oct24.test2;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Car {
    private final String brand;
    private final String model;
    private final int distanceDriven; // in kilometers

    public Car(String brand, String model, int distanceDriven) {
        this.brand = brand;
        this.model = model;
        this.distanceDriven = distanceDriven;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getDistanceDriven() {
        return distanceDriven;
    }
}

public class Main2 {
    public static void main(String[] args) {
        // Create a list of Car objects
        List<Car> cars = Arrays.asList(
                new Car("Toyota", "Camry", 15000),
                new Car("Honda", "Accord", 20000),
                new Car("Ford", "Focus", 12000),
                new Car("Toyota", "Corolla", 18000),
                new Car("Honda", "Civic", 22000),
                new Car("Chevrolet", "Malibu", 18000)
        );

        // Group by brand and count the number of cars in each brand
        Map<String, Long> carCountByBrand = cars.stream()
                .collect(Collectors.groupingBy(Car::getBrand, Collectors.counting()));

        // Print the result
        carCountByBrand.forEach((brand, count) ->
                System.out.println("Brand: " + brand + ", Count: " + count));
    }
}
