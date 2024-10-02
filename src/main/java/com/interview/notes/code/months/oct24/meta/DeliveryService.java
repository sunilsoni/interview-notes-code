package com.interview.notes.code.months.oct24.meta;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class DeliveryService {
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private List<Driver> drivers = new ArrayList<>();

    public DeliveryService() {
        // Populate some drivers for simulation purposes
        drivers.add(new Driver(1, "Driver A", 4.9, true, 2.5));
        drivers.add(new Driver(2, "Driver B", 4.5, true, 1.2));
        drivers.add(new Driver(3, "Driver C", 4.7, false, 3.0)); // This driver is busy
    }

    // Step 1: Place the order and schedule the driver assignment
    public void placeOrder(Order order) {
        System.out.println("Order " + order.orderId + " placed at restaurant " + order.restaurant);

        // Step 2: Schedule a task to assign a driver closer to the preparation time
        scheduler.schedule(() -> {
            System.out.println("Checking for driver assignment for order " + order.orderId);
            order.setPrepared(true);
            assignDriver(order);
        }, order.preparationTime - TimeUnit.MINUTES.toMillis(5), TimeUnit.MILLISECONDS); // Schedule 5 minutes before ready
    }

    // Step 3: Assign the best available driver when the food is almost ready
    private void assignDriver(Order order) {
        if (!order.isPrepared) {
            System.out.println("Order is not ready yet for delivery.");
            return;
        }

        // Find the available drivers
        List<Driver> availableDrivers = new ArrayList<>();
        for (Driver driver : drivers) {
            if (driver.available) {
                availableDrivers.add(driver);
            }
        }

        if (availableDrivers.isEmpty()) {
            System.out.println("No available drivers for order " + order.orderId);
            return;
        }

        // Rank drivers by distance to the restaurant and rating
        availableDrivers.sort((d1, d2) -> {
            // Rank by distance first, then by rating
            if (Double.compare(d1.currentDistanceToRestaurant, d2.currentDistanceToRestaurant) == 0) {
                return Double.compare(d2.rating, d1.rating);
            }
            return Double.compare(d1.currentDistanceToRestaurant, d2.currentDistanceToRestaurant);
        });

        // Assign the closest driver
        Driver selectedDriver = availableDrivers.get(0);
        System.out.println("Driver " + selectedDriver.name + " assigned to order " + order.orderId);

        // Simulate sending the driver to the restaurant
        selectedDriver.available = false;
        System.out.println("Driver " + selectedDriver.name + " is en route to pick up the order.");
    }
}
