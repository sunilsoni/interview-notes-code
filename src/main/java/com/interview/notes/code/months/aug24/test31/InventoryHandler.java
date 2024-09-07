package com.interview.notes.code.months.aug24.test31;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryHandler {

    public List<String> handleInventory(List<Car> carsList) {
        return carsList.stream()
                .filter(car -> car.getMiles() <= 50000)
                .map(car -> {
                    // Add "360 degree view" to features
                    car.addFeature("360 degree view");
                    return String.format("%s (%d) - %d miles, Features: %s",
                            car.getName(), car.getYear(), car.getMiles(),
                            String.join(", ", car.getFeatures()));
                })
                .collect(Collectors.toList());
    }
}

class Car {
    private String name;
    private int year;
    private int miles;
    private List<String> features;

    // Constructor
    public Car(String name, int year, int miles, String... features) {
        this.name = name;
        this.year = year;
        this.miles = miles;
        this.features = new ArrayList<>(Arrays.asList(features));
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public int getMiles() {
        return miles;
    }

    public List<String> getFeatures() {
        return features;
    }

    // Method to add a feature
    public void addFeature(String feature) {
        if (!features.contains(feature)) {
            features.add(feature);
        }
    }
}
