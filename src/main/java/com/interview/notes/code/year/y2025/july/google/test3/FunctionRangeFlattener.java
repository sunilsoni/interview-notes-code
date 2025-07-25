package com.interview.notes.code.year.y2025.july.google.test3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FunctionRangeFlattener {
    // Main method to flatten nested function ranges
    public static List<Function> flattenRanges(List<Function> functions) {
        // Sort by start address, if equal then by depth (higher depth first)
        functions.sort((a, b) -> {
            if (a.start != b.start) return a.start - b.start;
            return b.depth - a.depth;  // Higher depth comes first
        });

        // Result list to store flattened ranges
        List<Function> result = new ArrayList<>();

        // Process each function
        int currentPos = 0;
        while (currentPos < functions.size()) {
            Function current = functions.get(currentPos);

            // Check if this function is overridden by any deeper nested function
            boolean isOverridden = false;
            for (int i = currentPos + 1; i < functions.size(); i++) {
                Function next = functions.get(i);
                if (next.start <= current.end && next.depth > current.depth) {
                    // Add partial range before the override
                    if (current.start < next.start) {
                        result.add(new Function(current.name,
                                current.start, next.start, current.depth));
                    }
                    current.start = next.end;
                    isOverridden = true;
                }
            }

            // Add remaining range if not completely overridden
            if (!isOverridden || current.start < current.end) {
                result.add(current);
            }
            currentPos++;
        }

        return result;
    }

    public static void main(String[] args) {
        runAllTests();
    }

    private static void runAllTests() {
        // Test 1: Basic sequential ranges
        List<Function> test1 = Arrays.asList(
                new Function("foo", 1, 25, 1),
                new Function("bar", 25, 35, 1),
                new Function("baz", 35, 45, 1),
                new Function("bar", 45, 75, 1),
                new Function("foo", 75, 100, 1)
        );

        // Test 2: Nested ranges with overrides
        List<Function> test2 = Arrays.asList(
                new Function("outer", 1, 100, 1),
                new Function("middle", 25, 75, 2),
                new Function("inner", 40, 60, 3)
        );

        // Test 3: Large data test
        List<Function> test3 = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            test3.add(new Function("func" + i, i * 10, (i + 1) * 10,
                    i % 3 + 1));  // Varying depths
        }

        // Test 4: Overlapping ranges with same depth
        List<Function> test4 = Arrays.asList(
                new Function("f1", 1, 50, 1),
                new Function("f2", 25, 75, 1),
                new Function("f3", 60, 100, 1)
        );

        // Run all tests
        runTest("Test 1 (Sequential ranges)", test1);
        runTest("Test 2 (Nested ranges)", test2);
        runTest("Test 3 (Large data)", test3);
        runTest("Test 4 (Overlapping ranges)", test4);
    }

    private static void runTest(String testName, List<Function> input) {
        System.out.println("\n" + testName + ":");
        System.out.println("Input:");
        input.forEach(System.out::println);

        List<Function> result = flattenRanges(input);

        System.out.println("\nFlattened output:");
        result.forEach(System.out::println);

        // Validate results
        boolean valid = validateResults(result);
        System.out.println("\nTest Result: " + (valid ? "PASS" : "FAIL"));
    }

    private static boolean validateResults(List<Function> result) {
        // Check for overlapping ranges
        for (int i = 0; i < result.size() - 1; i++) {
            if (result.get(i).end > result.get(i + 1).start) {
                return false;
            }
        }
        return true;
    }

    // Class to represent a function with its address range
    static class Function {
        String name;    // Function name
        int start;      // Start address
        int end;        // End address
        int depth;      // Nesting depth (higher means more deeply nested)

        Function(String name, int start, int end, int depth) {
            this.name = name;
            this.start = start;
            this.end = end;
            this.depth = depth;
        }

        @Override
        public String toString() {
            return String.format("%s(): %d - %d", name, start, end);
        }
    }
}
