package com.interview.notes.code.year.y2026.jan.assessments.imocha.test1;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SegmentFilter {

    public static int removalOfNodes(int N, int[] Node) {
        String result = Arrays.stream(Node)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() % 2 == 0)
                .flatMap(e -> Collections.nCopies(e.getValue().intValue(), e.getKey()).stream())
                .map(String::valueOf)
                .collect(Collectors.joining(" "));

        System.out.print(result);
        return 0;
    }

    public static void main(String[] args) {
        runTest("Sample Input", 9, new int[]{1, 1, 2, 2, 2, 3, 4, 4, 5}, "1 1 4 4");
        runTest("Logic Check (Odd Removal)", 5, new int[]{1, 2, 2, 2, 3}, "");
        runTest("Logic Check (All Even)", 4, new int[]{5, 5, 8, 8}, "5 5 8 8");

        int[] largeInput = new int[100000];
        Arrays.fill(largeInput, 0, 50000, 10);
        Arrays.fill(largeInput, 50000, 100000, 20);
        String largeExpected = IntStream.concat(
                IntStream.generate(() -> 10).limit(50000),
                IntStream.generate(() -> 20).limit(50000)
        ).mapToObj(String::valueOf).collect(Collectors.joining(" "));

        runTest("Large Data Input (10^5)", 100000, largeInput, largeExpected);
    }

    private static void runTest(String testName, int n, int[] input, String expected) {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        removalOfNodes(n, input);

        System.setOut(originalOut);
        String actual = outContent.toString();
        boolean passed = actual.equals(expected);
        System.out.println(testName + ": " + (passed ? "PASS" : "FAIL"));
        if (!passed) System.out.println("Expected: " + expected + "\nGot: " + actual);
    }
}