package com.interview.notes.code.months.oct24.codereport.test3;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Consumer;
import java.util.concurrent.atomic.AtomicInteger;

public class ListMergerAlternative {
    public static void main(String[] args) {
        runAllTests();
    }

    // Main merge logic using sorting and manual deduplication
    public static int[] mergeOrderedLists(int[] list1, int[] list2) {
        if (list1 == null || list1.length == 0) {
            return removeDuplicates(list2 != null ? list2 : new int[0]);
        }
        if (list2 == null || list2.length == 0) {
            return removeDuplicates(list1);
        }

        int[] merged = new int[list1.length + list2.length];
        int i = 0, j = 0, k = 0;
        
        Arrays.sort(list1);
        Arrays.sort(list2);
        
        while (i < list1.length && j < list2.length) {
            if (list1[i] < list2[j]) {
                if (k == 0 || merged[k - 1] != list1[i]) {
                    merged[k++] = list1[i];
                }
                i++;
            } else if (list1[i] > list2[j]) {
                if (k == 0 || merged[k - 1] != list2[j]) {
                    merged[k++] = list2[j];
                }
                j++;
            } else {
                if (k == 0 || merged[k - 1] != list1[i]) {
                    merged[k++] = list1[i];
                }
                i++;
                j++;
            }
        }
        
        while (i < list1.length) {
            if (k == 0 || merged[k - 1] != list1[i]) {
                merged[k++] = list1[i];
            }
            i++;
        }
        
        while (j < list2.length) {
            if (k == 0 || merged[k - 1] != list2[j]) {
                merged[k++] = list2[j];
            }
            j++;
        }

        return Arrays.copyOf(merged, k);
    }

    private static int[] removeDuplicates(int[] arr) {
        if (arr == null || arr.length == 0) return new int[0];
        
        Arrays.sort(arr);
        int j = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[j] != arr[i]) {
                arr[++j] = arr[i];
            }
        }
        return Arrays.copyOf(arr, j + 1);
    }

    private static void runAllTests() {
        AtomicInteger testsPassed = new AtomicInteger(0);
        AtomicInteger totalTests = new AtomicInteger(0);

        // Test 1: Basic test
        totalTests.incrementAndGet();
        testCase(
            new int[]{1, 4, 2, 5},
            new int[]{7, 9, 0, 8},
            new int[]{0, 1, 2, 4, 5, 7, 8, 9},
            "Basic Test",
            passed -> {
                if (passed) testsPassed.incrementAndGet();
            });

        // Test 2: Duplicates
        totalTests.incrementAndGet();
        testCase(
            new int[]{1, 1, 2, 2, 3},
            new int[]{2, 3, 3, 4, 4},
            new int[]{1, 2, 3, 4},
            "Duplicate Numbers",
            passed -> {
                if (passed) testsPassed.incrementAndGet();
            });

        // Test 3: Empty arrays
        totalTests.incrementAndGet();
        testCase(
            new int[]{},
            new int[]{},
            new int[]{},
            "Empty Arrays",
            passed -> {
                if (passed) testsPassed.incrementAndGet();
            });

        // Test 4: One empty array
        totalTests.incrementAndGet();
        testCase(
            new int[]{1, 2, 3},
            new int[]{},
            new int[]{1, 2, 3},
            "One Empty Array",
            passed -> {
                if (passed) testsPassed.incrementAndGet();
            });

        // Test 5: Large arrays
        totalTests.incrementAndGet();
        int[] largeArray1 = generateLargeArray(50000);
        int[] largeArray2 = generateLargeArray(50000);
        int[] expectedLarge = getExpectedResult(largeArray1, largeArray2);
        testCase(
            largeArray1,
            largeArray2,
            expectedLarge,
            "Large Arrays",
            passed -> {
                if (passed) testsPassed.incrementAndGet();
            });

        // Print final results
        System.out.println("\nTest Summary:");
        System.out.printf("Passed: %d/%d tests%n", 
            testsPassed.get(), totalTests.get());
        System.out.println("Overall Result: " + 
            (testsPassed.get() == totalTests.get() ? "PASS" : "FAIL"));
    }

    private static void testCase(int[] list1, int[] list2, int[] expected, 
                               String testName, Consumer<Boolean> resultCollector) {
        System.out.println("\nExecuting: " + testName);
        long startTime = System.nanoTime();
        
        int[] result = mergeOrderedLists(list1, list2);
        long endTime = System.nanoTime();
        
        boolean passed = Arrays.equals(result, expected);
        resultCollector.accept(passed);
        
        System.out.printf("Status: %s%n", passed ? "PASS" : "FAIL");
        System.out.printf("Time taken: %.2f ms%n", 
            (endTime - startTime) / 1_000_000.0);
        
        if (!passed) {
            System.out.println("Expected: " + Arrays.toString(expected));
            System.out.println("Got: " + Arrays.toString(result));
        }
    }

    private static int[] generateLargeArray(int size) {
        Random rand = new Random();
        return rand.ints(size).toArray();
    }

    private static int[] getExpectedResult(int[] arr1, int[] arr2) {
        return Arrays.stream(concat(arr1, arr2))
            .distinct()
            .sorted()
            .toArray();
    }

    private static int[] concat(int[] arr1, int[] arr2) {
        int[] result = Arrays.copyOf(arr1, arr1.length + arr2.length);
        System.arraycopy(arr2, 0, result, arr1.length, arr2.length);
        return result;
    }
}