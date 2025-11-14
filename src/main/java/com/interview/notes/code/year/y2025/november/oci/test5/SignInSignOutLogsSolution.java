package com.interview.notes.code.year.y2025.november.oci.test5;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SignInSignOutLogsSolution {

    /**
     * This method applies the main logic described in the problem.
     *
     * @param logs    List of raw log strings: "userId timestamp action"
     * @param maxSpan Maximum allowed session length in seconds
     * @return        List of user IDs that satisfy the condition,
     *                sorted by numeric value as strings
     */
    public static List<String> processLogs(List<String> logs, int maxSpan) {

        // Map to hold sign-in times for each user ID.
        // Key  : user ID as string (e.g. "99")
        // Value: sign-in timestamp as integer
        Map<String, Integer> signInTimes = new HashMap<>();

        // Map to hold sign-out times for each user ID.
        // Key  : user ID as string (e.g. "99")
        // Value: sign-out timestamp as integer
        Map<String, Integer> signOutTimes = new HashMap<>();

        // Iterate over every log line using Java 8 forEach + lambda.
        logs.forEach(logLine -> {
            // Split the log line into components: userId, timestamp, action.
            String[] parts = logLine.split(" ");

            // The first part is the user ID as a string.
            String userId = parts[0];

            // The second part is the timestamp; parse it into an int.
            int timestamp = Integer.parseInt(parts[1]);

            // The third part is the action: "sign-in" or "sign-out".
            String action = parts[2];

            // If the action means the user signed in, store the sign-in time.
            if ("sign-in".equals(action)) {
                // Save or overwrite the sign-in time for this user.
                signInTimes.put(userId, timestamp);
            }
            // If the action means the user signed out, store the sign-out time.
            else if ("sign-out".equals(action)) {
                // Save or overwrite the sign-out time for this user.
                signOutTimes.put(userId, timestamp);
            }
            // Any other action is ignored; the problem states only two actions exist.
        });

        // Build the final list of user IDs that satisfy the rules using Stream.
        List<String> result = signInTimes.entrySet().stream()
                // Keep only users that also have a sign-out time recorded.
                .filter(entry -> signOutTimes.containsKey(entry.getKey()))
                // Keep only users whose session duration is not more than maxSpan.
                .filter(entry -> {
                    // Sign-in time for this user.
                    int signInTime = entry.getValue();
                    // Sign-out time fetched from the second map.
                    int signOutTime = signOutTimes.get(entry.getKey());
                    // Duration the user stayed logged in.
                    int duration = signOutTime - signInTime;
                    // We include this user only if duration <= maxSpan.
                    return duration <= maxSpan;
                })
                // From each Map.Entry we now only need the user ID string.
                .map(Map.Entry::getKey)
                // Sort user IDs using numeric order instead of plain string order.
                .sorted(Comparator.comparingLong(Long::parseLong))
                // Collect the sorted IDs into a List<String> to return.
                .collect(Collectors.toList());

        // Return the final list of qualifying user IDs.
        return result;
    }

    /**
     * Helper method to print PASS / FAIL for a test case with expected and actual lists.
     */
    private static void printTestResult(String testName, List<String> expected, List<String> actual) {
        // Check if expected list and actual list are exactly equal.
        boolean pass = expected.equals(actual);
        // Print the test name, status, expected value, and actual value.
        System.out.println(
                testName + ": " + (pass ? "PASS" : "FAIL")
                        + " | expected=" + expected
                        + " | actual=" + actual
        );
    }

    /**
     * Main method to run several test cases, including large data.
     */
    public static void main(String[] args) {

        // ---------- Sample Case 0 from the problem statement ----------
        // Number of log lines is 6; build the list directly.
        List<String> logsSample0 = Arrays.asList(
                "99 1 sign-in",
                "100 10 sign-in",
                "50 20 sign-in",
                "100 15 sign-out",
                "50 26 sign-out",
                "99 2 sign-out"
        );
        // Maximum allowed span in this sample is 5 seconds.
        int maxSpanSample0 = 5;
        // Expected users are 99 and 100 in numeric order.
        List<String> expectedSample0 = Arrays.asList("99", "100");
        // Call our logic method to get the result.
        List<String> actualSample0 = processLogs(logsSample0, maxSpanSample0);
        // Print PASS/FAIL for Sample Case 0.
        printTestResult("Sample Case 0", expectedSample0, actualSample0);

        // ---------- Sample Case 1 from the problem statement ----------
        // Logs for the second sample.
        List<String> logsSample1 = Arrays.asList(
                "60 12 sign-in",
                "80 20 sign-out",
                "10 20 sign-in",
                "60 20 sign-out"
        );
        // Maximum allowed span is 100 seconds here.
        int maxSpanSample1 = 100;
        // Only user 60 qualifies.
        List<String> expectedSample1 = List.of("60");
        // Compute actual result using our method.
        List<String> actualSample1 = processLogs(logsSample1, maxSpanSample1);
        // Print PASS/FAIL for Sample Case 1.
        printTestResult("Sample Case 1", expectedSample1, actualSample1);

        // ---------- Example from the main description ----------
        // Example with n = 7 logs described in the problem statement.
        List<String> logsExample = Arrays.asList(
                "30 99 sign-in",
                "30 105 sign-out",
                "12 100 sign-in",
                "20 80 sign-in",
                "12 120 sign-out",
                "20 101 sign-out",
                "21 110 sign-in"
        );
        // Maximum allowed span in the example is 20 seconds.
        int maxSpanExample = 20;
        // Users 12 and 30 qualify; they must be in numeric order.
        List<String> expectedExample = Arrays.asList("12", "30");
        // Compute the example result.
        List<String> actualExample = processLogs(logsExample, maxSpanExample);
        // Print PASS/FAIL for the example.
        printTestResult("Example Case", expectedExample, actualExample);

        // ---------- Edge Case: user with no sign-out ----------
        // Here user 1 never signs out, and user 2 has a valid short session.
        List<String> logsEdge = Arrays.asList(
                "1 10 sign-in",
                "2 20 sign-in",
                "2 22 sign-out"
        );
        // Allow a span of 5 seconds.
        int maxSpanEdge = 5;
        // Only user 2 should appear.
        List<String> expectedEdge = List.of("2");
        // Compute and print result for the edge case.
        List<String> actualEdge = processLogs(logsEdge, maxSpanEdge);
        printTestResult("Edge Case - Missing Sign-out", expectedEdge, actualEdge);

        // ---------- Large Data Test: performance and correctness ----------
        // Decide how many users we want for a big test.
        int largeUserCount = 100000; // one hundred thousand users

        // Prepare a list with capacity for sign-in and sign-out logs.
        List<String> largeLogs = new ArrayList<>(largeUserCount * 2);

        // For each user ID from 1 to largeUserCount, create a short valid session.
        IntStream.rangeClosed(1, largeUserCount).forEach(id -> {
            // Make a simple sign-in log line at time = id.
            largeLogs.add(id + " " + id + " sign-in");
            // Make a sign-out log line at time = id + 5 (duration 5 seconds).
            largeLogs.add(id + " " + (id + 5) + " sign-out");
        });

        // Set maxSpan so that all these sessions are valid.
        int maxSpanLarge = 10;

        // Build the expected list of all user IDs as strings from 1..largeUserCount.
        List<String> expectedLarge = IntStream.rangeClosed(1, largeUserCount)
                .mapToObj(String::valueOf)
                .collect(Collectors.toList());

        // Call the main logic on the large data set.
        List<String> actualLarge = processLogs(largeLogs, maxSpanLarge);

        // Check size and boundary elements to avoid printing huge lists.
        boolean sizeMatches = actualLarge.size() == expectedLarge.size();
        boolean firstMatches = !actualLarge.isEmpty()
                && actualLarge.get(0).equals(expectedLarge.get(0));
        boolean lastMatches = !actualLarge.isEmpty()
                && actualLarge.get(actualLarge.size() - 1)
                   .equals(expectedLarge.get(expectedLarge.size() - 1));

        // We treat the large test as PASS only if all quick checks are true.
        boolean largePass = sizeMatches && firstMatches && lastMatches;

        // Print the high-level result for the large data test.
        System.out.println("Large Data Test: " + (largePass ? "PASS" : "FAIL")
                + " | users=" + largeUserCount);
    }
}
