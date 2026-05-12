package com.interview.notes.code.year.y2026.may.apple.test3;

import java.time.LocalDate; // Import standard date class
import java.util.ArrayList; // Import ArrayList for our underlying list
import java.util.List; // Import List interface
import java.util.Map; // Import Map interface
import java.util.concurrent.ConcurrentHashMap; // NEW: Thread-safe map for web server environments

interface Hotel { // Define the contract for our reservation system
    boolean checkAvailability(LocalDate checkIn, LocalDate checkOut); // Method to see if any room is free
    boolean bookRoom(int roomId, LocalDate checkIn, LocalDate checkOut); // Method to book a specific room
} // End interface

record Booking(LocalDate checkIn, LocalDate checkOut) {} // Immutable record remains completely thread-safe by default

class Room { // Class representing an individual hotel room entity
    private final int id; // Unique identifier for the room
    private final List<Booking> schedule = new ArrayList<>(); // Underlying list (we will protect this with locks)

    public Room(int id) { // Constructor requires an integer ID
        this.id = id; // Assign ID
    } // End constructor

    // Helper method: Kept private because we only want to call it when safely locked
    private boolean isAvailable(LocalDate in, LocalDate out) { // Method to check overlapping dates
        return schedule.stream() // Convert schedule to stream
                .noneMatch(b -> !in.isAfter(b.checkOut()) && !out.isBefore(b.checkIn())); // Check for overlap
    } // End isAvailable

    // NEW: We combined the "Check" and the "Act" into a single, synchronized method.
    // The 'synchronized' keyword ensures only ONE thread can enter this method for this specific room at a time.
    public synchronized boolean tryBook(LocalDate in, LocalDate out) { // Thread-safe booking attempt
        if (!isAvailable(in, out)) { // 1. Check Availability (Safely locked)
            return false; // Return false if already booked
        } // End if
        schedule.add(new Booking(in, out)); // 2. Act: Add the booking (Safely locked, no other thread can interrupt)
        return true; // Return success
    } // End tryBook

    // NEW: We also must synchronize the read operation to prevent ConcurrentModificationException
    // while another thread is inside tryBook() modifying the list.
    public synchronized boolean isFree(LocalDate in, LocalDate out) { // Thread-safe read operation
        return isAvailable(in, out); // Call the internal availability check safely
    } // End isFree
} // End Room class

class StandardHotel implements Hotel { // Class implementing the required Hotel interface
    // NEW: Using ConcurrentHashMap. Even if we dynamically add/remove rooms later, 
    // this map handles simultaneous reads/writes from thousands of web requests safely.
    private final Map<Integer, Room> directory = new ConcurrentHashMap<>(); // Thread-safe map initialization

    public StandardHotel(int numberOfRooms) { // Constructor
        for (int i = 1; i <= numberOfRooms; i++) { // Loop to generate rooms
            directory.put(i, new Room(i)); // Safely put rooms into the concurrent map
        } // End loop
    } // End constructor

    @Override // Override notation
    public boolean checkAvailability(LocalDate checkIn, LocalDate checkOut) { // Check overall capacity
        return directory.values().stream() // Stream the concurrent map values
                .anyMatch(room -> room.isFree(checkIn, checkOut)); // Call the synchronized isFree method on each room
    } // End checkAvailability

    @Override // Override notation
    public boolean bookRoom(int roomId, LocalDate checkIn, LocalDate checkOut) { // Attempt booking
        Room targetRoom = directory.get(roomId); // ConcurrentHashMap allows safe, instant lookups
        if (targetRoom == null) { // Guard clause: fail if room doesn't exist
            return false; // Return failure
        } // End if
        
        // Notice we delegate the ENTIRE check-and-book process to the Room's synchronized method.
        // This completely eliminates the Check-Then-Act race condition.
        return targetRoom.tryBook(checkIn, checkOut); // Return the result of the atomic booking attempt
    } // End bookRoom
} // End StandardHotel class