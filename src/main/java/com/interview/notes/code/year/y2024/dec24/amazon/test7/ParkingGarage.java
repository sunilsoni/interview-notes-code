package com.interview.notes.code.year.y2024.dec24.amazon.test7;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/*
Design and implement a parking garage that provides valet parking services.
When a customer pulls up to the entrance they are either rejected because the garage is full, or they are given a ticket they can use to collect their car, and the car is parked for them.
You have three sizes of parking bays small, medium and large.
The cars that arrive will also be either small, medium or large. Small cars only go in small bays,
medium cars in medium bays and large cars in large bays.

How would you handle multiple entrances and exits to the parking garage? No only one entrace.
They go by then go party car, you bring them to get back, they go find your car and you leave. So you don't have to worry about doing it a bunch of time. Reserv.

How would you implement a reservation system? No need to impleemnt
They go by then go party car, you bring them to get back, they go find your car and you leave. So you don't have to worry about doing it a bunch of time. Reservations. Let's say no, these are only. Some retail time so only he's not the show, it's a like walk up whatever. So you drive up then you do it at the time. I think it says here if the garage is full, you get rejected. Yeah. so,


How would you handle peak hours and traffic management? No need its a static load all the time..no need to worry
so, the question was like, I mean, how do how do we handle handle the peak hours? Like our like if there are any peak hours management and and they there is any heavy traffic. Do we have any draft traffic management further? Let's just assume it's that, okay? In and out all the time. We don't need to. It's a garage. So It'll just take cars as they come in. Yeah, so let's yeah, let's not consider like peak traffic hours or anything.


How would you implement a payment system? its free parking based n ticket no need to implements.

 */
public class ParkingGarage {

    // Main method for testing
    public static void main(String[] args) {
        // Initialize the parking garage with specific bay counts
        ParkingGarageSystem garage = new ParkingGarageSystem(2, 2, 2);

        // List to store test results
        List<String> testResults = new ArrayList<>();

        // Test Case 1: Park a small car
        Ticket ticket1 = garage.parkCar(Size.SMALL);
        testResults.add(ticket1 != null && garage.getAvailableSmall() == 1 ? "PASS" : "FAIL");

        // Test Case 2: Park a medium car
        Ticket ticket2 = garage.parkCar(Size.MEDIUM);
        testResults.add(ticket2 != null && garage.getAvailableMedium() == 1 ? "PASS" : "FAIL");

        // Test Case 3: Park a large car
        Ticket ticket3 = garage.parkCar(Size.LARGE);
        testResults.add(ticket3 != null && garage.getAvailableLarge() == 1 ? "PASS" : "FAIL");

        // Test Case 4: Park another small car
        Ticket ticket4 = garage.parkCar(Size.SMALL);
        testResults.add(ticket4 != null && garage.getAvailableSmall() == 0 ? "PASS" : "FAIL");

        // Test Case 5: Attempt to park a small car when small bays are full
        Ticket ticket5 = garage.parkCar(Size.SMALL);
        testResults.add(ticket5 == null && garage.getAvailableSmall() == 0 ? "PASS" : "FAIL");

        // Test Case 6: Retrieve a car with a valid ticket
        boolean retrieve1 = garage.retrieveCar(ticket1.getId());
        testResults.add(retrieve1 && garage.getAvailableSmall() == 1 ? "PASS" : "FAIL");

        // Test Case 7: Attempt to retrieve a car with an invalid ticket
        boolean retrieve2 = garage.retrieveCar(999);
        testResults.add(!retrieve2 ? "PASS" : "FAIL");

        // Test Case 8: Attempt to retrieve the same car again
        boolean retrieve3 = garage.retrieveCar(ticket1.getId());
        testResults.add(!retrieve3 ? "PASS" : "FAIL");

        // Test Case 9: Park a large car when one bay is available
        Ticket ticket6 = garage.parkCar(Size.LARGE);
        testResults.add(ticket6 != null && garage.getAvailableLarge() == 0 ? "PASS" : "FAIL");

        // Test Case 10: Attempt to park a large car when large bays are full
        Ticket ticket7 = garage.parkCar(Size.LARGE);
        testResults.add(ticket7 == null && garage.getAvailableLarge() == 0 ? "PASS" : "FAIL");

        // Test Case 11: Large Data Input - Park and retrieve 1000 small cars
        ParkingGarageSystem largeGarage = new ParkingGarageSystem(1000, 1000, 1000);
        boolean largeTestPass = true;
        List<Ticket> largeTickets = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Ticket t = largeGarage.parkCar(Size.SMALL);
            if (t == null) {
                largeTestPass = false;
                break;
            }
            largeTickets.add(t);
        }
        // Attempt to park one more small car, should fail
        Ticket extraTicket = largeGarage.parkCar(Size.SMALL);
        if (extraTicket != null) {
            largeTestPass = false;
        }
        // Retrieve all parked cars
        for (Ticket t : largeTickets) {
            if (!largeGarage.retrieveCar(t.getId())) {
                largeTestPass = false;
                break;
            }
        }
        // After retrieval, all small bays should be available
        if (largeGarage.getAvailableSmall() != 1000) {
            largeTestPass = false;
        }
        testResults.add(largeTestPass ? "PASS" : "FAIL");

