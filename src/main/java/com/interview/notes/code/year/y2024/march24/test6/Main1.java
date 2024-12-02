package com.interview.notes.code.year.y2024.march24.test6;

import java.util.List;

public class Main1 {
    public static void main(String[] args) {
        List<List<String>> commands1 = List.of(
                List.of("Insert", "addthis"),
                List.of("Print", "5"),
                List.of("Left", "4"),
                List.of("Right", "2"),
                List.of("Backspace", "1"),
                List.of("Delete", "1"),
                List.of("Print", "10")
        );

        List<List<String>> commands2 = List.of(
                List.of("Insert", "present"),
                List.of("Print", "5"),
                List.of("Left", "4"),
                List.of("Insert", "test"),
                List.of("Print", "8"),
                List.of("Delete", "4"),
                List.of("Print", "7")
        );

        List<List<String>> commands3 = List.of(
                List.of("Insert", "xgpuamkx"),
                List.of("Print", "1"),
                List.of("Insert", "kbpph"),
                List.of("Left", "5"),
                List.of("Insert", "ezpl"),
                List.of("Print", "1"),
                List.of("Print", "2"),
                List.of("Insert", "op")
        );

        System.out.println("Example 1 Output: " + PrintedStrings.getPrintedStrings(commands1));
        System.out.println("Example 2 Output: " + PrintedStrings.getPrintedStrings(commands2));
        System.out.println("Example 3 Output: " + PrintedStrings.getPrintedStrings(commands3));
    }
}
