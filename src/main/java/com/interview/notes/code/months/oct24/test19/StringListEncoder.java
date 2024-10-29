package com.interview.notes.code.months.oct24.test19;

public class StringListEncoder {
    private static final String DELIMITER = "#";
    private static final String LENGTH_SEPARATOR = ":";

    public static String encode(String[] strings) {
        if (strings == null || strings.length == 0) {
            return "";
        }

        StringBuilder encoded = new StringBuilder();
        for (String str : strings) {
            // Format: length:string#
            encoded.append(str.length())
                    .append(LENGTH_SEPARATOR)
                    .append(str)
                    .append(DELIMITER);
        }
        return encoded.toString();
    }

    public static String[] decode(String encoded) {
        if (encoded == null || encoded.isEmpty()) {
            return new String[0];
        }

        java.util.List<String> result = new java.util.ArrayList<>();
        int i = 0;

        while (i < encoded.length()) {
            int lengthEnd = encoded.indexOf(LENGTH_SEPARATOR, i);
            if (lengthEnd == -1) break;

            int length = Integer.parseInt(encoded.substring(i, lengthEnd));
            int stringStart = lengthEnd + 1;
            String str = encoded.substring(stringStart, stringStart + length);
            result.add(str);

            i = stringStart + length + 1; // Skip the delimiter
        }

        return result.toArray(new String[0]);
    }

    private static void runTest(String testName, String[] input) {
        try {
            String encoded = encode(input);
            String[] decoded = decode(encoded);

            boolean passed = java.util.Arrays.equals(input, decoded);

            System.out.println(testName + ": " + (passed ? "PASS" : "FAIL"));
            if (!passed) {
                System.out.println("  Expected: " + java.util.Arrays.toString(input));
                System.out.println("  Got: " + java.util.Arrays.toString(decoded));
            }
        } catch (Exception e) {
            System.out.println(testName + ": FAIL (Exception: " + e.getMessage() + ")");
        }
    }

    public static void main(String[] args) {
        // Basic test cases
        runTest("Empty array", new String[]{});
        runTest("Single string", new String[]{"hello"});
        runTest("Multiple strings", new String[]{"hello", "world", "test"});
        runTest("Strings with special chars",
                new String[]{"hello#world", "test:123", "special@chars"});

        // Edge cases
        runTest("Null input", null);
        runTest("Empty string", new String[]{""});
        runTest("Very long string", new String[]{new String(new char[10000]).replace('\0', 'a')});

        // Large data test
        String[] largeInput = new String[1000];
        for (int i = 0; i < largeInput.length; i++) {
            largeInput[i] = "string" + i;
        }
        runTest("Large input", largeInput);
    }
}