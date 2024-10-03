package com.interview.notes.code.months.oct24.test1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

class Car {
    private String model;
    private int drivenKilometers;

    public Car(String model, int drivenKilometers) {
        this.model = model;
        this.drivenKilometers = drivenKilometers;
    }

    public String getModel() {
        return model;
    }

    public int getDrivenKilometers() {
        return drivenKilometers;
    }

    @Override
    public String toString() {
        return "Car{model='" + model + "', drivenKilometers=" + drivenKilometers + '}';
    }
}

public class Main1 {
    public static void main(String[] args) {
        List<Car> cars = Arrays.asList(
                new Car("Toyota", 50000),
                new Car("Honda", 75000),
                new Car("Ford", 60000),
                new Car("BMW", 90000)
        );

        // Find the car with the maximum driven kilometers
        Optional<Car> maxDrivenCar = cars.stream()
                .max(Comparator.comparingInt(Car::getDrivenKilometers));

        // Print the result
        maxDrivenCar.ifPresent(car -> System.out.println("Car with maximum driven kilometers: " + car));
    }
}
