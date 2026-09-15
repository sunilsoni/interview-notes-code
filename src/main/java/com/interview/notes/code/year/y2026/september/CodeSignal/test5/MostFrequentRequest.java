package com.interview.notes.code.year.y2026.september.CodeSignal.test5;

import java.util.*; // Provides List, Map, Arrays and other collection classes.
import java.util.function.Function; // Provides Function.identity() used while grouping values.
import java.util.stream.Collectors; // Provides groupingBy and counting Stream operations.

public class MostFrequentRequest { // Defines the main class for this problem.

    static String mostFrequent(List<String> requests) { // Finds the most frequently requested access type.

        if (requests == null || requests.isEmpty()) return null; // Handles null or empty input safely.

        Map<String, Long> counts = requests.stream() // Creates a stream from all access requests.
                .collect(Collectors.groupingBy( // Groups identical requests together.
                        Function.identity(), // Uses the request itself as the grouping key.
                        LinkedHashMap::new, // Keeps the original request insertion order for tie cases.
                        Collectors.counting())); // Counts how many times each request occurs.

        return counts.entrySet().stream() // Creates a stream of request and count entries.
                .max(Map.Entry.comparingByValue()) // Finds the entry having the highest frequency.
                .map(Map.Entry::getKey) // Extracts only the request name from that entry.
                .orElse(null); // Returns null if no request exists.
    }

    static void test(String name, List<String> input, String expected) { // Reusable method for testing.
        String actual = mostFrequent(input); // Executes the solution for the given input.
        System.out.println(name + ": " + // Prints the test case name.
                (Objects.equals(actual, expected) ? "PASS" : "FAIL") + // Checks whether result is correct.
                " | Expected=" + expected + // Prints expected result.
                " | Actual=" + actual); // Prints actual result.
    }

    static void main(String[] args) { // Program starts execution from this method.

        test("Test 1", // Tests a normal list containing repeated requests.
                Arrays.asList("READ", "WRITE", "READ", "DELETE", "READ", "WRITE"), // Creates sample requests.
                "READ"); // READ occurs three times.

        test("Test 2", // Tests when one request clearly occurs most often.
                Arrays.asList("ADMIN", "USER", "ADMIN"), // ADMIN occurs twice.
                "ADMIN"); // Expected most frequent request.

        test("Test 3", // Tests a list containing only one request.
                Collections.singletonList("READ"), // Creates a single-element list.
                "READ"); // The only request must be returned.

        test("Test 4", // Tests an empty input list.
                Collections.emptyList(), // Creates an empty list.
                null); // Empty input should return null.

        test("Test 5", // Tests two requests with the same frequency.
                Arrays.asList("READ", "WRITE", "READ", "WRITE"), // Both occur twice.
                "READ"); // First appearing request wins the tie.

        List<String> large = new ArrayList<>(); // Creates a list for a large-data test.

        for (int i = 0; i < 100000; i++) large.add("READ"); // Adds READ one hundred thousand times.

        for (int i = 0; i < 99999; i++) large.add("WRITE"); // Adds WRITE slightly fewer times.

        test("Large Test", large, "READ"); // Verifies the solution with a large input.
    }
}