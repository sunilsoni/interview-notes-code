package com.interview.notes.code.months.Aug23.test6;

import java.util.*;

/**
 * Given a hotel which has 10 floors lo-g] and each floor has 26 rooms [A-Z] You are
 * given a sequence of rooms where * suggests room is booked - room is freed You
 * have to find which room is booked maximum number of times
 * You may assume that the list describes a correct sequence of bookings in
 * chronological order: that is. only free rooms can be booked and only booked rooms
 * can be freed All rooms are initially free Note that this does not mean that all rooms
 * have to be free at the end In case 2 rooms have been booked the same number of
 * times return the Lexicographically smaller room
 * You may assume:
 * N (Length of input) is an integer within the range 11.600] each element of array A is a
 * string consisting of three characters or a digit ’O'-’g’ and uppercase English
 * Letter 'A' - ’Z’ the sequence is correct That is every booked room was previously free
 * and every freed room was previously booked
 * Example: Input I'+1A* ’*3E'. *-iA’ **4F* ’♦lA’. ‘-3E’] Output '1A* Explanation: 1A as it has
 * been booked 2 times
 */

public class HotelBooking {

    public static String maxBookedRoom(List<String> sequence) {
        Map<String, Integer> bookings = new HashMap<>();

        for (String entry : sequence) {
            if (!entry.startsWith("*")) {  // If the room is being booked
                bookings.put(entry, bookings.getOrDefault(entry, 0) + 1);
            }
        }

        // Identify the max bookings
        int maxBookings = Collections.max(bookings.values());

        // Find all rooms with max bookings
        List<String> maxBookedRooms = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : bookings.entrySet()) {
            if (entry.getValue() == maxBookings) {
                maxBookedRooms.add(entry.getKey());
            }
        }

        // Sort to get lexicographically smallest room
        Collections.sort(maxBookedRooms);
        return maxBookedRooms.get(0);
    }

    public static void main(String[] args) {
        List<String> sequence = Arrays.asList("+1A", "*3E", "-1A", "*4F", "+1A", "-3E");
        System.out.println(maxBookedRoom(sequence));  // Output: '1A'


        //Bard

        String[] sequence1 = new String[]{"+1A*", "*3E", "-1A", "4F*", "+1A*", "-3E"};

        String mostBookedRoom = mostBookedRoom(sequence1);

        System.out.println(mostBookedRoom); // Output: 1A*
    }


    public static String mostBookedRoom(String[] sequence) {
        // Create a map to store the number of times each room has been booked.
        Map<String, Integer> roomCounts = new HashMap<>();

        // Iterate over the sequence of rooms.
        for (String room : sequence) {
            // If the room is being booked, increment its count.
            if (room.startsWith("+")) {
                roomCounts.put(room.substring(1), roomCounts.getOrDefault(room.substring(1), 0) + 1);
            } else {
                // If the room is being freed, decrement its count.
                roomCounts.put(room.substring(1), roomCounts.getOrDefault(room.substring(1), 0) - 1);
            }
        }

        // Find the room with the highest count.
        String mostBookedRoom = "";
        int highestCount = 0;

        for (String room : roomCounts.keySet()) {
            int count = roomCounts.get(room);
            if (count > highestCount || (count == highestCount && room.compareTo(mostBookedRoom) < 0)) {
                mostBookedRoom = room;
                highestCount = count;
            }
        }

        return mostBookedRoom;
    }
}
