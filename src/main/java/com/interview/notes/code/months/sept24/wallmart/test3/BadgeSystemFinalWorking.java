package com.interview.notes.code.months.sept24.wallmart.test3;

import java.util.*;

/*

WORKING:

We are working on a security system for a badged-access room in our company's building.
Given an ordered list of employees who used their badge to enter or exit the room, write a function that returns two collections:
1. All employees who didn't use their badge while exiting the room - they recorded an enter without a matching exit. (All employees are required to leave the room before the log ends.)
2. All employees who didn't use their badge while entering the room - they recorded an exit without a matching enter. (The room is empty when the log begins.)
Each collection should contain no duplicates, regardless of how many times a given employee matches the criteria for belonging to it.

 */
public class BadgeSystemFinalWorking {
    public static void main(String[] args) {
        // Test cases
        String[][] records1 = {
            {"Paul", "enter"}, {"Pauline", "exit"}, {"Paul", "enter"}, {"Paul", "exit"},
            {"Martha", "exit"}, {"Joe", "enter"}, {"Martha", "enter"}, {"Steve", "enter"},
            {"Martha", "exit"}, {"Jennifer", "enter"}, {"Joe", "enter"}, {"Curtis", "exit"},
            {"Curtis", "enter"}, {"Joe", "exit"}, {"Martha", "enter"}, {"Martha", "exit"},
            {"Jennifer", "exit"}, {"Joe", "enter"}, {"Joe", "enter"}, {"Martha", "exit"},
            {"Joe", "exit"}, {"Joe", "exit"}
        };

        String[][] records2 = {{"Paul", "enter"}, {"Paul", "exit"}};

        String[][] records3 = {
            {"Paul", "enter"}, {"Paul", "enter"}, {"Paul", "exit"}, {"Paul", "exit"}
        };

        String[][] records4 = {
            {"Raj", "enter"}, {"Paul", "enter"}, {"Paul", "exit"}, {"Paul", "exit"},
            {"Paul", "enter"}, {"Raj", "enter"}
        };

        // Run test cases
        runTestCase(records1, "Test Case 1");
        runTestCase(records2, "Test Case 2");
        runTestCase(records3, "Test Case 3");
        runTestCase(records4, "Test Case 4");
    }

    public static void runTestCase(String[][] records, String testCaseName) {
        List<List<String>> result = mismatches(records);
        System.out.println(testCaseName + ":");
        System.out.println("Didn't exit: " + result.get(0));
        System.out.println("Didn't enter: " + result.get(1));
        System.out.println();
    }

    public static List<List<String>> mismatches(String[][] records) {
        Set<String> inRoom = new HashSet<>();
        Set<String> didntExit = new HashSet<>();
        Set<String> didntEnter = new HashSet<>();

        for (String[] record : records) {
            String name = record[0];
            String action = record[1];

            if (action.equals("enter")) {
                if (inRoom.contains(name)) {
                    didntExit.add(name);
                }
                inRoom.add(name);
            } else if (action.equals("exit")) {
                if (!inRoom.contains(name)) {
                    didntEnter.add(name);
                } else {
                    inRoom.remove(name);
                }
            }
        }

        // Anyone still in the room at the end didn't exit
        didntExit.addAll(inRoom);

        List<List<String>> result = new ArrayList<>();
        result.add(new ArrayList<>(didntExit));
        result.add(new ArrayList<>(didntEnter));

        return result;
    }
}