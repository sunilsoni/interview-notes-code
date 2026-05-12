package com.interview.notes.code.year.y2026.may.apple.test4;

import java.time.LocalDate; // Import standard date class
import java.util.ArrayList; // Import ArrayList for dynamic lists
import java.util.List; // Import List interface
import java.util.Map; // Import Map interface
import java.util.concurrent.ConcurrentHashMap; // MINIMAL CHANGE 1: Use ConcurrentHashMap for thread safety

// Interface updated to use 'int' for roomId
interface Hotel { // Define the contract for our reservation system
    boolean checkAvailability(LocalDate checkIn, LocalDate checkOut); // Method to see if any room is free
    boolean bookRoom(int roomId, LocalDate checkIn, LocalDate checkOut); // UPDATED: Method to book a specific room using an integer ID
} // End interface

// Immutable record for bookings (Inherently thread-safe)
record Booking(LocalDate checkIn, LocalDate checkOut) {} // Holds reservation dates for a specific room instance

// Room class encapsulating its own state and behaviors
class Room { // Class representing an individual hotel room entity
    private final int id; // UPDATED: Unique identifier for the room is now an integer
    private final List<Booking> schedule = new ArrayList<>(); // Encapsulated list of reservations for this specific room

    public Room(int id) { // UPDATED: Constructor requires an integer ID
        this.id = id; // Assign the passed ID to the instance variable
    } // End constructor

    // MINIMAL CHANGE 2: Added 'synchronized' to prevent ConcurrentModificationException during reads
    public synchronized boolean isAvailable(LocalDate in, LocalDate out) { // Method to check if passed dates overlap with existing schedule
        return schedule.stream() // Convert the room's bookings into a Stream for functional processing
                .noneMatch(b -> !in.isAfter(b.checkOut()) && !out.isBefore(b.checkIn())); // Ensure no inclusive overlap exists
    } // End availability check

    // MINIMAL CHANGE 3: Added 'synchronized' to safely add to the ArrayList
    public synchronized void addBooking(LocalDate in, LocalDate out) { // Method to mutate state by adding a new booking
        schedule.add(new Booking(in, out)); // Create and store the new booking record inside this room's private list
    } // End addBooking
} // End Room class

// Main Hotel implementation
class StandardHotel implements Hotel { // Class implementing the required Hotel interface
    // MINIMAL CHANGE 1: Instantiating ConcurrentHashMap instead of standard HashMap
    private final Map<Integer, Room> directory = new ConcurrentHashMap<>(); // Map now uses Integer keys for O(1) lookups

    public StandardHotel(int numberOfRooms) { // Constructor accepts the total number of rooms to create
        for (int i = 1; i <= numberOfRooms; i++) { // Loop starting at 1 up to the requested number of rooms
            directory.put(i, new Room(i)); // Create the Room object with an integer ID and store it in our map directory
        } // End loop
    } // End constructor

    @Override // Override notation for interface implementation
    public boolean checkAvailability(LocalDate checkIn, LocalDate checkOut) { // Check if the hotel has capacity overall
        return directory.values().stream() // Stream all Room objects currently existing in the hotel
                .anyMatch(room -> room.isAvailable(checkIn, checkOut)); // Return true if at least one room object reports it is free
    } // End checkAvailability

    @Override // Override notation for interface implementation
    public boolean bookRoom(int roomId, LocalDate checkIn, LocalDate checkOut) { // Takes integer roomId
        Room targetRoom = directory.get(roomId); // Retrieve the specific Room object using the integer key
        if (targetRoom == null) { // Guard clause: fail if room doesn't exist
            return false; // Fast failure return indicating booking could not be completed
        } // End if block
        
        // MINIMAL CHANGE 4: Synchronize on the target room to fix the "Check-Then-Act" race condition
        synchronized (targetRoom) { 
            if (!targetRoom.isAvailable(checkIn, checkOut)) { // Now thread-safe: check availability while locked
                return false; 
            }
            targetRoom.addBooking(checkIn, checkOut); // Now thread-safe: delegate booking directly to the target Room object
            return true; // Return success after the room updates its own schedule
        } // End synchronized block
    } // End bookRoom
} // End StandardHotel class