package com.interview.notes.code.months.july24.test16;

class Solution {
    public String solution(String S) {
        int[] count = new int[10];
        for (char c : S.toCharArray()) {
            count[c - '0']++;
        }

        StringBuilder result = new StringBuilder();
        boolean isOdd = false;
        char middleDigit = '0';

        for (int i = 9; i >= 0; i--) {
            while (count[i] > 1 || (count[i] == 1 && !isOdd)) {
                if (result.length() == 0 && i == 0) {
                    break;
                }
                result.append((char) (i + '0'));
                count[i] -= 2;
                if (count[i] == 1) {
                    isOdd = true;
                    middleDigit = (char) (i + '0');
                }
            }
        }

        if (result.length() == 0 && !isOdd) {
            for (int i = 9; i >= 0; i--) {
                if (count[i] > 0) {
                    return String.valueOf(i);
                }
            }
            return "0";
        }

        String firstHalf = result.toString();

        if (isOdd) {
            result.append(middleDigit);
        }

        result.append(new StringBuilder(firstHalf).reverse());

        return result.toString();
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test cases
        String[] testCases = {
                "39878",    // Example 1
                "00900",    // Example 2
                "0000",     // Example 3
                "54321",    // Example 4
                "8199",     // From the problem description
                "1234567890",
                "999999",
                "1010101",
                "5",
                "100001",
                "9876543210"
        };

        String[] expectedOutputs = {
                "898",
                "9",
                "0",
                "5",
                "989",
                "9889899",
                "999999",
                "91019",
                "5",
                "10001",
                "98789"
        };

        for (int i = 0; i < testCases.length; i++) {
            String result = solution.solution(testCases[i]);
            System.out.println("Input: " + testCases[i]);
            System.out.println("Output: " + result);
            System.out.println("Expected: " + expectedOutputs[i]);
            System.out.println("Correct: " + result.equals(expectedOutputs[i]));
            System.out.println();
        }
    }
}
