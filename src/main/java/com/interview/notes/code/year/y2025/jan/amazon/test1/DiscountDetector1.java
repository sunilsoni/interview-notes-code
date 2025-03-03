package com.interview.notes.code.year.y2025.jan.amazon.test1;

import java.util.*;

/*

In AWS EC2 there is a feature that allows you to configure an EC2 Autoscaling Group to allow launching instances of different instance types and generations. In order to increase adoption of this feature, EC2 has created a set of special discount bundles. Depending on the combination of instance types assigned to your autoscaling group, you could qualify for a discount.

Being customer obsessed, we want to scan users’ autoscaling group configurations and automatically apply the best discount possible. You have been assigned the implementation of this detection process.

For simplicity we’ll define Instance types by two attributes: a type (example: m, c, r) and a generation (example: 4, 5, 6). We’ll ignore size (xlarge, 8xlarge) and special instance types (like “m6g” and “m5a”).

Imagine we start with three discount bundles. In order of best discount to worst one:

- Compute5Plus: Any “m” or “c” instances of generations 5 and above. Example: [m5, m6, c5]
- GeneralCompute456: Exactly the set [m4, m5, m6]
- SameInstanceType: Any generation, but all types are the same. [r3, r5, r6]

As input you will receive a collection of instance types and you should return the name of the best discount bundle that applies to those instances.

Example:

- Input: [m3, m5, m6]
- Output: SameInstanceType

Rationale:

All instances in the input are of the same instance type (“m”) and no other bundle before SameInstanceType applies.

 */
public class DiscountDetector1 {

    // Check Compute5Plus condition
    public static boolean checkCompute5Plus(List<String> instances) {
        for (String inst : instances) {
            if (inst.length() < 2) return false; // invalid instance format
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

    // Check GeneralCompute456 condition
    public static boolean checkGeneralCompute456(List<String> instances) {
        // Create a set from instances
        Set<String> requiredSet = new HashSet<>(Arrays.asList("m4", "m5", "m6"));
        if (instances.size() != requiredSet.size()) {
            return false;
        }
        Set<String> instanceSet = new HashSet<>(instances);
        return instanceSet.equals(requiredSet);
    }

    // Check SameInstanceType condition
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

    // Determine the best discount bundle
    public static String findBestDiscount(List<String> instances) {
        if (checkCompute5Plus(instances)) {
            return "Compute5Plus";
        } else if (checkGeneralCompute456(instances)) {
            return "GeneralCompute456";
        } else if (checkSameInstanceType(instances)) {
            return "SameInstanceType";
        } else {
            return "No Discount";
        }
    }

    public static void main(String[] args) {
        // Define test cases: each test case is a pair of input and expected output.
        List<TestCase> testCases = new ArrayList<>();
        testCases.add(new TestCase(Arrays.asList("m3", "m5", "m6"), "SameInstanceType"));
        testCases.add(new TestCase(Arrays.asList("m5", "m6", "c5"), "Compute5Plus"));
        testCases.add(new TestCase(Arrays.asList("m4", "m5", "m6"), "GeneralCompute456"));
        testCases.add(new TestCase(Arrays.asList("r3", "r5", "r6"), "SameInstanceType"));
        testCases.add(new TestCase(Arrays.asList("r3", "m5"), "No Discount"));
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

        // Additional testing for large data input
        List<String> largeData = new ArrayList<>();
        // Create a large list of "m5" instances
        for (int i = 0; i < 10000; i++) {
            largeData.add("m5");
        }
        // Timing check for largeData processing
        long startTime = System.currentTimeMillis();
        String largeDataResult = findBestDiscount(largeData);
        long endTime = System.currentTimeMillis();
        System.out.println("Large Data Test: " + largeDataResult
                + " processed in " + (endTime - startTime) + " ms");
    }

    // Helper class for test cases
    static class TestCase {
        List<String> instances;
        String expected;

        TestCase(List<String> instances, String expected) {
            this.instances = instances;
            this.expected = expected;
        }
    }
}
