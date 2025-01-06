package com.interview.notes.code.year.y2024.dec24.test14;

import java.util.*;
import java.util.stream.Collectors;

public class RequestProcessorStreams {

    public static Map<String, List<String>> consolidateRequests(List<String> requests) {
        if (requests == null || requests.isEmpty()) {
            return new LinkedHashMap<>();
        }

        return requests.stream()
                .filter(req -> req != null && req.contains(":"))
                .map(req -> req.split(":"))
                .collect(Collectors.groupingBy(
                        parts -> parts[0],
                        LinkedHashMap::new,
                        Collectors.mapping(
                                parts -> parts[1],
                                Collectors.toList()
                        )
                ));
    }

    public static void main(String[] args) {
        runAllTests();
    }

    private static void runAllTests() {
        // Test Case 1: Normal case
        List<String> test1 = Arrays.asList(
                "client1:BNG1234",
                "client2:HYD3456",
                "client1:BNG1567",
                "client2:HYD9876",
                "client3:DEL3456"
        );
        runTest("Test 1 - Normal Case", test1);

        // Test Case 2: Empty input
        runTest("Test 2 - Empty List", new ArrayList<>());

        // Test Case 3: Null input
        runTest("Test 3 - Null Input", null);

        // Test Case 4: Invalid format
        List<String> test4 = Arrays.asList(
                "client1:BNG1234",
                null,
                "invalid_entry",
                "client2:HYD3456",
                "client1:BNG1567"
        );
        runTest("Test 4 - Invalid Format", test4);

        // Test Case 5: Large dataset
        List<String> largeInput = generateLargeDataset(100_000);
        runTest("Test 5 - Large Dataset (100K entries)", largeInput);
    }

    private static void runTest(String testName, List<String> input) {
        System.out.println("\n" + testName + ":");
        System.out.println("Input Sample: " +
                (input != null ?
                        input.stream()
                                .limit(3)
                                .collect(Collectors.toList()) +
                                (input.size() > 3 ? "... (" + input.size() + " total items)" : "")
                        : "null"));

        long startTime = System.nanoTime();
        Map<String, List<String>> result = consolidateRequests(input);
        long endTime = System.nanoTime();
        double timeInMs = (endTime - startTime) / 1_000_000.0;

        System.out.println("Output: " + formatOutput(result));
        System.out.println("Execution Time: " + String.format("%.2f ms", timeInMs));

        boolean passed = validateResult(input, result);
        System.out.println("Test Result: " + (passed ? "PASS ✓" : "FAIL ✗"));
    }

    private static String formatOutput(Map<String, List<String>> result) {
        if (result.size() <= 3) {
            return result.toString();
        }
        return result.entrySet().stream()
                .limit(3)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new))
                .toString()
                + "... (" + result.size() + " total clients)";
    }

    private static boolean validateResult(List<String> input, Map<String, List<String>> result) {
        if (input == null || input.isEmpty()) {
            return result.isEmpty();
        }

        Map<String, Long> expectedCounts = input.stream()
                .filter(req -> req != null && req.contains(":"))
                .map(req -> req.split(":"))
                .collect(Collectors.groupingBy(
                        parts -> parts[0],
                        Collectors.counting()
                ));

        return result.entrySet().stream()
                .allMatch(entry ->
                        expectedCounts.containsKey(entry.getKey()) &&
                                expectedCounts.get(entry.getKey()) == entry.getValue().size()
                );
    }

    private static List<String> generateLargeDataset(int size) {
        return new Random().ints(0, 100)
                .limit(size)
                .mapToObj(i -> "client" + (i % 20) + ":REQ" + i)
                .collect(Collectors.toList());
    }
}
