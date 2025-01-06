package com.interview.notes.code.year.y2024.dec24.wallmart.test1;

import java.util.*;

/*
WORKING:



You are analyzing data for Aquaintly, a hot new social network.

On Aquaintly, connections are always symmetrical. If a user Alice is connected to Bob, then Bob is also connected to Alice.

You are given a sequential log of CONNECT and DISCONNECT events of the following form:
- This event connects users Alice and Bob: `["CONNECT", "Alice", "Bob"]`
- This event disconnects the same users: `["DISCONNECT", "Bob", "Alice"]` (order of users does not matter)

We want to separate users based on their popularity (number of connections). To do this, write a function that takes in the event log and a number `N` and returns two collections:
1. Users with fewer than `N` connections.
2. Users with `N` or more connections.

### Example:

```java
String[][] events = {
    {"CONNECT", "Alice", "Bob"},
    {"DISCONNECT", "Bob", "Alice"},
    {"CONNECT", "Alice", "Charlie"},
    {"CONNECT", "Dennis", "Bob"},
    {"CONNECT", "Pam", "Dennis"},
    {"DISCONNECT", "Pam", "Dennis"},
    {"CONNECT", "Pam", "Dennis"},
    {"CONNECT", "Edward", "Bob"},
    {"CONNECT", "Dennis", "Charlie"},
    {"CONNECT", "Alice", "Nicole"},
    {"CONNECT", "Pam", "Edward"},
    {"DISCONNECT", "Dennis", "Charlie"},
    {"CONNECT", "Dennis", "Edward"},
    {"CONNECT", "Charlie", "Bob"}
};
```

Using a target of `3` connections, the expected results are:
- Users with less than 3 connections: `["Alice", "Charlie", "Pam", "Nicole"]`
- Users with 3 or more connections: `["Dennis", "Bob", "Edward"]`

### Test Cases:
1. `grouping(events, 3)` => `[["Alice", "Charlie", "Pam", "Nicole"], ["Dennis", "Bob", "Edward"]]`
2. `grouping(events, 1)` => `[[], ["Alice", "Charlie", "Dennis", "Bob", "Pam", "Edward", "Nicole"]]`
3. `grouping(events, 10)` => `[["Alice", "Charlie", "Dennis", "Bob", "Pam", "Edward", "Nicole"], []]`

---

### Complexity:
- `E` = number of events.


 */
public class AquaintlyAnalyzer {

    /**
     * Processes a list of connection events and categorizes users based on the number of connections.
     *
     * @param events A 2D array where each sub-array represents an event.
     * @param N      The threshold for categorizing users.
     * @return A list containing two lists:
     * - First list: Users with fewer than N connections.
     * - Second list: Users with N or more connections.
     */
    public static List<List<String>> grouping(String[][] events, int N) {
        // Map to store each user's set of connections
        Map<String, Set<String>> userConnections = new HashMap<>();

        for (String[] event : events) {
            if (event.length != 3) {
                // Invalid event format; skip processing
                continue;
            }

            String action = event[0];
            String userA = event[1];
            String userB = event[2];

            // Initialize connection sets if users are new
            userConnections.putIfAbsent(userA, new HashSet<>());
            userConnections.putIfAbsent(userB, new HashSet<>());

            if (action.equalsIgnoreCase("CONNECT")) {
                // Avoid self-connections
                if (!userA.equals(userB)) {
                    userConnections.get(userA).add(userB);
                    userConnections.get(userB).add(userA);
                }
            } else if (action.equalsIgnoreCase("DISCONNECT")) {
                userConnections.get(userA).remove(userB);
                userConnections.get(userB).remove(userA);
            }
            // Ignore unknown actions
        }

        List<String> lessThanN = new ArrayList<>();
        List<String> NOrMore = new ArrayList<>();

        for (Map.Entry<String, Set<String>> entry : userConnections.entrySet()) {
            String user = entry.getKey();
            int connections = entry.getValue().size();
            if (connections < N) {
                lessThanN.add(user);
            } else {
                NOrMore.add(user);
            }
        }

        // Sort the lists alphabetically for consistent ordering
        Collections.sort(lessThanN);
        Collections.sort(NOrMore);

        List<List<String>> result = new ArrayList<>();
        result.add(lessThanN);
        result.add(NOrMore);

        return result;
    }

