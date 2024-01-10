package com.interview.notes.code.months.year2023.nov23.test6;

/**
 * Given a hotel which has 10 floors [0-9] and each floor has 26 rooms lA-Z). You
 * are given a sequence of rooms, where + suggests room is booked, - room is
 * freed. You have to find which room is booked maximum number of times.
 * You may assume that the list describes a correct sequence of bookings in
 * chronological order; that is, only free rooms can be booked and only booked
 * rooms can be freed. All rooms are initially free. Note that this does not mean
 * that all rooms have to be free at the end. In case 2 rooms have been booked
 * the same number of times, return the lexicographically smaller room.
 * You may assume:
 * N (length of input) is an integer within the range [1. 600] each element of array
 * A is a string consisting of three characters: *+' ora digit '0“-"9"; and uppercase
 * English letter "A" - *Z" the sequence is correct. That is every booked room was
 * previously free and every freed room was previously booked.
 * Example: Input: [’♦lA*. '+3E', ‘-lA*. "mF’, ’nA", "-3E’] Output: '1A‘ Explanation: 1A as
 * it has been booked 2 times.
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class HotelBookingTracker1 {
    public static void main(String[] args) {
        String[] input = {"+1A", "+3E", "-1A", "+1F", "-1A", "+3E"};
        String mostBooked = findMostBookedRoom(input);
        System.out.println("Most booked room: " + mostBooked);
    }

    private static String findMostBookedRoom(String[] bookings) {
        HashMap<String, Integer> roomBookings = new HashMap<>();
        HashSet<String> currentBookings = new HashSet<>();

        for (String booking : bookings) {
            String room = booking.substring(1);
            boolean isBooking = booking.startsWith("+");

            if (isBooking) {
                roomBookings.put(room, roomBookings.getOrDefault(room, 0) + 1);
                currentBookings.add(room);
            } else {
                currentBookings.remove(room);
            }
        }

        String mostBookedRoom = "";
        int maxBookings = 0;

        for (Map.Entry<String, Integer> entry : roomBookings.entrySet()) {
            String room = entry.getKey();
            int count = entry.getValue();

            if (count > maxBookings || (count == maxBookings && room.compareTo(mostBookedRoom) < 0)) {
                mostBookedRoom = room;
                maxBookings = count;
            }
        }

        return mostBookedRoom;
    }
}
