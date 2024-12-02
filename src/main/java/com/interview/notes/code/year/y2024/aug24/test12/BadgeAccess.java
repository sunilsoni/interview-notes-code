package com.interview.notes.code.year.y2024.aug24.test12;

import java.util.*;

public class BadgeAccess {

    public static void main(String[] args) {
        List<String[]> records1 = Arrays.asList(
                new String[]{"Paul", "enter"}, new String[]{"Pauline", "exit"},
                new String[]{"Paul", "enter"}, new String[]{"Paul", "exit"},
                new String[]{"Martha", "exit"}, new String[]{"Joe", "enter"},
                new String[]{"Martha", "enter"}, new String[]{"Steve", "enter"},
                new String[]{"Martha", "exit"}, new String[]{"Jennifer", "enter"},
                new String[]{"Joe", "enter"}, new String[]{"Curtis", "exit"},
                new String[]{"Curtis", "enter"}, new String[]{"Joe", "exit"},
                new String[]{"Martha", "enter"}, new String[]{"Martha", "exit"},
                new String[]{"Jennifer", "exit"}, new String[]{"Joe", "enter"},
                new String[]{"Joe", "enter"}, new String[]{"Martha", "exit"},
                new String[]{"Joe", "exit"}, new String[]{"Joe", "exit"}
        );

        List<String[]> records2 = Arrays.asList(
                new String[]{"Paul", "enter"}, new String[]{"Paul", "exit"}
        );

        List<String[]> records3 = Arrays.asList(
                new String[]{"Paul", "enter"}, new String[]{"Paul", "enter"},
                new String[]{"Paul", "exit"}, new String[]{"Paul", "exit"}
        );

        List<String[]> records4 = Arrays.asList(
                new String[]{"Raj", "enter"}, new String[]{"Paul", "enter"},
                new String[]{"Paul", "exit"}, new String[]{"Paul", "exit"},
                new String[]{"Paul", "enter"}, new String[]{"Raj", "enter"}
        );

        System.out.println(mismatches(records1));
        System.out.println(mismatches(records2));
        System.out.println(mismatches(records3));
        System.out.println(mismatches(records4));
    }

    public static Map<String, Set<String>> mismatches(List<String[]> records) {
        Map<String, Integer> employeeStatus = new HashMap<>();

        for (String[] record : records) {
            String employee = record[0];
            String action = record[1];

            employeeStatus.put(employee, employeeStatus.getOrDefault(employee, 0) + (action.equals("enter") ? 1 : -1));
        }

        Set<String> unmatchedEntry = new HashSet<>();
        Set<String> unmatchedExit = new HashSet<>();

        for (Map.Entry<String, Integer> entry : employeeStatus.entrySet()) {
            String employee = entry.getKey();
            int count = entry.getValue();

            if (count > 0) {
                unmatchedExit.add(employee);
            } else if (count < 0) {
                unmatchedEntry.add(employee);
            }
        }

        return Map.of(
                "unmatchedExit", unmatchedExit,
                "unmatchedEntry", unmatchedEntry
        );
    }
}
