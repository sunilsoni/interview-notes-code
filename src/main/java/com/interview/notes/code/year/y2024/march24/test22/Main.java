package com.interview.notes.code.year.y2024.march24.test22;

// Car class with optional parameters
class Car {
    private String model;
    private String color;
    private int year;
    private double price;

    // Private constructor to prevent direct instantiation
    private Car(Builder builder) {
        this.model = builder.model;
        this.color = builder.color;
        this.year = builder.year;
        this.price = builder.price;
    }

    // Getters for Car properties
    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public int getYear() {
        return year;
    }

    public double getPrice() {
        return price;
    }

    // Static nested Builder class
    static class Builder {
        private String model;
        private String color;
        private int year;
        private double price;

        // Builder methods for setting optional parameters
        public Builder setModel(String model) {
            this.model = model;
            return this;
        }

        public Builder setColor(String color) {
            this.color = color;
            return this;
        }

        public Builder setYear(int year) {
            this.year = year;
            return this;
        }

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }

        // Build method to construct the Car object
        public Car build() {
            return new Car(this);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Creating a Car object using the builder pattern
        Car car1 = new Car.Builder()
                .setModel("Toyota Camry")
                .setColor("Blue")
                .setYear(2022)
                .setPrice(25000.00)
                .build();

        // Creating another Car object with different configuration
        Car car2 = new Car.Builder()
                .setModel("Honda Accord")
                .setYear(2021)
                .setPrice(22000.00)
                .build();

        // Printing details of car1
        System.out.println("Car 1 details:");
        System.out.println("Model: " + car1.getModel());
        System.out.println("Color: " + car1.getColor());
        System.out.println("Year: " + car1.getYear());
        System.out.println("Price: $" + car1.getPrice());

        // Printing details of car2
        System.out.println("\nCar 2 details:");
        System.out.println("Model: " + car2.getModel());
        System.out.println("Color: " + car2.getColor()); // No color set, so it will be null
        System.out.println("Year: " + car2.getYear());
        System.out.println("Price: $" + car2.getPrice());
    }
}
