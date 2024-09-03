package com.interview.notes.code.months.sept24.wallmart.test1;

import java.util.*;

public class BadgeChecker {

    public static List<Set<String>> findMismatches(List<List<String>> records) {
        Map<String, Boolean> inRoom = new HashMap<>();
        Set<String> didntExit = new HashSet<>();
        Set<String> didntEnter = new HashSet<>();

        for (List<String> record : records) {
            String name = record.get(0);
            String action = record.get(1);

            if (action.equals("enter")) {
                if (inRoom.getOrDefault(name, false)) {
                    didntExit.add(name);
                }
                inRoom.put(name, true);
            } else { // exit
                if (!inRoom.getOrDefault(name, false)) {
                    didntEnter.add(name);
                }
                inRoom.put(name, false);
            }
        }

        // Check for any employees still in the room at the end
        for (Map.Entry<String, Boolean> entry : inRoom.entrySet()) {
            if (entry.getValue()) {
                didntExit.add(entry.getKey());
            }
        }

        return Arrays.asList(didntExit, didntEnter);
    }

    public static void main(String[] args) {
        // Test cases
        List<List<String>> records1 = Arrays.asList(
                Arrays.asList("Paul", "enter"),
                Arrays.asList("Pauline", "exit"),
                Arrays.asList("Paul", "enter"),
                Arrays.asList("Paul", "exit"),
                Arrays.asList("Martha", "exit"),
                Arrays.asList("Joe", "enter"),
                Arrays.asList("Martha", "enter"),
                Arrays.asList("Steve", "enter"),
                Arrays.asList("Martha", "exit"),
                Arrays.asList("Jennifer", "enter"),
                Arrays.asList("Joe", "enter"),
                Arrays.asList("Curtis", "exit"),
                Arrays.asList("Curtis", "enter"),
                Arrays.asList("Joe", "exit"),
                Arrays.asList("Martha", "enter"),
                Arrays.asList("Martha", "exit"),
                Arrays.asList("Jennifer", "exit"),
                Arrays.asList("Joe", "enter"),
                Arrays.asList("Joe", "enter"),
                Arrays.asList("Martha", "exit"),
                Arrays.asList("Joe", "exit"),
                Arrays.asList("Joe", "exit")
        );

        List<List<String>> records2 = Arrays.asList(
                Arrays.asList("Paul", "enter"),
                Arrays.asList("Paul", "exit")
        );

        List<List<String>> records3 = Arrays.asList(
                Arrays.asList("Paul", "enter"),
                Arrays.asList("Paul", "enter"),
                Arrays.asList("Paul", "exit"),
                Arrays.asList("Paul", "exit")
        );

        List<List<String>> records4 = Arrays.asList(
                Arrays.asList("Raj", "enter"),
                Arrays.asList("Paul", "enter"),
                Arrays.asList("Paul", "exit"),
                Arrays.asList("Paul", "exit"),
                Arrays.asList("Paul", "enter"),
                Arrays.asList("Raj", "enter")
        );

        System.out.println("Test case 1: " + findMismatches(records1));
        System.out.println("Test case 2: " + findMismatches(records2));
        System.out.println("Test case 3: " + findMismatches(records3));
        System.out.println("Test case 4: " + findMismatches(records4));
    }
}
