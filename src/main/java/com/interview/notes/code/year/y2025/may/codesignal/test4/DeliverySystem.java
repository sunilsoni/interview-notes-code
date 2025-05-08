package com.interview.notes.code.year.y2025.may.codesignal.test4;

import java.time.LocalDateTime;
import java.time.Duration;
import java.util.*;

public class DeliverySystem {
    // Store driver rates
    private Map<Integer, Double> driverRates = new HashMap<>();
    
    // Store all deliveries (brute force approach)
    private List<Delivery> deliveries = new ArrayList<>();
    
    // Delivery class to store delivery details
    private static class Delivery {
        int driverId;
        LocalDateTime startTime;
        LocalDateTime endTime;
        
        Delivery(int driverId, LocalDateTime startTime, LocalDateTime endTime) {
            this.driverId = driverId;
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }

    public void addDriver(int driverId, double usdHourlyRate) {
        driverRates.put(driverId, usdHourlyRate);
    }

    public void recordDelivery(int driverId, LocalDateTime startTime, LocalDateTime endTime) {
        deliveries.add(new Delivery(driverId, startTime, endTime));
    }

    public double getTotalCost() {
        double totalCost = 0.0;
        
        // Iterate through each delivery and calculate cost
        for(Delivery delivery : deliveries) {
            double hourlyRate = driverRates.get(delivery.driverId);
            Duration duration = Duration.between(delivery.startTime, delivery.endTime);
            double hours = duration.toMinutes() / 60.0;
            totalCost += hourlyRate * hours;
        }
        
        return totalCost;
    }

    public static void main(String[] args) {
        // Your specific test case
        System.out.println("Running specified test case:");
        DeliverySystem billingSystem = new DeliverySystem();

        // Add drivers with their rates
        billingSystem.addDriver(1, 35.10);
        billingSystem.addDriver(2, 15.15);
        billingSystem.addDriver(3, 8.55);
        billingSystem.addDriver(4, 11.28);

        // Convert epoch seconds to LocalDateTime
        LocalDateTime baseTime = LocalDateTime.of(1970, 1, 1, 0, 0);

        // Record deliveries
        billingSystem.recordDelivery(1,
                baseTime,
                baseTime.plusSeconds(3600));    // 1 hour delivery for driver 1

        billingSystem.recordDelivery(2,
                baseTime,
                baseTime.plusSeconds(5400));    // 1.5 hour delivery for driver 2

        billingSystem.recordDelivery(2,
                baseTime.plusSeconds(5400),
                baseTime.plusSeconds(7200));    // 0.5 hour delivery for driver 2

        System.out.println("Total cost: $" + String.format("%.2f", billingSystem.getTotalCost()));
        System.out.println();

        // Original test cases continue below...
        System.out.println("Starting Additional Test Cases...\n");

        // [Rest of the original test cases...]
        // ... [Previous test cases remain unchanged]
    }

}
