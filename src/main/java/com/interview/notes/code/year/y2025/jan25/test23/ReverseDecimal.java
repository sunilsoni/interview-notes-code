package com.interview.notes.code.year.y2025.jan25.test23;

//WORKING
public class ReverseDecimal {
    public static void main(String[] args) {
        String[] inputs = {"123456.789", "123456789", ".123", "123.", ""};
        String[] expected = {"987654.321", "987654321", "321.", ".321", ""};

        for (int i = 0; i < inputs.length; i++) {
            String result = reverseDecimal(inputs[i]);
            System.out.println("Input: " + inputs[i]
                    + " | Output: " + result
                    + " | Expected: " + expected[i]
                    + " | " + (result.equals(expected[i]) ? "PASS" : "FAIL"));
        }
    }

    private static String reverseDecimal(String s) {
        if (s == null || s.isEmpty()) return s;
        int dotIndex = s.indexOf('.');
        if (dotIndex < 0) {
            return new StringBuilder(s).reverse().toString();
        }

        int rightCount = s.length() - dotIndex - 1;
        // Remove the decimal
        String withoutDot = s.substring(0, dotIndex) + s.substring(dotIndex + 1);
        // Reverse everything
        String reversed = new StringBuilder(withoutDot).reverse().toString();

        // Insert the decimal so the right side has the same length
        int insertPosition = reversed.length() - rightCount;
        if (insertPosition < 0) insertPosition = 0;          // If decimal was near start
        if (insertPosition > reversed.length()) insertPosition = reversed.length(); // If decimal was near end

        return reversed.substring(0, insertPosition)
                + (reversed.isEmpty() ? "" : ".")
                + (insertPosition < reversed.length() ? reversed.substring(insertPosition) : "");
    }
}
