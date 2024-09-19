package com.interview.notes.code.months.sept24.test10;

import java.util.*;

public class BadgeAccessSystem {

    public static void main(String[] args) {
        // Test Case 1
        String[][] records1 = {
                {"Paul", "enter"},
                {"Pauline", "exit"},
                {"Paul", "enter"},
                {"Paul", "exit"},
                {"Martha", "exit"},
                {"Joe", "enter"},
                {"Martha", "enter"},
                {"Steve", "enter"},
                {"Martha", "exit"},
                {"Jennifer", "enter"},
                {"Joe", "enter"},
                {"Curtis", "exit"},
                {"Curtis", "enter"},
                {"Joe", "exit"},
                {"Martha", "enter"},
                {"Martha", "exit"},
                {"Jennifer", "exit"},
                {"Joe", "enter"},
                {"Joe", "enter"},
                {"Martha", "exit"},
                {"Joe", "exit"},
                {"Joe", "exit"}
        };
        testMismatch(records1, "Test Case 1");

        // Test Case 2
        String[][] records2 = {
                {"Paul", "enter"},
                {"Paul", "exit"}
        };
        testMismatch(records2, "Test Case 2");

        // Test Case 3
        String[][] records3 = {
                {"Paul", "enter"},
                {"Paul", "enter"},
                {"Paul", "exit"},
                {"Paul", "exit"}
        };
        testMismatch(records3, "Test Case 3");

        // Test Case 4
        String[][] records4 = {
                {"Raj", "enter"},
                {"Paul", "enter"},
                {"Paul", "exit"},
                {"Paul", "exit"},
                {"Paul", "enter"},
                {"Raj", "enter"}
        };
        testMismatch(records4, "Test Case 4");
    }

    private static void testMismatch(String[][] records, String testCaseName) {
        System.out.println(testCaseName + ":");
        Map<String, List<String>> result = findMismatches(records);
        System.out.println("Did not badge out: " + result.get("noExitBadge"));
        System.out.println("Did not badge in: " + result.get("noEnterBadge"));
        System.out.println();
    }

    private static Map<String, List<String>> findMismatches(String[][] records) {
        Set<String> noExitBadge = new HashSet<>();
        Set<String> noEnterBadge = new HashSet<>();
        Map<String, Integer> employeeStatus = new HashMap<>();

        for (String[] record : records) {
            String name = record[0];
            String action = record[1];

            employeeStatus.putIfAbsent(name, 0);
            int status = employeeStatus.get(name);

            if (action.equals("enter")) {
                if (status > 0) {
                    noExitBadge.add(name);
                }
                employeeStatus.put(name, status + 1);
            } else if (action.equals("exit")) {
                if (status != 0) {
                    noEnterBadge.add(name);
                } else {
                    employeeStatus.put(name, status - 1);
                }
            }
        }

        // Check for employees still inside the room
        for (Map.Entry<String, Integer> entry : employeeStatus.entrySet()) {
            String name = entry.getKey();
            int status = entry.getValue();
            if (status > 0) {
                noExitBadge.add(name);
            }
        }

        List<String> noExitBadgeList = new ArrayList<>(noExitBadge);
        List<String> noEnterBadgeList = new ArrayList<>(noEnterBadge);

        // Sort the lists for consistent output
        Collections.sort(noExitBadgeList);
        Collections.sort(noEnterBadgeList);

        Map<String, List<String>> result = new HashMap<>();
        result.put("noExitBadge", noExitBadgeList);
        result.put("noEnterBadge", noEnterBadgeList);

        return result;
    }
}
