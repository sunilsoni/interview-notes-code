package com.interview.notes.code.months.Oct23.test14;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class TopLastNames {

    public static void main(String[] args) {
        // Replace this with the path to your flat file
        String filePath = "path/to/your/file.txt";

        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            Map<String, Integer> lastNameFrequency = new HashMap<>();

            for (String line : lines) {
                String[] parts = line.split(" "); // Assuming space is the separator
                if (parts.length >= 2) {
                    String lastName = parts[1];
                    lastNameFrequency.put(lastName, lastNameFrequency.getOrDefault(lastName, 0) + 1);
                }
            }

            List<Map.Entry<String, Integer>> sortedLastNames = lastNameFrequency.entrySet()
                    .stream()
                    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                    .limit(5)
                    .collect(Collectors.toList());

            System.out.println("Top 5 Most Common Last Names:");
            for (Map.Entry<String, Integer> entry : sortedLastNames) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
