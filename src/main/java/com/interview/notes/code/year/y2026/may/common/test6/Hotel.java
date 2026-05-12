package com.interview.notes.code.year.y2026.may.common.test6;

import java.time.LocalDate; // Import LocalDate for standard date handling without time zones
import java.util.ArrayList; // Import ArrayList for dynamic lists
import java.util.HashMap; // Import HashMap for O(1) fast room lookups
import java.util.List; // Import List interface
import java.util.Map; // Import Map interface

// Interface remains exactly as requested in the requirements image
interface Hotel { // Define the contract for our reservation system
    boolean checkAvailability(LocalDate checkIn, LocalDate checkOut); // Method to see if any room is free
    boolean bookRoom(String roomId, LocalDate checkIn, LocalDate checkOut); // Method to book a specific room
} // End interface

// Java 21 Record for immutable data transfer, a modern coding standard
record Booking(LocalDate checkIn, LocalDate checkOut) {} // Holds reservation dates for a specific room instance

// Object-Oriented Room class encapsulating its own state and behaviors
class Room { // Class representing an individual hotel room entity
    private final String id; // Unique identifier for the room
    private final List<Booking> schedule = new ArrayList<>(); // Encapsulated list of reservations for this specific room

    public Room(String id) { // Constructor requires an ID upon creation
        this.id = id; // Assign the passed ID to the instance variable
    } // End constructor

    // Encapsulated logic: The room checks its own availability
    public boolean isAvailable(LocalDate in, LocalDate out) { // Method to check if passed dates overlap with existing schedule
        return schedule.stream() // Convert the room's bookings into a Stream for functional processing
                .noneMatch(b -> !in.isAfter(b.checkOut()) && !out.isBefore(b.checkIn())); // Ensure no inclusive overlap exists
    } // End availability check

    public void addBooking(LocalDate in, LocalDate out) { // Method to mutate state by adding a new booking
        schedule.add(new Booking(in, out)); // Create and store the new booking record inside this room's private list
    } // End addBooking
} // End Room class

// Main Hotel implementation utilizing OOP relationships
class StandardHotel implements Hotel { // Class implementing the required Hotel interface
    private final Map<String, Room> directory = new HashMap<>(); // Using Map for standard O(1) time complexity lookups by ID

    public StandardHotel(List<String> roomIds) { // Constructor accepts a list of room strings to build the hotel
        roomIds.forEach(id -> directory.put(id, new Room(id))); // Instantiate Room objects and populate the directory map
    } // End constructor

    @Override // Override notation for interface implementation
    public boolean checkAvailability(LocalDate checkIn, LocalDate checkOut) { // Check if the hotel has capacity overall
        return directory.values().stream() // Stream all Room objects currently existing in the hotel
                .anyMatch(room -> room.isAvailable(checkIn, checkOut)); // Return true if at least one room object reports it is free
    } // End checkAvailability

    @Override // Override notation for interface implementation
    public boolean bookRoom(String roomId, LocalDate checkIn, LocalDate checkOut) { // Attempt to finalize a booking
        Room targetRoom = directory.get(roomId); // Retrieve the specific Room object from the map in O(1) fast time
        if (targetRoom == null || !targetRoom.isAvailable(checkIn, checkOut)) { // Guard clause: fail if room doesn't exist or is busy
            return false; // Fast failure return indicating booking could not be completed
        } // End if block
        targetRoom.addBooking(checkIn, checkOut); // Delegate the booking action directly to the target Room object (OOP Encapsulation)
        return true; // Return success after the room updates its own schedule
    } // End bookRoom
} // End StandardHotel class