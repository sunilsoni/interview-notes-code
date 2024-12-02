package com.interview.notes.code.year.y2024.aug24.amz.test14;

import java.util.*;

public class AssociateTaskSummary {
    public static String generateSummary(Map<String, List<Integer>> associateTasks) {
        TreeMap<Integer, List<String>> timestampMap = new TreeMap<>();

        for (Map.Entry<String, List<Integer>> entry : associateTasks.entrySet()) {
            String associate = entry.getKey();
            List<Integer> timestamps = entry.getValue();

            for (Integer timestamp : timestamps) {
                timestampMap.computeIfAbsent(timestamp, k -> new ArrayList<>()).add(associate);
            }
        }

        StringBuilder result = new StringBuilder();
        for (Map.Entry<Integer, List<String>> entry : timestampMap.entrySet()) {
            int timestamp = entry.getKey();
            List<String> associates = entry.getValue();

            for (String associate : associates) {
                if (result.length() > 0) {
                    result.append(", ");
                }
                result.append(associate).append(":").append(timestamp);
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Map<String, List<Integer>> associateTasks = new HashMap<>();
        associateTasks.put("Associate_1", Arrays.asList(1, 5, 7, 9));
        associateTasks.put("Associate_2", Arrays.asList(3, 4, 8));
        associateTasks.put("Associate_3", Arrays.asList(2, 6));

        String summary = generateSummary(associateTasks);
        System.out.println(summary);

        // Additional test cases
        associateTasks.clear();
        associateTasks.put("Associate_A", Arrays.asList(1, 3, 5));
        associateTasks.put("Associate_B", Arrays.asList(2, 4, 6));
        summary = generateSummary(associateTasks);
        System.out.println(summary);

        associateTasks.clear();
        associateTasks.put("Associate_X", Arrays.asList(10, 20, 30));
        associateTasks.put("Associate_Y", Arrays.asList(15, 25, 35));
        associateTasks.put("Associate_Z", Arrays.asList(5, 15, 25));
        summary = generateSummary(associateTasks);
        System.out.println(summary);
    }
}