        // Output all test results
        for (int i = 0; i < testResults.size(); i++) {
            System.out.println("Test Case " + (i + 1) + ": " + testResults.get(i));
        }
    }

    // Enum for Car and Bay sizes
    enum Size {
        SMALL, MEDIUM, LARGE
    }

    // Ticket class to store ticket information
    static class Ticket {
        private final int id;
        private final Size size;

        public Ticket(int id, Size size) {
            this.id = id;
            this.size = size;
        }

        public int getId() {
            return id;
        }

        public Size getSize() {
            return size;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Ticket)) return false;
            Ticket other = (Ticket) obj;
            return this.id == other.id && this.size == other.size;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, size);
        }
    }

    // ParkingGarage class to manage parking operations
    static class ParkingGarageSystem {
        private final int totalSmall;
        private final int totalMedium;
        private final int totalLarge;
        private final Map<Integer, Ticket> activeTickets;
        private final AtomicInteger ticketCounter;
        private int availableSmall;
        private int availableMedium;
        private int availableLarge;

        public ParkingGarageSystem(int small, int medium, int large) {
            this.totalSmall = small;
            this.totalMedium = medium;
            this.totalLarge = large;
            this.availableSmall = small;
            this.availableMedium = medium;
            this.availableLarge = large;
            this.activeTickets = new HashMap<>();
            this.ticketCounter = new AtomicInteger(1);
        }

        // Method to park a car
        public synchronized Ticket parkCar(Size size) {
            switch (size) {
                case SMALL:
                    if (availableSmall > 0) {
                        availableSmall--;
                        Ticket ticket = new Ticket(ticketCounter.getAndIncrement(), size);
                        activeTickets.put(ticket.getId(), ticket);
                        return ticket;
                    }
                    break;
                case MEDIUM:
                    if (availableMedium > 0) {
                        availableMedium--;
                        Ticket ticket = new Ticket(ticketCounter.getAndIncrement(), size);
                        activeTickets.put(ticket.getId(), ticket);
                        return ticket;
                    }
                    break;
                case LARGE:
                    if (availableLarge > 0) {
                        availableLarge--;
                        Ticket ticket = new Ticket(ticketCounter.getAndIncrement(), size);
                        activeTickets.put(ticket.getId(), ticket);
                        return ticket;
                    }
                    break;
            }
            return null; // Garage is full for the given size
        }

        // Method to retrieve a car
        public synchronized boolean retrieveCar(int ticketId) {
            Ticket ticket = activeTickets.remove(ticketId);
            if (ticket == null) {
                return false; // Invalid ticket
            }
            switch (ticket.getSize()) {
                case SMALL:
                    availableSmall++;
                    break;
                case MEDIUM:
                    availableMedium++;
                    break;
                case LARGE:
                    availableLarge++;
                    break;
            }
            return true;
        }

        // Getters for available spaces (useful for testing)
        public int getAvailableSmall() {
            return availableSmall;
        }

        public int getAvailableMedium() {
            return availableMedium;
        }

        public int getAvailableLarge() {
            return availableLarge;
        }
    }
}
