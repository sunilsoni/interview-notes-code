package com.interview.notes.code.months.aug24.test13;

import java.util.HashSet;
import java.util.Set;

public class BadgeChecker1 {
    public static EmployeeMismatches findMismatches(String[][] records) {
        Set<String> missingExit = new HashSet<>();
        Set<String> missingEnter = new HashSet<>();
        Set<String> inRoom = new HashSet<>();

        for (String[] record : records) {
            String employee = record[0];
            String action = record[1];

            if (action.equals("enter")) {
                if (inRoom.contains(employee)) {
                    missingExit.add(employee);
                }
                inRoom.add(employee);
            } else if (action.equals("exit")) {
                if (!inRoom.contains(employee)) {
                    missingEnter.add(employee);
                } else {
                    inRoom.remove(employee);
                }
            }
        }

        missingExit.addAll(inRoom);

        return new EmployeeMismatches(missingExit, missingEnter);
    }

    public static void main(String[] args) {
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

        EmployeeMismatches result = findMismatches(records11);

        System.out.println("Missing Exit: " + result.missingExit);
        System.out.println("Missing Enter: " + result.missingEnter);
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
