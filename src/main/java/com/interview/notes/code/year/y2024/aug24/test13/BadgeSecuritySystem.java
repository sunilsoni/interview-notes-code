package com.interview.notes.code.year.y2024.aug24.test13;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BadgeSecuritySystem {

    public static void main(String[] args) {
        List<List<String>> records1 = new ArrayList<>();
        records1.add(List.of("Paul", "enter"));
        records1.add(List.of("Pauline", "exit"));
        records1.add(List.of("Paul", "enter"));
        records1.add(List.of("Paul", "exit"));
        records1.add(List.of("Martha", "exit"));
        records1.add(List.of("Joe", "enter"));
        records1.add(List.of("Martha", "enter"));
        records1.add(List.of("Steve", "enter"));
        records1.add(List.of("Martha", "exit"));
        records1.add(List.of("Jennifer", "enter"));
        records1.add(List.of("Joe", "enter"));
        records1.add(List.of("Curtis", "exit"));
        records1.add(List.of("Curtis", "enter"));
        records1.add(List.of("Joe", "exit"));
        records1.add(List.of("Martha", "enter"));
        records1.add(List.of("Jennifer", "exit"));
        records1.add(List.of("Joe", "enter"));
        records1.add(List.of("Joe", "enter"));
        records1.add(List.of("Martha", "exit"));
        records1.add(List.of("Joe", "exit"));
        records1.add(List.of("Joe", "exit"));

        List<List<String>> result1 = findMissingInOutRecords(records1);
        System.out.println("Missing Enters: " + result1.get(0));
        System.out.println("Missing Exits: " + result1.get(1));

        List<List<String>> records2 = new ArrayList<>();
        records2.add(List.of("Paul", "enter"));
        records2.add(List.of("Paul", "exit"));
        List<List<String>> result2 = findMissingInOutRecords(records2);
        System.out.println("Missing Enters: " + result2.get(0));
        System.out.println("Missing Exits: " + result2.get(1));

        List<List<String>> records3 = new ArrayList<>();
        records3.add(List.of("Paul", "enter"));
        records3.add(List.of("Paul", "enter"));
        records3.add(List.of("Paul", "exit"));
        records3.add(List.of("Paul", "exit"));
        List<List<String>> result3 = findMissingInOutRecords(records3);
        System.out.println("Missing Enters: " + result3.get(0));
        System.out.println("Missing Exits: " + result3.get(1));

        List<List<String>> records4 = new ArrayList<>();
        records4.add(List.of("Raj", "enter"));
        records4.add(List.of("Paul", "enter"));
        records4.add(List.of("Paul", "exit"));
        records4.add(List.of("Paul", "exit"));
        records4.add(List.of("Paul", "enter"));
        records4.add(List.of("Raj", "enter"));
        List<List<String>> result4 = findMissingInOutRecords(records4);
        System.out.println("Missing Enters: " + result4.get(0));
        System.out.println("Missing Exits: " + result4.get(1));

        // New test cases
        List<List<String>> records5 = new ArrayList<>();
        records5.add(List.of("Paul", "enter"));
        records5.add(List.of("Pauline", "exit"));
        records5.add(List.of("Paul", "enter"));
        records5.add(List.of("Paul", "exit"));
        records5.add(List.of("Martha", "exit"));
        records5.add(List.of("Joe", "enter"));
        records5.add(List.of("Martha", "enter"));
        records5.add(List.of("Steve", "enter"));
        records5.add(List.of("Martha", "exit"));
        records5.add(List.of("Jennifer", "enter"));
        records5.add(List.of("Joe", "enter"));
        records5.add(List.of("Curtis", "exit"));
        records5.add(List.of("Curtis", "enter"));
        records5.add(List.of("Joe", "exit"));
        records5.add(List.of("Martha", "enter"));
        records5.add(List.of("Jennifer", "exit"));
        records5.add(List.of("Joe", "enter"));
        records5.add(List.of("Joe", "enter"));
        records5.add(List.of("Martha", "exit"));
        records5.add(List.of("Joe", "exit"));
        records5.add(List.of("Joe", "exit"));
        List<List<String>> result5 = findMissingInOutRecords(records5);
        System.out.println("Missing Enters: " + result5.get(0));
        System.out.println("Missing Exits: " + result5.get(1));

        List<List<String>> records6 = new ArrayList<>();
        records6.add(List.of("Paul", "enter"));
        records6.add(List.of("Paul", "exit"));
        List<List<String>> result6 = findMissingInOutRecords(records6);
        System.out.println("Missing Enters: " + result6.get(0));
        System.out.println("Missing Exits: " + result6.get(1));

        List<List<String>> records7 = new ArrayList<>();
        records7.add(List.of("Paul", "enter"));
        records7.add(List.of("Paul", "enter"));
        records7.add(List.of("Paul", "exit"));
        records7.add(List.of("Paul", "exit"));
        List<List<String>> result7 = findMissingInOutRecords(records7);
        System.out.println("Missing Enters: " + result7.get(0));
        System.out.println("Missing Exits: " + result7.get(1));
    }

    public static List<List<String>> findMissingInOutRecords(List<List<String>> records) {
        Set<String> missingEnters = new HashSet<>();
        Set<String> missingExits = new HashSet<>();
        Set<String> enteredEmployees = new HashSet<>();

        for (List<String> record : records) {
            String employee = record.get(0);
            String action = record.get(1);

            if (action.equals("enter")) {
                enteredEmployees.add(employee);
            } else if (action.equals("exit")) {
                if (enteredEmployees.contains(employee)) {
                    enteredEmployees.remove(employee);
                } else {
                    missingEnters.add(employee);
                }
            }
        }

        // Add employees who entered but didn't exit
        missingExits.addAll(enteredEmployees);

        return List.of(new ArrayList<>(missingEnters), new ArrayList<>(missingExits));
    }
}