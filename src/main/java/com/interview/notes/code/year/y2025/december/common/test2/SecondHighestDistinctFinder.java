package com.interview.notes.code.year.y2025.december.common.test2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

public class SecondHighestDistinctFinder {

    public static String codeHere(StringBuilder inputData) {
        return Arrays.stream(inputData.toString().split("\n"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .mapToLong(Long::parseLong)
                .boxed()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .map(String::valueOf)
                .orElse("NA");
    }

    public static void main(String[] args) {
        runTest("Sample Input", "10\n20\n30\n20\n50\n40", "40");
        runTest("Single Element", "5", "NA");
        runTest("All Duplicates", "7\n7\n7\n7", "NA");
        runTest("Two Distinct", "100\n200", "100");
        runTest("Empty Input", "", "NA");
        runTest("Negative Numbers", "-5\n-10\n-3\n-8", "-5");
        runTest("Mixed Numbers", "-100\n0\n50\n-50\n100", "50");
        runTest("With Spaces", "  15  \n  25  \n  35  ", "25");
        runTest("Large Values", "999999999\n888888888\n777777777", "888888888");
        runTest("Single Line Spaces", "   ", "NA");
        runTest("Descending Order", "50\n40\n30\n20\n10", "40");
        runTest("Ascending Order", "10\n20\n30\n40\n50", "40");
        runLargeDataTest();
    }

    static void runTest(String name, String input, String expected) {
        String result = codeHere(new StringBuilder(input));
        System.out.println(name + ": " + (result.equals(expected) ? "PASS" : "FAIL"));
    }

    static void runLargeDataTest() {
        StringBuilder largeInput = new StringBuilder();
        IntStream.rangeClosed(1, 100000).forEach(i -> largeInput.append(i).append("\n"));
        String result = codeHere(largeInput);
        System.out.println("Large Data (100K): " + (result.equals("99999") ? "PASS" : "FAIL"));
    }
}