    /**
     * Compares two lists irrespective of their order.
     *
     * @param list1 First list.
     * @param list2 Second list.
     * @return True if both lists contain the same elements, false otherwise.
     */
    private static boolean compareLists(List<String> list1, List<String> list2) {
        if (list1.size() != list2.size()) return false;
        List<String> sorted1 = new ArrayList<>(list1);
        List<String> sorted2 = new ArrayList<>(list2);
        Collections.sort(sorted1);
        Collections.sort(sorted2);
        return sorted1.equals(sorted2);
    }

    /**
     * Runs predefined and custom test cases to validate the grouping method.
     */
    public static void main(String[] args) {
        // Define test cases
        List<TestCase> testCases = new ArrayList<>();

        // Test Case 1
        String[][] events1 = {
                {"CONNECT", "Alice", "Bob"},
                {"DISCONNECT", "Bob", "Alice"},
                {"CONNECT", "Alice", "Charlie"},
                {"CONNECT", "Dennis", "Bob"},
                {"CONNECT", "Pam", "Dennis"},
                {"DISCONNECT", "Pam", "Dennis"},
                {"CONNECT", "Pam", "Dennis"},
                {"CONNECT", "Edward", "Bob"},
                {"CONNECT", "Dennis", "Charlie"},
                {"CONNECT", "Alice", "Nicole"},
                {"CONNECT", "Pam", "Edward"},
                {"DISCONNECT", "Dennis", "Charlie"},
                {"CONNECT", "Dennis", "Edward"},
                {"CONNECT", "Charlie", "Bob"}
        };
        List<List<String>> expected1 = Arrays.asList(
                Arrays.asList("Alice", "Charlie", "Pam", "Nicole"),
                Arrays.asList("Dennis", "Bob", "Edward")
        );
        testCases.add(new TestCase(events1, 3, expected1, "Test Case 1"));

        // Test Case 2
        String[][] events2 = {
                {"CONNECT", "Alice", "Bob"},
                {"DISCONNECT", "Bob", "Alice"},
                {"CONNECT", "Alice", "Charlie"},
                {"CONNECT", "Dennis", "Bob"},
                {"CONNECT", "Pam", "Dennis"},
                {"DISCONNECT", "Pam", "Dennis"},
                {"CONNECT", "Pam", "Dennis"},
                {"CONNECT", "Edward", "Bob"},
                {"CONNECT", "Dennis", "Charlie"},
                {"CONNECT", "Alice", "Nicole"},
                {"CONNECT", "Pam", "Edward"},
                {"DISCONNECT", "Dennis", "Charlie"},
                {"CONNECT", "Dennis", "Edward"},
                {"CONNECT", "Charlie", "Bob"}
        };
        List<List<String>> expected2 = Arrays.asList(
                Collections.emptyList(),
                Arrays.asList("Alice", "Charlie", "Dennis", "Bob", "Pam", "Edward", "Nicole")
        );
        testCases.add(new TestCase(events2, 1, expected2, "Test Case 2"));

        // Test Case 3
        String[][] events3 = {
                {"CONNECT", "Alice", "Bob"},
                {"DISCONNECT", "Bob", "Alice"},
                {"CONNECT", "Alice", "Charlie"},
                {"CONNECT", "Dennis", "Bob"},
                {"CONNECT", "Pam", "Dennis"},
                {"DISCONNECT", "Pam", "Dennis"},
                {"CONNECT", "Pam", "Dennis"},
                {"CONNECT", "Edward", "Bob"},
                {"CONNECT", "Dennis", "Charlie"},
                {"CONNECT", "Alice", "Nicole"},
                {"CONNECT", "Pam", "Edward"},
                {"DISCONNECT", "Dennis", "Charlie"},
                {"CONNECT", "Dennis", "Edward"},
                {"CONNECT", "Charlie", "Bob"}
        };
        List<List<String>> expected3 = Arrays.asList(
                Arrays.asList("Alice", "Charlie", "Dennis", "Bob", "Pam", "Edward", "Nicole"),
                Collections.emptyList()
        );
        testCases.add(new TestCase(events3, 10, expected3, "Test Case 3"));

        // Additional Edge Test Cases

        // Test Case 4: No Events
        String[][] events4 = {};
        List<List<String>> expected4 = Arrays.asList(
                Collections.emptyList(),
                Collections.emptyList()
        );
        testCases.add(new TestCase(events4, 1, expected4, "Test Case 4 - No Events"));

        // Test Case 5: Self Connections
        String[][] events5 = {
                {"CONNECT", "Alice", "Alice"},
                {"CONNECT", "Bob", "Bob"},
                {"CONNECT", "Charlie", "Charlie"}
        };
        List<List<String>> expected5 = Arrays.asList(
                Arrays.asList("Alice", "Bob", "Charlie"),
                Collections.emptyList()
        );
        testCases.add(new TestCase(events5, 1, expected5, "Test Case 5 - Self Connections"));

        // Test Case 6: Duplicate Connections
        String[][] events6 = {
                {"CONNECT", "Alice", "Bob"},
                {"CONNECT", "Bob", "Alice"},
                {"CONNECT", "Alice", "Bob"},
                {"DISCONNECT", "Alice", "Bob"},
                {"DISCONNECT", "Bob", "Alice"},
                {"DISCONNECT", "Alice", "Bob"}
        };
        List<List<String>> expected6 = Arrays.asList(
                Arrays.asList("Alice", "Bob"),
                Collections.emptyList()
        );
        testCases.add(new TestCase(events6, 1, expected6, "Test Case 6 - Duplicate Connections"));

        // Test Case 7: Large Data Input
        String[][] events7 = generateLargeTestEvents(10000, 10000);
        // For large data, determining expected results is resource-intensive.
        // Here, we'll ensure the method runs without errors.
        List<List<String>> expected7 = new ArrayList<>(); // Not checking exact output
        testCases.add(new TestCase(events7, 5, expected7, "Test Case 7 - Large Data Input"));

        // Run all test cases
        for (TestCase testCase : testCases) {
            boolean passed = false;
            try {
                List<List<String>> result = grouping(testCase.events, testCase.N);
                if (testCase.name.equals("Test Case 7 - Large Data Input")) {
                    // For large data, just ensure no exceptions and result is as expected
                    passed = result != null;
                } else {
                    passed = compareTestResults(result, testCase.expected);
                }
            } catch (Exception e) {
                passed = false;
            }
            System.out.println(testCase.name + ": " + (passed ? "PASS" : "FAIL"));
        }
    }

