package com.interview.notes.code.months.july24.test3;

public class OutcomeWorking2 {
    public static String solve(int n, String s) {
        // Define the vowels to be removed
        String vowels = "aeiouAEIOU";
        StringBuilder result = new StringBuilder();

        // Iterate over each character in the string
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            // Append only non-vowel characters
            if (vowels.indexOf(c) == -1) {
                result.append(c);
            }
        }

        // Convert StringBuilder to String and trim any leading/trailing spaces
        return result.toString().trim();
    }

    public static void main(String[] args) {
        // Example 1
        String input1 = "E Hello";
        System.out.println(solve(input1.length(), input1));  // Output: " Hll"

        // Example 2
        String input2 = "Get over here.";
        System.out.println(solve(input2.length(), input2));  // Output: "Gt vr hr."
    }
}
