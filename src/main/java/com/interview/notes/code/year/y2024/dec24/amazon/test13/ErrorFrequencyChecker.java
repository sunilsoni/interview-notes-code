package com.interview.notes.code.year.y2024.dec24.amazon.test13;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
You have an application which sends messages, and it has a log file which has the following sample format:
2021-10-23T09:35:29Z sent message

2021-10-23T09:44:01Z transmit error

2021-10-23T09:49:29Z sent message

2021-10-23T10:01:49Z sent message

2021-10-23T10:05:29Z transmit error

2021-10-23T10:06:05Z socket error

2021-10-23T10:07:17Z transmit error

2021-10-23T11:23:24Z sent message

2021-10-23T11:52:28Z sent message

2021-10-23T12:01:13Z connect error

2021-10-23T12:02:13Z connect error

2021-10-23T12:03:13Z transmit error

Given the l0g file, return the top T most frequent error types.

In above example, if T = 2

Result: [transmit error, connect error]

• List‹String› getMostFrequentErrors(File file, int T) {

 */
public class ErrorFrequencyChecker {

    public static List<String> getMostFrequentErrors(File file, int T) {
        Map<String, Integer> frequencyMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("error")) {
                    // Extract error phrase (last two words, e.g. "transmit error")
                    String[] parts = line.split("\\s+");
                    String error = parts[parts.length - 2] + " " + parts[parts.length - 1];
                    frequencyMap.put(error, frequencyMap.getOrDefault(error, 0) + 1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Sort errors by frequency
        List<Map.Entry<String, Integer>> list = new ArrayList<>(frequencyMap.entrySet());
        list.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        // Collect top T
        List<String> result = new ArrayList<>();
        for (int i = 0; i < list.size() && i < T; i++) {
            result.add(list.get(i).getKey());
        }
        return result;
    }

    public static void main(String[] args) {
        // Simple test harness
        File testFile = new File("test_log.txt");

        // Test 1: Basic scenario
        List<String> result1 = getMostFrequentErrors(testFile, 2);
        System.out.println("Test 1 result: " + result1);

        // Add more tests for edge cases
        // Example: T bigger than errors
        List<String> result2 = getMostFrequentErrors(testFile, 10);
        System.out.println("Test 2 result: " + result2);

        // Assess pass/fail manually or compare to expected.
        // For large data, ensure no timeout or memory issues.
    }
}
