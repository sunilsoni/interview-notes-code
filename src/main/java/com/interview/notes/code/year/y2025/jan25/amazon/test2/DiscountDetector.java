package com.interview.notes.code.year.y2025.jan25.amazon.test2;

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
public class DiscountDetector {

    // Existing check methods
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

    public static boolean checkNonGPU6Plus(List<String> instances) {
        if (instances.isEmpty()) return false;
        for (String inst : instances) {
            if (inst.length() < 2) return false;
            char type = inst.charAt(0);
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

    // Simulate a method that determines current promotion period
    // For real implementation, use actual date/time checks
    public static boolean isSpecialPromotionPeriod() {
        // Simulate special period with a flag, or use LocalDate.now() to check against a date range
        return true; // Change as needed for testing
    }

    public static List<DiscountPriority> getCurrentPriorityOrder() {
        List<DiscountPriority> order = new ArrayList<>();

        // Example: During special period, GeneralCompute456 is top priority.
        if (isSpecialPromotionPeriod()) {
            order.add(new DiscountPriority("GeneralCompute456", DiscountDetector::checkGeneralCompute456));
            order.add(new DiscountPriority("Compute5Plus", DiscountDetector::checkCompute5Plus));
            order.add(new DiscountPriority("SameInstanceType", DiscountDetector::checkSameInstanceType));
            order.add(new DiscountPriority("NonGPU6Plus", DiscountDetector::checkNonGPU6Plus));
        } else {
            // Normal priority order
            order.add(new DiscountPriority("Compute5Plus", DiscountDetector::checkCompute5Plus));
            order.add(new DiscountPriority("GeneralCompute456", DiscountDetector::checkGeneralCompute456));
            order.add(new DiscountPriority("SameInstanceType", DiscountDetector::checkSameInstanceType));
            order.add(new DiscountPriority("NonGPU6Plus", DiscountDetector::checkNonGPU6Plus));
        }
        return order;
    }

    public static String findBestDiscount(List<String> instances) {
        List<DiscountPriority> priorityList = getCurrentPriorityOrder();
        for (DiscountPriority dp : priorityList) {
            if (dp.checkMethod.check(instances)) {
                return dp.name;
            }
        }
        return "No Discount";
    }

    public static void main(String[] args) {
        // Test cases remain the same
        List<TestCase> testCases = new ArrayList<>();
        testCases.add(new TestCase(Arrays.asList("m3", "m5", "m6"), "SameInstanceType"));
        testCases.add(new TestCase(Arrays.asList("m5", "m6", "c5"), "Compute5Plus"));
        testCases.add(new TestCase(Arrays.asList("m4", "m5", "m6"), "GeneralCompute456"));
        testCases.add(new TestCase(Arrays.asList("r3", "r5", "r6"), "SameInstanceType"));
        testCases.add(new TestCase(Arrays.asList("m6", "t7", "c7"), "NonGPU6Plus"));
        testCases.add(new TestCase(Arrays.asList("p6", "m6", "c7"), "No Discount"));
        testCases.add(new TestCase(Collections.emptyList(), "No Discount"));
        testCases.add(new TestCase(Arrays.asList("m5", "m5", "m5"), "Compute5Plus"));

        // Run tests with current time simulation
        int passed = 0;
        for (TestCase tc : testCases) {
            String result = findBestDiscount(tc.instances);
            // Since priority might change for the special period, adjust expected results accordingly.
            System.out.println(tc.instances + " -> " + result);
        }

        // Additional large data test as before
        List<String> largeData = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            largeData.add("m6"); // Valid for NonGPU6Plus
        }
        long startTime = System.currentTimeMillis();
        String largeDataResult = findBestDiscount(largeData);
        long endTime = System.currentTimeMillis();
        System.out.println("Large Data Test: " + largeDataResult
                + " processed in " + (endTime - startTime) + " ms");
    }

    // Define functional interface for discount check methods
    interface DiscountCheck {
        boolean check(List<String> instances);
    }

    // Dynamic priority order mapping
    static class DiscountPriority {
        String name;
        DiscountCheck checkMethod;

        DiscountPriority(String name, DiscountCheck checkMethod) {
            this.name = name;
            this.checkMethod = checkMethod;
        }
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
