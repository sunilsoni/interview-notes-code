package com.interview.notes.code.year.y2025.jan25.test23;

public class ReverseDecimalString {
    public static void main(String[] args) {
        String[] inputs = {"123456.789", "987.654321", "123.", ".456"};
        String[] expected = {"987654.321", "123.456789", ".321", "654."};

        for (int i = 0; i < inputs.length; i++) {
            String result = reverseDecimal(inputs[i]);
            System.out.println(inputs[i] + " -> " + result
                    + " | " + (result.equals(expected[i]) ? "PASS" : "FAIL"));
        }
    }

    public static String reverseDecimal(String input) {
        if (!input.contains(".")) return new StringBuilder(input).reverse().toString();
        String[] parts = input.split("\\.", -1); // split keeping empty parts
        String left = new StringBuilder(parts[0]).reverse().toString();
        String right = (parts.length > 1) ? new StringBuilder(parts[1]).reverse().toString() : "";
        return left + "." + right;
    }
}
