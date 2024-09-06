package com.interview.notes.code.months.sept24.test1;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Venmo Parking Lot:

Description:

Your goal is to create a multi-level parking lot for people to park their cars. They will leave their cars for a set amount of time and be charged when leaving the parking lot.
Acceptance Criteria
1. The parking lot will have multiple levels and the same number of parking spaces on each level. The number of floors and spots are unknown.
2. When a customer enters the parking lot they will choose a space and be given a ticket. Your solution will need to keep track of this information.
3. When a customer wishes to leave they will give their ticket to the automated ticket taker when exiting the parking lot. They will then be presented with the amount owed based on how long they were parked.
4. The total fee must be calculated
a. The first hour is free
b. Ever hour after that is $1.50
 */
class ParkingLot {
    private List<ParkingLevel> levels;
    private Map<String, Ticket> occupiedSpots;

    public ParkingLot(int numLevels, int spotsPerLevel) {
        levels = new ArrayList<>();
        for (int i = 0; i < numLevels; i++) {
            levels.add(new ParkingLevel(i, spotsPerLevel));
        }
        occupiedSpots = new HashMap<>();
    }

    public Ticket parkVehicle() {
        for (ParkingLevel level : levels) {
            ParkingSpot spot = level.findAvailableSpot();
            if (spot != null) {
                Ticket ticket = new Ticket(spot);
                occupiedSpots.put(spot.getId(), ticket);
                return ticket;
            }
        }
        return null; // Parking lot is full
    }

    public double retrieveVehicle(Ticket ticket) {
        if (!occupiedSpots.containsKey(ticket.getSpot().getId())) {
            return -1; // Invalid ticket
        }
        occupiedSpots.remove(ticket.getSpot().getId());
        ticket.getSpot().setAvailable(true);
        return TicketProcessor.calculateFee(ticket);
    }
}

class ParkingLevel {
    private int levelNumber;
    private List<ParkingSpot> spots;

    public ParkingLevel(int levelNumber, int numSpots) {
        this.levelNumber = levelNumber;
        this.spots = new ArrayList<>();
        for (int i = 0; i < numSpots; i++) {
            spots.add(new ParkingSpot(levelNumber + "-" + i));
        }
    }

    public ParkingSpot findAvailableSpot() {
        for (ParkingSpot spot : spots) {
            if (spot.isAvailable()) {
                spot.setAvailable(false);
                return spot;
            }
        }
        return null;
    }
}

class ParkingSpot {
    private String id;
    private boolean available;

    public ParkingSpot(String id) {
        this.id = id;
        this.available = true;
    }

    public String getId() {
        return id;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

class Ticket {
    private ParkingSpot spot;
    private LocalDateTime entryTime;

    public Ticket(ParkingSpot spot) {
        this.spot = spot;
        this.entryTime = LocalDateTime.now();
    }

    public ParkingSpot getSpot() {
        return spot;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }
}

class TicketProcessor {
    private static final double HOURLY_RATE = 1.50;

    public static double calculateFee(Ticket ticket) {
        Duration parkingDuration = Duration.between(ticket.getEntryTime(), LocalDateTime.now());
        long hours = parkingDuration.toHours();
        if (hours <= 1) {
            return 0; // First hour is free
        }
        return (hours - 1) * HOURLY_RATE;
    }
}

public class Main {
    public static void main(String[] args) {
        // Test case 1: Park a vehicle and retrieve it within 1 hour
        ParkingLot parkingLot = new ParkingLot(3, 10);
        Ticket ticket1 = parkingLot.parkVehicle();
        System.out.println("Test case 1: " + (parkingLot.retrieveVehicle(ticket1) == 0 ? "PASS" : "FAIL"));

        // Test case 2: Park a vehicle and retrieve it after 2.5 hours
        Ticket ticket2 = parkingLot.parkVehicle();
        try {
            Thread.sleep(2500); // Simulate 2.5 hours passing (2.5 seconds in this case)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        double fee = parkingLot.retrieveVehicle(ticket2);
        System.out.println("Test case 2: " + (Math.abs(fee - 2.25) < 0.01 ? "PASS" : "FAIL"));

        // Test case 3: Attempt to retrieve a vehicle with an invalid ticket
        Ticket invalidTicket = new Ticket(new ParkingSpot("invalid"));
        System.out.println("Test case 3: " + (parkingLot.retrieveVehicle(invalidTicket) == -1 ? "PASS" : "FAIL"));

        // Test case 4: Park multiple vehicles and ensure correct spot assignment
        ParkingLot smallLot = new ParkingLot(1, 2);
        Ticket t1 = smallLot.parkVehicle();
        Ticket t2 = smallLot.parkVehicle();
        Ticket t3 = smallLot.parkVehicle();
        System.out.println("Test case 4: " + (t1 != null && t2 != null && t3 == null ? "PASS" : "FAIL"));
    }
}
