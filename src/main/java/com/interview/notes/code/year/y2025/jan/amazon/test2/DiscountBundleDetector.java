package com.interview.notes.code.year.y2025.jan.amazon.test2;

public class DiscountBundleDetector {

    // Helper method to check if instance types match Compute5Plus criteria
    private static boolean isCompute5Plus(String[] instances) {
        for (String instance : instances) {
            char type = instance.charAt(0);
            int generation = Character.getNumericValue(instance.charAt(1));

            // Must be 'm' or 'c' type AND generation 5 or higher
            if ((type != 'm' && type != 'c') || generation < 5) {
                return false;
            }
        }
        return true;
    }

    // Helper method to check if instance types match GeneralCompute456
    private static boolean isGeneralCompute456(String[] instances) {
        // Must have exactly m4, m5, m6
        boolean hasM4 = false, hasM5 = false, hasM6 = false;

        if (instances.length != 3) return false;

        for (String instance : instances) {
            if (instance.equals("m4")) hasM4 = true;
            else if (instance.equals("m5")) hasM5 = true;
            else if (instance.equals("m6")) hasM6 = true;
        }

        return hasM4 && hasM5 && hasM6;
    }

    // Helper method to check if all instances are of same type
    private static boolean isSameInstanceType(String[] instances) {
        if (instances.length == 0) return false;
        char firstType = instances[0].charAt(0);

        for (String instance : instances) {
            if (instance.charAt(0) != firstType) {
                return false;
            }
        }
        return true;
    }

    public static String findBestDiscount(String[] instances) {
        if (instances == null || instances.length == 0) {
            return "No discount applicable";
        }

        // Check bundles in order of best discount to worst
        if (isCompute5Plus(instances)) {
            return "Compute5Plus";
        }
        if (isGeneralCompute456(instances)) {
            return "GeneralCompute456";
        }
        if (isSameInstanceType(instances)) {
            return "SameInstanceType";
        }

        return "No discount applicable";
    }

    public static void main(String[] args) {
        // Test cases
        runTest(new String[]{"m5", "m6", "c5"}, "Compute5Plus", "Basic Compute5Plus test");
        runTest(new String[]{"m4", "m5", "m6"}, "GeneralCompute456", "Basic GeneralCompute456 test");
        runTest(new String[]{"r3", "r5", "r6"}, "SameInstanceType", "Basic SameInstanceType test");
        runTest(new String[]{"m3", "m5", "m6"}, "SameInstanceType", "Given example test");
        runTest(new String[]{"m3", "c4"}, "No discount applicable", "No matching discount test");
        runTest(new String[]{}, "No discount applicable", "Empty input test");
        runTest(null, "No discount applicable", "Null input test");
    }

    private static void runTest(String[] input, String expectedOutput, String testName) {
        String result = findBestDiscount(input);
        boolean passed = result.equals(expectedOutput);

        System.out.println("Test: " + testName);
        System.out.print("Input: ");
        if (input != null) {
            System.out.println(String.join(", ", input));
        } else {
            System.out.println("null");
        }
        System.out.println("Expected: " + expectedOutput);
        System.out.println("Got: " + result);
        System.out.println("Status: " + (passed ? "PASS" : "FAIL"));
        System.out.println();
    }
}
