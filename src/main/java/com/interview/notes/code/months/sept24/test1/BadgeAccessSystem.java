package com.interview.notes.code.months.sept24.test1;

import java.util.*;

public class BadgeAccessSystem {

    public static List<List<String>> mismatches(List<List<String>> records) {
        Set<String> inRoom = new HashSet<>();
        Set<String> enteredWithoutExit = new HashSet<>();
        Set<String> exitedWithoutEnter = new HashSet<>();

        for (List<String> record : records) {
            String employee = record.get(0);
            String action = record.get(1);

            if (action.equals("enter")) {
                if (inRoom.contains(employee)) {
                    enteredWithoutExit.add(employee);
                } else {
                    inRoom.add(employee);
                }
            } else if (action.equals("exit")) {
                if (inRoom.contains(employee)) {
                    inRoom.remove(employee);
                } else {
                    exitedWithoutEnter.add(employee);
                }
            }
        }

        // Anyone still in the room at the end entered without exiting
        enteredWithoutExit.addAll(inRoom);

        return Arrays.asList(
                new ArrayList<>(enteredWithoutExit),
                new ArrayList<>(exitedWithoutEnter)
        );
    }

    public static void main(String[] args) {
        // Test case 1
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

        List<List<String>> result1 = mismatches(records1);
        System.out.println("Test Case 1:");
        System.out.println("Expected: [Steve, Curtis, Paul, Joe], [Martha, Pauline, Curtis, Joe]");
        System.out.println("Actual: " + result1);
        System.out.println("Pass: " + (result1.get(0).containsAll(Arrays.asList("Steve", "Curtis", "Paul", "Joe")) &&
                result1.get(1).containsAll(Arrays.asList("Martha", "Pauline", "Curtis", "Joe"))));

        // Test case 2
        List<List<String>> records2 = Arrays.asList(
                Arrays.asList("Paul", "enter"),
                Arrays.asList("Paul", "exit")
        );

        List<List<String>> result2 = mismatches(records2);
        System.out.println("\nTest Case 2:");
        System.out.println("Expected: [], []");
        System.out.println("Actual: " + result2);
        System.out.println("Pass: " + (result2.get(0).isEmpty() && result2.get(1).isEmpty()));

        // Test case 3
        List<List<String>> records3 = Arrays.asList(
                Arrays.asList("Paul", "enter"),
                Arrays.asList("Paul", "enter"),
                Arrays.asList("Paul", "exit"),
                Arrays.asList("Paul", "exit")
        );

        List<List<String>> result3 = mismatches(records3);
        System.out.println("\nTest Case 3:");
        System.out.println("Expected: [Paul], [Paul]");
        System.out.println("Actual: " + result3);
        System.out.println("Pass: " + (result3.get(0).equals(Arrays.asList("Paul")) && result3.get(1).equals(Arrays.asList("Paul"))));

        // Test case 4
        List<List<String>> records4 = Arrays.asList(
                Arrays.asList("Raj", "enter"),
                Arrays.asList("Paul", "enter"),
                Arrays.asList("Paul", "exit"),
                Arrays.asList("Paul", "exit"),
                Arrays.asList("Paul", "enter"),
                Arrays.asList("Raj", "enter")
        );

        List<List<String>> result4 = mismatches(records4);
        System.out.println("\nTest Case 4:");
        System.out.println("Expected: [Raj, Paul], [Paul]");
        System.out.println("Actual: " + result4);
        System.out.println("Pass: " + (result4.get(0).containsAll(Arrays.asList("Raj", "Paul")) && result4.get(1).equals(Arrays.asList("Paul"))));
    }
}
