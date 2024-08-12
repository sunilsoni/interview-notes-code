package com.interview.notes.code.months.aug24.test12;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class BadgeSystem {
    public static EmployeeMismatches findMismatches(String[][] records) {
        Set<String> missingExit = new HashSet<>();
        Set<String> missingEnter = new HashSet<>();
        Deque<String> employeesInRoom = new ArrayDeque<>();

        for (String[] record : records) {
            String employee = record[0];
            String action = record[1];

            if ("enter".equals(action)) {
                if (!employeesInRoom.isEmpty() && employeesInRoom.peek().equals(employee)) {
                    missingExit.add(employee);
                } else {
                    employeesInRoom.push(employee);
                }
            } else if ("exit".equals(action)) {
                if (employeesInRoom.isEmpty() || !employeesInRoom.peek().equals(employee)) {
                    missingEnter.add(employee);
                } else {
                    employeesInRoom.pop();
                }
            }
        }

        missingExit.addAll(employeesInRoom);
        return new EmployeeMismatches(missingExit, missingEnter);
    }

    public static void main(String[] args) {
        // Test case 1
        String[][] records11 = {
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
                {"Joe", "exit"},
        };
        // Test case 2
        String[][] records2 = {
                {"Paul", "enter"}, {"Paul", "exit"}
        };

        // Test case 3
        String[][] records3 = {
                {"Paul", "enter"},
                {"Paul", "enter"},
                {"Paul", "exit"},
                {"Paul", "exit"}
        };

        // Test case 4 (from previous example)
        String[][] records4 = {
                {"Raj", "enter"}, {"Paul", "enter"}, {"Paul", "exit"}, {"Paul", "exit"},
                {"Paul", "enter"}, {"Raj", "enter"}
        };

        // Run all test cases
        EmployeeMismatches[] results = new EmployeeMismatches[4];
        results[0] = findMismatches(records1);
        results[1] = findMismatches(records2);
        results[2] = findMismatches(records3);
        results[3] = findMismatches(records4);

        // Print results
        for (int i = 0; i < results.length; i++) {
            System.out.println("Test case " + (i + 1) + ":");
            System.out.println("Missing Exit: " + results[i].missingExit);
            System.out.println("Missing Enter: " + results[i].missingEnter);
            System.out.println();
        }
    }

    public static class EmployeeMismatches {
        Set<String> missingExit;
        Set<String> missingEnter;

        public EmployeeMismatches(Set<String> missingExit, Set<String> missingEnter) {
            this.missingExit = missingExit;
            this.missingEnter = missingEnter;
        }
    }
}
