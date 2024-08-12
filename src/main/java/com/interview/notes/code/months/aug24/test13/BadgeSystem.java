package com.interview.notes.code.months.aug24.test13;

import java.util.*;

public class BadgeSystem {
    public static List<List<String>> mismatches(String[][] records) {
        Set<String> noExit = new HashSet<>();
        Set<String> noEnter = new HashSet<>();
        Map<String, Integer> enterCount = new HashMap<>();

        for (String[] record : records) {
            String name = record[0];
            String action = record[1];

            if (action.equals("enter")) {
                enterCount.put(name, enterCount.getOrDefault(name, 0) + 1);
            } else if (action.equals("exit")) {
                if (!enterCount.containsKey(name) || enterCount.get(name) == 0) {
                    noEnter.add(name);
                } else {
                    enterCount.put(name, enterCount.get(name) - 1);
                }
            }
        }

        for (Map.Entry<String, Integer> entry : enterCount.entrySet()) {
            if (entry.getValue() > 0) {
                noExit.add(entry.getKey());
            }
        }

        List<List<String>> result = new ArrayList<>();
        result.add(new ArrayList<>(noExit));
        result.add(new ArrayList<>(noEnter));
        return result;
    }

    public static void main(String[] args) {
        // Test case 1
        String[][] records1 = {
                {"Paul", "enter"}, {"Pauline", "exit"},
                {"Paul", "enter"}, {"Paul", "exit"},
                {"Martha", "exit"},
                {"Joe", "enter"}, {"Martha", "enter"}, {"Steve", "enter"},
                {"Martha", "exit"},
                {"Jennifer", "enter"},
                {"Joe", "enter"}, {"Curtis", "exit"},
                {"Curtis", "enter"},
                {"Joe", "exit"},
                {"Martha", "enter"},
                {"Martha", "exit"},
                {"Jennifer", "exit"},
                {"Joe", "enter"},
                {"Joe", "enter"}, {"Martha", "exit"},
                {"Joe", "exit"},
                {"Joe", "exit"}
        };
        System.out.println("Test case 1 result: " + mismatches(records1));

        // Test case 2
        String[][] records2 = {
                {"Paul", "enter"}, {"Paul", "exit"}
        };
        System.out.println("Test case 2 result: " + mismatches(records2));

        // Test case 3
        String[][] records3 = {
                {"Paul", "enter"},
                {"Paul", "enter"},
                {"Paul", "exit"},
                {"Paul", "exit"}
        };
        System.out.println("Test case 3 result: " + mismatches(records3));

        // Test case 4
        String[][] records4 = {
                {"Raj", "enter"},
                {"Paul", "enter"},
                {"Paul", "exit"},
                {"Paul", "exit"},
                {"Paul", "enter"},
                {"Raj", "enter"}
        };
        System.out.println("Test case 4 result: " + mismatches(records4));
    }
}
