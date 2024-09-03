package com.interview.notes.code.months.sept24.wallmart.test1;

import java.util.*;

public class BadgeAccessSystem {
    public static List<List<String>> findMismatches(String[][] records) {
        Map<String, Integer> employeeState = new HashMap<>();
        Set<String> enteredWithoutExit = new HashSet<>();
        Set<String> exitedWithoutEnter = new HashSet<>();

        for (String[] record : records) {
            String employee = record[0];
            String action = record[1];

            if (action.equals("enter")) {
                employeeState.put(employee, employeeState.getOrDefault(employee, 0) + 1);
                if (employeeState.get(employee) > 1) {
                    enteredWithoutExit.add(employee);
                }
            } else { // exit
                if (!employeeState.containsKey(employee) || employeeState.get(employee) == 0) {
                    exitedWithoutEnter.add(employee);
                }
                employeeState.put(employee, employeeState.getOrDefault(employee, 0) - 1);
                if (employeeState.get(employee) < 0) {
                    exitedWithoutEnter.add(employee);
                }
            }
        }

        // Check final state
        for (Map.Entry<String, Integer> entry : employeeState.entrySet()) {
            if (entry.getValue() > 0) {
                enteredWithoutExit.add(entry.getKey());
            }
        }

        return Arrays.asList(
                new ArrayList<>(enteredWithoutExit),
                new ArrayList<>(exitedWithoutEnter)
        );
    }


    public static void main(String[] args) {
        // Test case 1
        String[][] records1 = {
                {"Paul", "enter"}, {"Pauline", "exit"}, {"Paul", "enter"}, {"Paul", "exit"},
                {"Martha", "exit"}, {"Joe", "enter"}, {"Martha", "enter"}, {"Steve", "enter"},
                {"Martha", "exit"}, {"Jennifer", "enter"}, {"Joe", "enter"}, {"Curtis", "exit"},
                {"Curtis", "enter"}, {"Joe", "exit"}, {"Martha", "enter"}, {"Martha", "exit"},
                {"Jennifer", "exit"}, {"Joe", "enter"}, {"Joe", "enter"}, {"Martha", "exit"},
                {"Joe", "exit"}, {"Joe", "exit"}
        };

        // Test case 2
        String[][] records2 = {{"Paul", "enter"}, {"Paul", "exit"}};

        // Test case 3
        String[][] records3 = {
                {"Paul", "enter"}, {"Paul", "enter"}, {"Paul", "exit"}, {"Paul", "exit"}
        };

        // Test case 4
        String[][] records4 = {
                {"Raj", "enter"}, {"Paul", "enter"}, {"Paul", "exit"}, {"Paul", "exit"},
                {"Paul", "enter"}, {"Raj", "enter"}
        };

        // Run test cases
        runTestCase(records1, "Test Case 1", Arrays.asList(Arrays.asList("Steve", "Curtis", "Paul", "Joe"), Arrays.asList("Martha", "Pauline", "Curtis", "Joe")));
        runTestCase(records2, "Test Case 2", Arrays.asList(new ArrayList<>(), new ArrayList<>()));
        runTestCase(records3, "Test Case 3", Arrays.asList(Arrays.asList("Paul"), Arrays.asList("Paul")));
        runTestCase(records4, "Test Case 4", Arrays.asList(Arrays.asList("Raj", "Paul"), Arrays.asList("Paul")));
    }

    private static void runTestCase(String[][] records, String testName, List<List<String>> expectedOutput) {
        List<List<String>> result = findMismatches(records);
        boolean passed = result.size() == expectedOutput.size() &&
                new HashSet<>(result.get(0)).equals(new HashSet<>(expectedOutput.get(0))) &&
                new HashSet<>(result.get(1)).equals(new HashSet<>(expectedOutput.get(1)));

        System.out.println(testName + ": " + (passed ? "PASSED" : "FAILED"));
        if (!passed) {
            System.out.println("Expected: " + expectedOutput);
            System.out.println("Actual: " + result);
        }
        System.out.println();
    }
}
