package com.interview.notes.code.months.july24.test17;

public class MainFinal {
    public static void main(String[] args) {
        LargestValuePalindromicNumber solution = new LargestValuePalindromicNumber();

        String[] testCases = {
                "39878", "00900", "0000", "54321", "8199",
                "1234567890", "999999", "1010101", "5", "100001", "9876543210"
        };

        String[] expectedOutputs = {
                "898", "9", "0", "5", "989",
                "9889899", "999999", "91019", "5", "10001", "98789"
        };

        for (int i = 0; i < testCases.length; i++) {
            String result = solution.solution(testCases[i]);
            System.out.println("Input: " + testCases[i]);
            System.out.println("Output: " + result);
            System.out.println("Expected: " + expectedOutputs[i]);
            System.out.println("Correct: " + result.equals(expectedOutputs[i]));
            System.out.println();
        }

        // Additional test cases
        System.out.println("Additional test cases:");
        System.out.println("Input: '39878'");
        System.out.println("Output: " + solution.solution("39878"));
        System.out.println("Expected: 898");
        System.out.println();

        System.out.println("Input: '00900'");
        System.out.println("Output: " + solution.solution("00900"));
        System.out.println("Expected: 9");
        System.out.println();

        System.out.println("Input: '0000'");
        System.out.println("Output: " + solution.solution("0000"));
        System.out.println("Expected: 0");
    }
}
