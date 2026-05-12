package com.interview.notes.code.year.y2026.may.common.test7;

import java.time.LocalDate; // Import LocalDate to handle date representations without timezones
import java.util.ArrayList; // Import ArrayList to store our dynamic list of active bookings
import java.util.List; // Import List interface for our bookings collection reference
import java.util.Set; // Import Set interface to hold unique room IDs cleanly

// Define the Hotel interface based exactly on the image requirements
interface Hotel { // Interface declaration for the Hotel reservation system
    boolean checkAvailability(LocalDate checkIn, LocalDate checkOut); // Method to check if any room is free for the dates
    boolean bookRoom(String roomId, LocalDate checkIn, LocalDate checkOut); // Method to book a specific room for the dates
} // End of Hotel interface

// Record to store booking details using modern Java feature for less boilerplate code
record Booking(String roomId, LocalDate checkIn, LocalDate checkOut) {} // Immutable data carrier for reservation details

// Implementation of the Hotel interface
class SimpleHotel implements Hotel { // Class declaration implementing our core Hotel interface
    private final Set<String> rooms; // A Set containing all valid room IDs in our hotel
    private final List<Booking> bookings = new ArrayList<>(); // A List containing all active reservations

    public SimpleHotel(Set<String> rooms) { // Constructor to initialize the hotel with available rooms
        this.rooms = rooms; // Assign the provided rooms set to our instance variable
    } // End of constructor

    // Helper method to check if a specific room has no overlapping bookings for given dates
    private boolean isRoomFree(String roomId, LocalDate in, LocalDate out) { // Method takes room ID and requested dates
        return bookings.stream() // Convert our bookings list into a Stream for easy filtering
                .filter(b -> b.roomId().equals(roomId)) // Keep only the existing bookings that match the requested room ID
                .noneMatch(b -> !in.isAfter(b.checkOut()) && !out.isBefore(b.checkIn())); // Check for date overlaps (dates are inclusive)
    } // End of helper method

    @Override // Override annotation indicating we are implementing the interface method
    public boolean checkAvailability(LocalDate checkIn, LocalDate checkOut) { // Implementation of checkAvailability
        return rooms.stream() // Stream through all available room IDs in the hotel
                .anyMatch(roomId -> isRoomFree(roomId, checkIn, checkOut)); // Return true immediately if ANY room is completely free
    } // End of checkAvailability method

    @Override // Override annotation indicating we are implementing the interface method
    public boolean bookRoom(String roomId, LocalDate checkIn, LocalDate checkOut) { // Implementation of bookRoom
        if (!rooms.contains(roomId) || !isRoomFree(roomId, checkIn, checkOut)) { // If room doesn't exist OR is already booked for these dates
            return false; // Return false because the booking cannot be completed
        } // End of if condition
        bookings.add(new Booking(roomId, checkIn, checkOut)); // Create a new Booking record and add it to our tracking list
        return true; // Return true indicating successful reservation
    } // End of bookRoom method
} // End of SimpleHotel class