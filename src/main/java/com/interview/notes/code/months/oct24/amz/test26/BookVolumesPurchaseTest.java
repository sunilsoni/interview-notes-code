package com.interview.notes.code.months.oct24.amz.test26;

import java.util.*;

public class BookVolumesPurchaseTest {

    // Main solution function
    public static List<List<Integer>> buyVolumes(List<Integer> volumes) {
        int n = volumes.size();
        List<List<Integer>> result = new ArrayList<>();
        Set<Integer> purchased = new HashSet<>();

        // Process each day
        for (int i = 0; i < n; i++) {
            int currentVolume = volumes.get(i);
            List<Integer> todaysPurchase = new ArrayList<>();

            // Check if we can purchase the current volume and any dependent volumes
            boolean canPurchase = true;
            for (int j = 1; j < currentVolume; j++) {
                if (!purchased.contains(j)) {
                    canPurchase = false;
                    break;
                }
            }

            if (canPurchase && !purchased.contains(currentVolume)) {
                todaysPurchase.add(currentVolume);
                purchased.add(currentVolume);

                // Check if we can now purchase any later volumes
                for (int j = 0; j < i; j++) {
                    int prevVolume = volumes.get(j);
                    if (!purchased.contains(prevVolume)) {
                        boolean canPurchasePrev = true;
                        for (int k = 1; k < prevVolume; k++) {
                            if (!purchased.contains(k)) {
                                canPurchasePrev = false;
                                break;
                            }
                        }
                        if (canPurchasePrev) {
                            todaysPurchase.add(prevVolume);
                            purchased.add(prevVolume);
                        }
                    }
                }
            }

            if (todaysPurchase.isEmpty()) {
                result.add(Arrays.asList(-1));
            } else {
                Collections.sort(todaysPurchase);
                result.add(todaysPurchase);
            }
        }

        return result;
    }

    // Test method
    public static void runTest(List<Integer> volumes, List<List<Integer>> expected) {
        List<List<Integer>> result = buyVolumes(volumes);
        boolean passed = result.equals(expected);
        System.out.println("Test case with volumes " + volumes);
        System.out.println("Expected: " + expected);
        System.out.println("Got: " + result);
        System.out.println("Test " + (passed ? "PASSED" : "FAILED"));
        System.out.println();
    }

    public static void main(String[] args) {
        // Test Case 1 - Sample case from problem
        List<Integer> volumes1 = Arrays.asList(1, 4, 3, 2, 5);
        List<List<Integer>> expected1 = Arrays.asList(
                Arrays.asList(1),
                Arrays.asList(-1),
                Arrays.asList(-1),
                Arrays.asList(2, 3, 4),
                Arrays.asList(5)
        );
        runTest(volumes1, expected1);

        // Test Case 2 - Sequential volumes
        List<Integer> volumes2 = Arrays.asList(1, 2, 3);
        List<List<Integer>> expected2 = Arrays.asList(
                Arrays.asList(1),
                Arrays.asList(2),
                Arrays.asList(3)
        );
        runTest(volumes2, expected2);

        // Test Case 3 - Large input test
        List<Integer> volumes3 = new ArrayList<>();
        List<List<Integer>> expected3 = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            volumes3.add(i);
            expected3.add(Arrays.asList(i));
        }
        runTest(volumes3, expected3);

        // Test Case 4 - Reverse order
        List<Integer> volumes4 = Arrays.asList(3, 2, 1);
        List<List<Integer>> expected4 = Arrays.asList(
                Arrays.asList(-1),
                Arrays.asList(-1),
                Arrays.asList(1, 2, 3)
        );
        runTest(volumes4, expected4);
    }
}