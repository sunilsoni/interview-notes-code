package com.interview.notes.code.year.y2025.may.codesignal.test1;

import java.util.*;

public class DeliverySystem {
    private Map<Integer, Double> driverRates = new HashMap<>();
    private List<Delivery> deliveries = new ArrayList<>();
    private long lastPaymentTime = 0;  // Track the last payment time

    private static class Delivery {
        int driverId;
        long startTime;  // in seconds
        long endTime;    // in seconds
        boolean isPaid;  // Track if delivery has been paid

        Delivery(int driverId, long startTime, long endTime) {
            this.driverId = driverId;
            this.startTime = startTime;
            this.endTime = endTime;
            this.isPaid = false;
        }
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
                return hourlyRate * durationSeconds / 3600.0;
            })
            .sum();
    }

    public void payUpTo(long payTime) {
        for (Delivery delivery : deliveries) {
            if (!delivery.isPaid && delivery.endTime <= payTime) {
                delivery.isPaid = true;
            }
        }
        lastPaymentTime = payTime;
    }

    public double getTotalCostUnpaid() {
        return deliveries.stream()
            .filter(delivery -> !delivery.isPaid)
            .mapToDouble(delivery -> {
                double hourlyRate = driverRates.get(delivery.driverId);
                long durationSeconds = delivery.endTime - delivery.startTime;
                return hourlyRate * durationSeconds / 3600.0;
            })
            .sum();
    }

    public static void main(String[] args) {
        DeliverySystem billingSystem = new DeliverySystem();

        billingSystem.addDriver(1, 35.10);
        billingSystem.addDriver(2, 15.15);
        billingSystem.addDriver(3, 8.55);
        billingSystem.addDriver(4, 11.28);
        
        // Record deliveries
        billingSystem.recordDelivery(1, 0, 3600);    // Jan 1 1970 00:00:00 - Jan 1 1970 01:00:00
        billingSystem.recordDelivery(2, 0, 5400);    // Jan 1 1970 00:00:00 - Jan 1 1970 01:30:00
        billingSystem.recordDelivery(2, 5400, 7200); // Jan 1 1970 01:30:00 - Jan 1 1970 02:00:00
        
        // Print initial total cost
        System.out.println("Initial total cost: $" + String.format("%.2f", billingSystem.getTotalCost()));
        System.out.println("Initial unpaid cost: $" + String.format("%.2f", billingSystem.getTotalCostUnpaid()));
        
        // Pay deliveries up to 1 hour
        billingSystem.payUpTo(3600);
        System.out.println("Unpaid cost after paying up to 1 hour: $" + 
                          String.format("%.2f", billingSystem.getTotalCostUnpaid()));
        
        // Pay all deliveries
        billingSystem.payUpTo(7200);
        System.out.println("Unpaid cost after paying all: $" + 
                          String.format("%.2f", billingSystem.getTotalCostUnpaid()));
    }
}
