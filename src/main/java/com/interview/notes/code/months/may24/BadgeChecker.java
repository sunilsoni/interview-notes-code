package com.interview.notes.code.months.may24;

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
                }
            }
        }

        didntExit.addAll(currentlyInside);

        return Arrays.asList(didntExit, didntEnter);
    }

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

        String[][] records2 = {
                {"Paul", "enter"},
                {"Paul", "exit"}
        };

        String[][] records3 = {
                {"Paul", "enter"},
                {"Paul", "enter"},
                {"Paul", "exit"},
                {"Paul", "exit"}
        };

        String[][] records4 = {
                {"Raj", "enter"},
                {"Paul", "enter"},
                {"Paul", "exit"},
                {"Paul", "exit"},
                {"Paul", "enter"},
                {"Raj", "enter"}
        };

        System.out.println("Test case 1: " + findBadgeDiscrepancies(records1));
        System.out.println("Test case 2: " + findBadgeDiscrepancies(records2));
        System.out.println("Test case 3: " + findBadgeDiscrepancies(records3));
        System.out.println("Test case 4: " + findBadgeDiscrepancies(records4));
    }
}
