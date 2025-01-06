package com.interview.notes.code.year.y2024.dec24.test14;

import java.util.*;

public class RequestProcessor {

    // Main method to process requests and create map
    public static Map<String, List<String>> consolidateRequests(List<String> requests) {
        // Using LinkedHashMap to maintain insertion order
        Map<String, List<String>> clientRequests = new LinkedHashMap<>();

        if (requests == null || requests.isEmpty()) {
            return clientRequests;
        }

        for (String request : requests) {
            // Skip invalid entries
            if (request == null || !request.contains(":")) {
                continue;
            }

            // Split the request string
            String[] parts = request.split(":");
            String clientId = parts[0].trim();
            String requestId = parts[1].trim();

            // Get or create list for client
            clientRequests.computeIfAbsent(clientId, k -> new ArrayList<>())
                    .add(requestId);
        }

        return clientRequests;
    }

    // Test method
    public static void main(String[] args) {
        // Test cases
        runTest("Test 1 - Normal Case", Arrays.asList(
                "client1:BNG1234",
                "client2:HYD3456",
                "client1:BNG1567",
                "client2:HYD9876",
                "client3:DEL3456"
        ));

        runTest("Test 2 - Empty List", new ArrayList<>());

        runTest("Test 3 - Null Input", null);

        runTest("Test 4 - Invalid Format", Arrays.asList(
                "client1:BNG1234",
                "invalid_entry",
                "client2:HYD3456"
        ));

        // Large data test
        List<String> largeInput = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            largeInput.add("client" + (i % 100) + ":REQ" + i);
        }
        runTest("Test 5 - Large Data", largeInput);
    }

    private static void runTest(String testName, List<String> input) {
        System.out.println("\n" + testName);
        System.out.println("Input: " + (input != null ? input.subList(0, Math.min(5, input.size())) : "null")
                + (input != null && input.size() > 5 ? "... (" + input.size() + " items)" : ""));

        long startTime = System.currentTimeMillis();
        Map<String, List<String>> result = consolidateRequests(input);
        long endTime = System.currentTimeMillis();

        System.out.println("Output: " + result);
        System.out.println("Time taken: " + (endTime - startTime) + "ms");

        // Validate result
        boolean passed = validateResult(input, result);
        System.out.println("Test Result: " + (passed ? "PASS" : "FAIL"));
    }

    private static boolean validateResult(List<String> input, Map<String, List<String>> result) {
        if (input == null || input.isEmpty()) {
            return result.isEmpty();
        }

        // Count requests per client in input
        Map<String, Integer> inputCounts = new HashMap<>();
        for (String req : input) {
            if (req != null && req.contains(":")) {
                String clientId = req.split(":")[0];
                inputCounts.merge(clientId, 1, Integer::sum);
            }
        }

        // Compare with result
        for (Map.Entry<String, List<String>> entry : result.entrySet()) {
            String clientId = entry.getKey();
            List<String> requests = entry.getValue();

            if (!inputCounts.containsKey(clientId) ||
                    inputCounts.get(clientId) != requests.size()) {
                return false;
            }
        }

        return true;
    }
}
