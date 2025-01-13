package com.interview.notes.code.year.y2025.jan24.amazon.test1;

import java.util.*;

public class DiscountDetector {

    // Existing check methods...

    public static boolean checkCompute5Plus(List<String> instances) {
        for (String inst : instances) {
            if (inst.length() < 2) return false;
            char type = inst.charAt(0);
            int generation;
            try {
                generation = Integer.parseInt(inst.substring(1));
            } catch (NumberFormatException e) {
                return false;
            }
            if (!((type == 'm' || type == 'c') && generation >= 5)) {
                return false;
            }
        }
        return !instances.isEmpty();
    }

    public static boolean checkGeneralCompute456(List<String> instances) {
        Set<String> requiredSet = new HashSet<>(Arrays.asList("m4", "m5", "m6"));
        if (instances.size() != requiredSet.size()) {
            return false;
        }
        Set<String> instanceSet = new HashSet<>(instances);
        return instanceSet.equals(requiredSet);
    }

    public static boolean checkSameInstanceType(List<String> instances) {
        if (instances.isEmpty()) return false;
        char firstType = instances.get(0).charAt(0);
        for (String inst : instances) {
            if (inst.charAt(0) != firstType) {
                return false;
            }
        }
        return true;
    }

    // New check method for NonGPU6Plus
    public static boolean checkNonGPU6Plus(List<String> instances) {
        if (instances.isEmpty()) return false;
        for (String inst : instances) {
            if (inst.length() < 2) return false;
            char type = inst.charAt(0);
            // Exclude "p" and "g"
            if (type == 'p' || type == 'g') return false;
            int generation;
            try {
                generation = Integer.parseInt(inst.substring(1));
            } catch (NumberFormatException e) {
                return false;
            }
            if (generation < 6) return false;
        }
        return true;
    }

    // Updated method to determine the best discount bundle
    public static String findBestDiscount(List<String> instances) {
        if (checkCompute5Plus(instances)) {
            return "Compute5Plus";
        } else if (checkGeneralCompute456(instances)) {
            return "GeneralCompute456";
        } else if (checkSameInstanceType(instances)) {
            return "SameInstanceType";
        } else if (checkNonGPU6Plus(instances)) {
            return "NonGPU6Plus";
        } else {
            return "No Discount";
        }
    }

    public static void main(String[] args) {
        // Define test cases including ones for NonGPU6Plus
        List<TestCase> testCases = new ArrayList<>();
        testCases.add(new TestCase(Arrays.asList("m3", "m5", "m6"), "SameInstanceType"));
        testCases.add(new TestCase(Arrays.asList("m5", "m6", "c5"), "Compute5Plus"));
        testCases.add(new TestCase(Arrays.asList("m4", "m5", "m6"), "GeneralCompute456"));
        testCases.add(new TestCase(Arrays.asList("r3", "r5", "r6"), "SameInstanceType"));
        testCases.add(new TestCase(Arrays.asList("m6", "t7", "c7"), "NonGPU6Plus"));  // New test case
        testCases.add(new TestCase(Arrays.asList("p6", "m6", "c7"), "No Discount"));  // Contains "p"
        testCases.add(new TestCase(Collections.emptyList(), "No Discount"));
        testCases.add(new TestCase(Arrays.asList("m5", "m5", "m5"), "Compute5Plus"));

        int passed = 0;
        for (TestCase tc : testCases) {
            String result = findBestDiscount(tc.instances);
            if (result.equals(tc.expected)) {
                System.out.println("PASS: " + tc.instances + " -> " + result);
                passed++;
            } else {
                System.out.println("FAIL: " + tc.instances + " -> Expected: "
                        + tc.expected + ", Got: " + result);
            }
        }
        System.out.println(passed + "/" + testCases.size() + " test cases passed.");

        // Large data test remains the same
        List<String> largeData = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            largeData.add("m6");  // All are valid NonGPU6Plus
        }
        long startTime = System.currentTimeMillis();
        String largeDataResult = findBestDiscount(largeData);
        long endTime = System.currentTimeMillis();
        System.out.println("Large Data Test: " + largeDataResult
                + " processed in " + (endTime - startTime) + " ms");
    }

    static class TestCase {
        List<String> instances;
        String expected;

        TestCase(List<String> instances, String expected) {
            this.instances = instances;
            this.expected = expected;
        }
    }
}
