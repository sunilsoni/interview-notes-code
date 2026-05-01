package com.interview.notes.code.year.y2026.april.common.test3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream; // Import for index-based loops

public class GiftTracker { // Main class for the gift exchange system

    public static List<Assignment> createExchange(List<Guest> partyGuests) { // Method to generate assignments
        if (partyGuests == null || partyGuests.size() < 2) return List.of(); // Safety check for small parties

        var shuffled = new ArrayList<>(partyGuests); // Copy the list so we don't mess up the original arrival order
        Collections.shuffle(shuffled); // Scramble the guests to make the pairings random

        int size = shuffled.size(); // Get the total count of people at the party

        return IntStream.range(0, size) // Create a stream of numbers from 0 to size-1
            .mapToObj(i -> new Assignment( // For every index, create a new Assignment record
                shuffled.get(i), // The Giver: The person at the current index
                shuffled.get((i + 1) % size) // The Receiver: The next person in line (looping back to 0 at the end)
            ))
            .toList(); // Collect all assignments into a final list
    }

    public static void main(String[] args) { // Main method for testing
        // Creating a list of guests with their specific presents
        List<Guest> guests = List.of(
            new Guest("Brian", "a Tesla Model S Toy"),
            new Guest("Leona", "a Bottle of Wine"),
            new Guest("Sarah", "a Gaming Mouse"),
            new Guest("Mike", "a Box of Chocolates")
        );

        runTest("Standard Party", guests); // Run a test with our 4 friends

        // Large data test case
        var largeParty = IntStream.range(0, 10000) // Create 10,000 guests
            .mapToObj(i -> new Guest("Guest" + i, "Gift" + i)) // Each brings a numbered gift
            .toList(); // Convert to list
        runTest("Large Party (10k)", largeParty); // Ensure it handles heavy loads
    }

    private static void runTest(String label, List<Guest> input) { // Helper method to print and verify results
        var results = createExchange(input); // Execute the shuffle and assignment logic

        System.out.println("--- " + label + " ---"); // Print the test category

        if (input.size() < 10) { // Only print full details for small lists so the console stays clean
            results.forEach(System.out::println); // Print each assignment (Giver -> Gift -> Receiver)
        }

        // Final verification check
        boolean hasSelfAssignment = results.stream() // Check every result
            .anyMatch(a -> a.giver().name().equals(a.receiver().name())); // Ensure no one gave to themselves

        if (!hasSelfAssignment && results.size() == input.size()) { // Check if the logic was successful
            System.out.println("STATUS: PASS (No self-gifting, all " + results.size() + " guests assigned)\n");
        } else {
            System.out.println("STATUS: FAIL\n"); // Print failure if logic broke
        }
    }

    // Using a Java 21 'record' to neatly store the person and their specific gift
    public record Guest(String name, String gift) {}

    // A record to represent the final result: who gives to whom and what was given
    public record Assignment(Guest giver, Guest receiver) {
        @Override
        public String toString() { // Custom string to make the output easy to read
            return giver.name() + " (who brought " + giver.gift() + ") is giving it to " + receiver.name();
        }
    }
}