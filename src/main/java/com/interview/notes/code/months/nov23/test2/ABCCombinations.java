package com.interview.notes.code.months.nov23.test2;

import java.util.ArrayList;
import java.util.List;

public class ABCCombinations {

    public static void main(String[] args) {
        // Original list
        List<String> items = List.of("A1", "B1", "C1", "A2", "B2", "C2");

        // Separate the items into A, B, and C lists
        List<String> listA = new ArrayList<>();
        List<String> listB = new ArrayList<>();
        List<String> listC = new ArrayList<>();

        // Fill A, B and C lists
        for (String item : items) {
            switch (item.charAt(0)) {
                case 'A':
                    listA.add(item);
                    break;
                case 'B':
                    listB.add(item);
                    break;
                case 'C':
                    listC.add(item);
                    break;
                default:
                    // Handle unexpected items
                    System.err.println("Unexpected item: " + item);
                    break;
            }
        }

        // Generate all combinations
        for (String a : listA) {
            for (String b : listB) {
                for (String c : listC) {
                    System.out.println(a + b + c);
                }
            }
        }
    }
}
