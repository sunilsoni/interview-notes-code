package com.interview.notes.code.year.y2026.march.snowflake.test1;

import java.util.List;
import java.util.stream.IntStream;

public class SuperBitstringUnionCalculator {

    public static int superBitstrings(int n, List<Integer> bitStrings) {
        boolean[] d = new boolean[1 << n];
        bitStrings.forEach(v -> { d[v] = true; });
        
        for (int i = 0; i < n; i++) {
            for (int m = 0; m < (1 << n); m++) {
                if ((m & (1 << i)) != 0) {
                    d[m] |= d[m ^ (1 << i)];
                }
            }
        }
        
        return (int) IntStream.range(0, 1 << n).filter(i -> d[i]).count();
    }

    public static void main(String[] args) {
        runTest(2, List.of(1), 2, "Sample 0");
        runTest(3, List.of(2, 6), 4, "Sample 1");
        runTest(5, List.of(10, 26), 8, "Example");
        
        List<Integer> largeInput = IntStream.range(0, 50000).boxed().toList();
        int largeResult = superBitstrings(18, largeInput);
        System.out.println("Large Data Test: " + (largeResult == 262144 ? "PASS" : "FAIL"));
    }

    private static void runTest(int n, List<Integer> b, int expected, String name) {
        System.out.println(name + " Test: " + (superBitstrings(n, b) == expected ? "PASS" : "FAIL"));
    }
}