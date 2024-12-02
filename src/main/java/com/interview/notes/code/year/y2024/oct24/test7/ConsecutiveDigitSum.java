package com.interview.notes.code.year.y2024.oct24.test7;

public class ConsecutiveDigitSum {
    public static String solution(String number) {
        StringBuilder current = new StringBuilder(number);

        // Flag to determine if we need to continue the process
        boolean hasConsecutive = true;

        // Repeat the operation until no consecutive digits are left
        while (hasConsecutive) {
            StringBuilder next = new StringBuilder();
            hasConsecutive = false;
            int i = 0;

            while (i < current.length()) {
                char ch = current.charAt(i);
                int sum = Character.getNumericValue(ch);
                int count = 1;

                // Sum consecutive equal digits
                while (i + count < current.length() && current.charAt(i + count) == ch) {
                    sum += Character.getNumericValue(ch);
                    count++;
                }

                // Append the sum if there was more than one consecutive digit
                if (count > 1) {
                    next.append(sum);
                    hasConsecutive = true; // This indicates we found consecutive digits
                } else {
                    next.append(ch); // No consecutive digits, append the current character
                }

                i += count; // Move to the next group of digits
            }

            // Update current string for the next iteration
            current = next;
        }

        return current.toString();
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(solution("66644319333"));  // Output: 26328
        System.out.println(solution("0044886"));      // Output: 084
        System.out.println(solution("429201"));       // Output: 429201
    }
}