    /**
     * Compares the actual and expected results for non-large test cases.
     *
     * @param actual   The actual result from the grouping method.
     * @param expected The expected result.
     * @return True if actual matches expected, false otherwise.
     */
    private static boolean compareTestResults(List<List<String>> actual, List<List<String>> expected) {
        if (actual.size() != expected.size()) return false;
        for (int i = 0; i < actual.size(); i++) {
            if (!compareLists(actual.get(i), expected.get(i))) return false;
        }
        return true;
    }

    /**
     * Generates a large set of connection events for testing scalability.
     *
     * @param numUsers  Number of unique users.
     * @param numEvents Number of events to generate.
     * @return A 2D array representing the events.
     */
    private static String[][] generateLargeTestEvents(int numUsers, int numEvents) {
        String[][] events = new String[numEvents][3];
        for (int i = 0; i < numEvents; i++) {
            String action = (i % 2 == 0) ? "CONNECT" : "DISCONNECT";
            String userA = "User" + (i % numUsers);
            String userB = "User" + ((i + 1) % numUsers);
            events[i][0] = action;
            events[i][1] = userA;
            events[i][2] = userB;
        }
        return events;
    }

    /**
     * Represents a test case with input events, threshold N, expected output, and a name.
     */
    static class TestCase {
        String[][] events;
        int N;
        List<List<String>> expected;
        String name;

        TestCase(String[][] events, int N, List<List<String>> expected, String name) {
            this.events = events;
            this.N = N;
            this.expected = expected;
            this.name = name;
        }
    }
}
