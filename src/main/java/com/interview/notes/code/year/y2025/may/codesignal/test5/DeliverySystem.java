package com.interview.notes.code.year.y2025.may.codesignal.test5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeliverySystem {
    private Map<Integer, Double> driverRates = new HashMap<>();
    private List<Delivery> deliveries = new ArrayList<>();

    public static void main(String[] args) {
        DeliverySystem billingSystem = new DeliverySystem();

        billingSystem.addDriver(1, 35.10);
        billingSystem.addDriver(2, 15.15);
        billingSystem.addDriver(3, 8.55);
        billingSystem.addDriver(4, 11.28);
        billingSystem.recordDelivery(1, 0, 3600);    // Jan 1 1970 00:00:00 - Jan 1 1970 01:00:00
        billingSystem.recordDelivery(2, 0, 5400);    // Jan 1 1970 00:00:00 - Jan 1 1970 01:30:00
        billingSystem.recordDelivery(2, 5400, 7200); // Jan 1 1970 01:30:00 - Jan 1 1970 02:00:00
        System.out.println(billingSystem.getTotalCost());
    }

    public void addDriver(int driverId, double usdHourlyRate) {
        if (usdHourlyRate < 0) {
            throw new IllegalArgumentException("Hourly rate cannot be negative");
        }
        driverRates.put(driverId, usdHourlyRate);
    }

    public void recordDelivery(int driverId, long startTime, long endTime) {
        if (!driverRates.containsKey(driverId)) {
            throw new IllegalArgumentException("Driver ID " + driverId + " not found");
        }
        if (endTime < startTime) {
            throw new IllegalArgumentException("End time cannot be before start time");
        }
        deliveries.add(new Delivery(driverId, startTime, endTime));
    }

    public double getTotalCost() {
        return deliveries.stream()
                .mapToDouble(delivery -> {
                    double hourlyRate = driverRates.get(delivery.driverId);
                    long durationSeconds = delivery.endTime - delivery.startTime;
                    return hourlyRate * durationSeconds / 3600.0; // Convert seconds to hours
                })
                .sum();
    }

    private static class Delivery {
        int driverId;
        long startTime;  // in seconds
        long endTime;    // in seconds

        Delivery(int driverId, long startTime, long endTime) {
            this.driverId = driverId;
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }
}
