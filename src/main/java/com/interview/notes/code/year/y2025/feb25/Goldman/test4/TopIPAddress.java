package com.interview.notes.code.year.y2025.feb25.Goldman.test4;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TopIPAddress {
    public static String findTopIPAddress(String[] logLines) {
        return Arrays.stream(logLines)
                .map(line -> line.split(" ")[0]) // Extract the IP address (assuming it's the first part of the line)
                .collect(Collectors.groupingBy(ip -> ip, Collectors.counting())) // Count occurrences
                .entrySet().stream()
                .max(Map.Entry.comparingByValue()) // Find the IP with the max occurrences
                .map(Map.Entry::getKey)
                .orElse(""); // Return the most frequent IP or empty string if none
    }

    public static String findTopKthIpAddress(String[] logLines, int k) {
        Map<String, Long> frequencyMap = Arrays.stream(logLines)
                .map(line -> line.split(" ")[0]) // Extract IP address
                .collect(Collectors.groupingBy(ip -> ip, Collectors.counting())); // Count occurrences

        // Sort by frequency in descending order and get the K-th most frequent IP
        List<Map.Entry<String, Long>> sortedList = frequencyMap.entrySet()
                .stream()
                .sorted((a, b) -> Long.compare(b.getValue(), a.getValue())) // Sort descending
                .collect(Collectors.toList());

        return (k > 0 && k <= sortedList.size()) ? sortedList.get(k - 1).getKey() : null; // Return K-th element
    }

    public static void main(String[] args) {
        String[] logLines = {
                "10.0.0.1 -- frank [10/Dec/2000:12:34:56 -0500] \"GET /a.gif HTTP/1.0\" 200 234",
                "10.0.0.1 -- frank [10/Dec/2000:12:35:57 -0500] \"GET /b.gif HTTP/1.0\" 200 234",
                "10.0.0.1 -- sally [10/Dec/2000:12:35:58 -0500] \"GET /b.gif HTTP/1.0\" 200 234",
                "10.0.0.4 -- frank [10/Dec/2000:12:41:57 -0500] \"GET /b.gif HTTP/1.0\" 200 234",
                "10.0.0.2 -- davey [10/Dec/2000:12:41:58 -0500] \"GET /b.gif HTTP/1.0\" 200 234",
                "10.0.0.1 -- frank [10/Dec/2000:12:42:57 -0500] \"GET /b.gif HTTP/1.0\" 200 234",
                "10.0.0.8 -- frank [10/Dec/2000:12:43:57 -0500] \"GET /b.gif HTTP/1.0\" 200 234",
                "10.0.0.1 -- frank [10/Dec/2000:12:44:57 -0500] \"GET /b.gif HTTP/1.0\" 200 234",
                "10.0.0.2 -- nancy [10/Dec/2000:12:44:58 -0500] \"GET /c.gif HTTP/1.0\" 200 234"
        };

    }
}
