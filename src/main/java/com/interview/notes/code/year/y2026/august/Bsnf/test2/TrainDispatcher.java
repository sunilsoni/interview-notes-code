package com.interview.notes.code.year.y2026.august.Bsnf.test2;

import java.util.*;
import java.util.function.Function; // Used to group identical strings in the stream
import java.util.stream.Collectors; // Used to count the grouped strings

public class TrainDispatcher { // Main class

    public static List<String> generateSchedule(String[] trains, int n) { // Modifying to return the actual schedule

        // 1. We keep your original logic to find the frequencies
        var counts = Arrays.stream(trains).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        // 2. As you noted, we MUST keep available trains sorted by frequency (descending)
        var available = new PriorityQueue<Train>((a, b) -> b.remaining() - a.remaining());

        // Loads our frequency map data into the sorted queue, ready at time 0
        counts.forEach((name, count) -> available.add(new Train(name, count.intValue(), 0)));

        var cooldown = new LinkedList<Train>(); // Queue to hold trains currently serving their 'n' mandatory idle slots
        var schedule = new ArrayList<String>(); // 3. The storage you mentioned to build and return the actual schedule
        int time = 0; // Tracks current slot to manage cooldowns

        // 4. We loop through until every single train is dispatched and the cooldown is empty
        while (!available.isEmpty() || !cooldown.isEmpty()) {

            // If the train at the front of the cooldown line is unlocked at this current time
            if (!cooldown.isEmpty() && cooldown.peek().unlock() <= time) {
                available.add(cooldown.poll()); // Move it back to the available sorted queue
            }

            if (!available.isEmpty()) { // If we have a valid train to dispatch
                var current = available.poll(); // Grab the most frequent available train
                schedule.add(current.name()); // Add its name to our final schedule

                if (current.remaining() > 1) { // If there are still more of this train line to dispatch
                    // Put it in cooldown: subtract 1 from count, and set unlock time to current time + gap + 1
                    cooldown.add(new Train(current.name(), current.remaining() - 1, time + n + 1));
                }
            } else { // If no trains are available (they are all stuck in cooldown)
                schedule.add("idle"); // We record an idle slot in the schedule
            }

            time++; // Move the clock forward to the next slot
        }

        return schedule; // Return the fully constructed schedule array
    } // Closes the method

    public static void main(String[] args) { // Simple main method for testing, avoiding JUnit
        test(new String[]{"Red", "Red", "Red"}, 2, 7, "Example 1"); // Tests Ex 1 constraints
        test(new String[]{"Red", "Red", "Red", "Blue", "Blue", "Blue"}, 2, 8, "Example 2"); // Tests Ex 2 constraints
        test(new String[]{"Red", "Blue", "Green", "Red", "Blue", "Green"}, 2, 6, "Example 3"); // Tests Ex 3 constraints
    } // Closes main method

    private static void test(String[] trains, int n, int expSize, String name) { // Helper to validate and print
        var schedule = generateSchedule(trains, n); // Runs the modified algorithm
        // Verifies the size matches our mathematical expectation, and prints the exact generated schedule
        System.out.println(name + (schedule.size() == expSize ? " -> PASS" : " -> FAIL") + " | Generated: " + schedule);
    } // Closes test helper

    // Java 21 feature: 'record' cleanly stores a train's name, remaining count, and when it can leave cooldown
    record Train(String name, int remaining, int unlock) {}
} // Closes main class