package com.interview.notes.code.months.aug24.test20;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ColorCounter {
    public static void main(String[] args) {
        // Example test cases
        printEvenOccurrenceColors("green red green yellow");
        printEvenOccurrenceColors("red green red yellow green blue");
        printEvenOccurrenceColors("red red red green green");
        printEvenOccurrenceColors("blue yellow blue red yellow green");
    }

    public static void printEvenOccurrenceColors(String colors) {
        System.out.println("Input: " + colors);
        System.out.print("Colors occurring even number of times: ");

        Arrays.stream(colors.split("\\s+"))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() % 2 == 0)
                .map(Map.Entry::getKey)
                .forEach(color -> System.out.print(color + " "));

        System.out.println("\n");
    }
}
