package com.interview.notes.code.months.nov23.test7;

import java.util.List;
import java.util.Scanner;

public class VehicleTrackingTest {
    public static void main1(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalNumberOfRequests = scanner.nextInt();
        long speedLimitInKm = scanner.nextLong();
        VehicleTrackingService trackingService = new VehicleTrackingService(speedLimitInKm);

        for (int i = 0; i < totalNumberOfRequests; i++) {
            String command = scanner.next();

            if (command.equals("registerVehicle")) {
                int vehicleNumber = scanner.nextInt();
                trackingService.registerVehicle(vehicleNumber);
            } else if (command.equals("getVehicleInfo")) {
                int vehicleNumber = scanner.nextInt();
                Vehicle vehicle = trackingService.getVehicleInfo(vehicleNumber);
                // Assuming you want to print some details about the vehicle
                System.out.println("Vehicle " + vehicleNumber + ": Last Polled Time - "
                        + vehicle.lastPolledInfoEpochTime + ", Number of Fines - "
                        + vehicle.numberOfTimesFinedImposed);
            } else if (command.equals("polledVehicleInfo")) {
                int vehicleNumber = scanner.nextInt();
                long distance = scanner.nextLong();
                long time = scanner.nextLong();
                boolean fined = trackingService.polledVehicleInfo(vehicleNumber, distance, time);
                System.out.println(fined ? "true" : "false");
            } else if (command.equals("fineHistory")) {
                int vehicleNumber = scanner.nextInt();
                int K = scanner.nextInt();
                List<Long> fines = trackingService.fineHistory(vehicleNumber, K);
                System.out.println("Fine History for Vehicle " + vehicleNumber + ": " + fines);
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        // Initialize the service with a speed limit of 60 km/h
        long speedLimitInKm = 60;
        VehicleTrackingService trackingService = new VehicleTrackingService(speedLimitInKm);

        // Register three vehicles with numbers 0, 1, and 2
        trackingService.registerVehicle(0);
        trackingService.registerVehicle(1);
        trackingService.registerVehicle(2);

        // Poll vehicle info with some test values
        trackingService.polledVehicleInfo(0, 1, 1600000000); // No fine expected, first poll
        trackingService.polledVehicleInfo(0, 10, 1600000200); // Fine expected, speed = 180 km/h
        trackingService.polledVehicleInfo(0, 12, 1600000800); // Fine expected, speed = 72 km/h
        trackingService.polledVehicleInfo(1, 1, 1600000030); // No fine expected, speed = 120 km/h

        // Get vehicle info and print details
        Vehicle vehicle0 = trackingService.getVehicleInfo(0);
        System.out.println("Vehicle 0: Last Polled Time - " + vehicle0.lastPolledInfoEpochTime +
                ", Number of Fines - " + vehicle0.numberOfTimesFinedImposed);

        // Get fine history for vehicle 0
        List<Long> finesVehicle0 = trackingService.fineHistory(0, 2); // Get last 2 fines
        System.out.println("Fine History for Vehicle 0: " + finesVehicle0);

        // Assuming the Vehicle class and VehicleTrackingService class are implemented as previously described.
    }
}
