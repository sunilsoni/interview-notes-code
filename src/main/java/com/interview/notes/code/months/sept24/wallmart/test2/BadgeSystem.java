package com.interview.notes.code.months.sept24.wallmart.test2;

import java.util.*;

public class BadgeSystem {

    public static void main(String[] args) {
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

        Map<String, List<String>> result = findMismatches(records1);
        System.out.println("No Exit Badge: " + result.get("noExitBadge"));
        System.out.println("No Enter Badge: " + result.get("noEnterBadge"));
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
                if (status <= 0) {
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
