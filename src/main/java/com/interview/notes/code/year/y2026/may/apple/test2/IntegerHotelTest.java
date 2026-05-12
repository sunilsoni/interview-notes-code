package com.interview.notes.code.year.y2026.may.apple.test2;

import java.time.LocalDate; // Import standard date class

public class IntegerHotelTest { // Main class strictly for testing purposes
    public static void main(String[] args) { // Main execution thread
        
        Hotel hotel = new StandardHotel(10); // Create a hotel with 10 rooms (IDs 1 through 10)

        LocalDate today = LocalDate.now(); // Baseline date for testing logic
        LocalDate tomorrow = today.plusDays(1); // One day span check
        LocalDate nextWeek = today.plusDays(7); // Later date for overlap testing

        // Test 1: Try booking room 1
        boolean t1 = hotel.bookRoom(1, today, tomorrow); // UPDATED: Pass integer 1 instead of string
        System.out.println("Test 1 (Book Room 1): " + (t1 ? "PASS" : "FAIL")); // Expect PASS

        // Test 2: Try booking room 10
        boolean t2 = hotel.bookRoom(10, today, tomorrow); // UPDATED: Pass integer 10
        System.out.println("Test 2 (Book Room 10): " + (t2 ? "PASS" : "FAIL")); // Expect PASS

        // Test 3: Try booking a room outside the initialized range (Room 11)
        boolean t3 = !hotel.bookRoom(11, today, tomorrow); // UPDATED: Pass integer 11 (should fail and return false)
        System.out.println("Test 3 (Invalid Room 11 Rejected): " + (t3 ? "PASS" : "FAIL")); // Expect PASS

        // Test 4: Inclusive date boundary failure on Room 1
        boolean t4 = !hotel.bookRoom(1, tomorrow, nextWeek); // Try booking room 1 again starting tomorrow (overlaps checkout)
        System.out.println("Test 4 (Inclusive Overlap Rejected): " + (t4 ? "PASS" : "FAIL")); // Expect PASS (It should return false)

        // Test 5: Large Scale Test (e.g., 10,000 rooms)
        Hotel megaHotel = new StandardHotel(10000); // Instantly create a 10,000 room hotel
        boolean t5 = megaHotel.bookRoom(9999, today, tomorrow); // UPDATED: Book room ID 9999 as an integer
        System.out.println("Test 5 (Large Scale Initialization): " + (t5 ? "PASS" : "FAIL")); // Expect PASS
    } // End main
} // End class