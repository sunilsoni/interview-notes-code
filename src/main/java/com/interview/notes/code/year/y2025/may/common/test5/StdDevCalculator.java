package com.interview.notes.code.year.y2025.may.common.test5;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class StdDevCalculator {

    /**
     * Compute population standard deviation of the list.
     * Returns 0 for size < 2.
     */
    public static double computeStdDev(List<Double> data) {
        int n = data.size();
        if (n < 2) return 0.0;
        double mean = data.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
        double variance = data.stream()
                .mapToDouble(d -> (d - mean) * (d - mean))
                .sum() / n;
        return Math.sqrt(variance);
    }

    /**
     * Simple test helper: prints PASS/FAIL
     */
    private static void test(String name, List<Double> input, double expected) {
        double actual = computeStdDev(input);
        double eps = 1e-6;
        if (Math.abs(actual - expected) <= eps) {
            System.out.println(name + ": PASS");
        } else {
            System.out.printf("%s: FAIL (expected=%.6f, actual=%.6f)%n",
                    name, expected, actual);
        }
    }

    public static void main(String[] args) throws Exception {
        // 1. Known small test cases
        test("Test1 [1,2,3,4,5]", Arrays.asList(1., 2., 3., 4., 5.), 1.41421356);
        test("Test2 [2,2,2,2]", Arrays.asList(2., 2., 2., 2.), 0.0);

        // 2. Large random data test (no assertion)
        List<Double> randomData = new Random()
                .doubles(1_000, 0, 100)
                .boxed()
                .collect(Collectors.toList());
        System.out.println("Random 1000 run σ = " + computeStdDev(randomData));

        // 3. Real CSV file (pass path as first arg or adjust here)
        String path = args.length > 0 ? args[0] : "nasdaq_data.txt";
        List<Double> closePrices = Files.lines(Paths.get(path))
                .skip(1)
                .map(line -> line.split("\\s+|,")[4])
                .map(Double::parseDouble)
                .collect(Collectors.toList());
        System.out.println("CSV σ = " + computeStdDev(closePrices));
    }
}