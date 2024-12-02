package com.interview.notes.code.year.y2024.sept24.wallmart.test2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BadgeRecords {

    public static void main(String[] args) {
        List<String[]> records1 = Arrays.asList(
                new String[]{"Paul", "enter"}, new String[]{"Pauline", "exit"}, new String[]{"Paul", "enter"},
                new String[]{"Paul", "exit"}, new String[]{"Martha", "exit"}, new String[]{"Joe", "enter"},
                new String[]{"Martha", "enter"}, new String[]{"Steve", "enter"}, new String[]{"Martha", "exit"},
                new String[]{"Jennifer", "enter"}, new String[]{"Joe", "enter"}, new String[]{"Curtis", "exit"},
                new String[]{"Curtis", "enter"}, new String[]{"Joe", "exit"}, new String[]{"Joe", "enter"},
                new String[]{"Martha", "enter"}, new String[]{"Martha", "exit"}, new String[]{"Joe", "exit"},
                new String[]{"Jennifer", "exit"}
        );

        System.out.println(mismatches(records1));
    }

    public static List<Set<String>> mismatches(List<String[]> records) {
        Set<String> enterWithoutExit = new HashSet<>();
        Set<String> exitWithoutEnter = new HashSet<>();

        for (String[] record : records) {
            String employee = record[0];
            String action = record[1];

            if (action.equals("enter")) {
                if (!exitWithoutEnter.remove(employee)) {
                    enterWithoutExit.add(employee);
                }
            } else if (action.equals("exit")) {
                if (!enterWithoutExit.remove(employee)) {
                    exitWithoutEnter.add(employee);
                }
            }
        }

        return Arrays.asList(enterWithoutExit, exitWithoutEnter);
    }
}
