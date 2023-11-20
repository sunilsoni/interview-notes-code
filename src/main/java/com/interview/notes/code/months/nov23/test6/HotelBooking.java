package com.interview.notes.code.months.nov23.test6;

import java.util.HashMap;

public class HotelBooking {
    public static void main(String[] args) {
        // Example input
        String[] input = {"+1A", "+3E", "-1A", "+1F", "+1A", "-3E"};
        // Process the input to find the most booked room
        String mostBookedRoom = findMostBookedRoom(input);
        // Output the result
        System.out.println("The most booked room is: " + mostBookedRoom);
    }

    public static String findMostBookedRoom(String[] bookings) {
        HashMap<String, Integer> bookingCounts = new HashMap<>();
        String mostBookedRoom = "";
        int maxBookings = 0;

        for (String booking : bookings) {
            // Extract room and operation from the booking string
            String room = booking.substring(1);
            char operation = booking.charAt(0);
            
            // Update the booking count based on the operation
            bookingCounts.put(room, bookingCounts.getOrDefault(room, 0) + (operation == '+' ? 1 : -1));
            
            // Check if the current room has more bookings than the current max
            if (bookingCounts.get(room) > maxBookings) {
                mostBookedRoom = room;
                maxBookings = bookingCounts.get(room);
            } else if (bookingCounts.get(room) == maxBookings) {
                // If there is a tie, choose the lexicographically smaller room
                mostBookedRoom = mostBookedRoom.compareTo(room) > 0 ? room : mostBookedRoom;
            }
        }

        return mostBookedRoom;
    }
}
