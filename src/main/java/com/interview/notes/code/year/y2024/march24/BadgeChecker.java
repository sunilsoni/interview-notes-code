package com.interview.notes.code.year.y2024.march24;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BadgeChecker {
    public static List<Set<String>> findBadgeDiscrepancies(String[][] records) {
        Set<String> didntExit = new HashSet<>();
        Set<String> didntEnter = new HashSet<>();
        Set<String> currentlyInside = new HashSet<>();

        for (String[] record : records) {
            String name = record[0];
            String action = record[1];

            if (action.equals("enter")) {
                if (currentlyInside.contains(name)) {
                    didntExit.add(name);
                } else {
                    currentlyInside.add(name);
                }
            } else if (action.equals("exit")) {
                if (currentlyInside.contains(name)) {
                    currentlyInside.remove(name);
                } else {
                    didntEnter.add(name);
                    // Add this line to handle multiple exits
                    didntExit.remove(name);
                }
            }
        }

        didntExit.addAll(currentlyInside);

        return Arrays.asList(didntExit, didntEnter);
    }

    public static void main(String[] args) {
        // ... (previous test cases)

        String[][] records4 = {
                {"Raj", "enter"},
                {"Paul", "enter"},
                {"Paul", "exit"},
                {"Paul", "exit"},
                {"Paul", "enter"},
                {"Raj", "enter"},
        };

        System.out.println("Test case 4: " + findBadgeDiscrepancies(records4));
    }
}
