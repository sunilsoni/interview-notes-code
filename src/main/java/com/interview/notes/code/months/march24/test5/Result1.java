package com.interview.notes.code.months.march24.test5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Result1 {
    public static String getLargestNumber1(String num) {
        // Separate digits into odd and even groups
        StringBuilder oddDigits = new StringBuilder();
        StringBuilder evenDigits = new StringBuilder();

        // Iterate through each digit in the input number
        for (char digit : num.toCharArray()) {
            if ((digit - '0') % 2 == 0) {
                evenDigits.append(digit);
            } else {
                oddDigits.append(digit);
            }
        }

        // Sort each group in descending order
        char[] oddArray = oddDigits.toString().toCharArray();
        java.util.Arrays.sort(oddArray);
        char[] evenArray = evenDigits.toString().toCharArray();
        java.util.Arrays.sort(evenArray);

        // Reverse the arrays to get descending order
        oddDigits = new StringBuilder(new String(oddArray)).reverse();
        evenDigits = new StringBuilder(new String(evenArray)).reverse();

        // Merge the two groups, maintaining the original order of parity
        StringBuilder largestNumber = new StringBuilder();
        int oddIndex = 0, evenIndex = 0;

        for (char digit : num.toCharArray()) {
            if ((digit - '0') % 2 == 0) {
                largestNumber.append(evenDigits.charAt(evenIndex++));
            } else {
                largestNumber.append(oddDigits.charAt(oddIndex++));
            }
        }

        return largestNumber.toString();
    }

    public static String getLargestNumber3(String num) {
        // Lists to hold the even and odd digits
        ArrayList<Character> evenDigits = new ArrayList<>();
        ArrayList<Character> oddDigits = new ArrayList<>();

        // Iterate through each digit in the input number and add to respective list
        for (char digit : num.toCharArray()) {
            if ((digit - '0') % 2 == 0) {
                evenDigits.add(digit);
            } else {
                oddDigits.add(digit);
            }
        }

        // Sort each list in reverse order to get the largest digits first
        Collections.sort(evenDigits, Collections.reverseOrder());
        Collections.sort(oddDigits, Collections.reverseOrder());

        // StringBuilder to construct the largest number
        StringBuilder largestNumber = new StringBuilder();
        int evenIndex = 0, oddIndex = 0;

        // Iterate through the original number and construct the largest number
        for (char digit : num.toCharArray()) {
            if ((digit - '0') % 2 == 0) {
                largestNumber.append(evenDigits.get(evenIndex++));
            } else {
                largestNumber.append(oddDigits.get(oddIndex++));
            }
        }

        return largestNumber.toString();
    }

    public static String getLargestNumber(String num) {
        char[] chars = num.toCharArray();
        List<Character> even = new ArrayList<>();
        List<Character> odd = new ArrayList<>();

        // Separate even and odd digits
        for (char c : chars) {
            if ((c - '0') % 2 == 0) {
                even.add(c);
            } else {
                odd.add(c);
            }
        }

        // Sort even and odd digits in descending order
        even.sort((a, b) -> b - a);
        odd.sort((a, b) -> b - a);

        StringBuilder result = new StringBuilder();
        int evenIndex = 0, oddIndex = 0;

        // Merge sorted even and odd digits
        while (evenIndex < even.size() || oddIndex < odd.size()) {
            if (oddIndex < odd.size()) {
                result.append(odd.get(oddIndex++));
            }
            if (evenIndex < even.size()) {
                result.append(even.get(evenIndex++));
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String num = "0082663";
        System.out.println(getLargestNumber(num)); // Output should be "9758601"

        num = "1806579";
        System.out.println(getLargestNumber(num)); // Output should be "1860975"


        num = "0082663";
        System.out.println(getLargestNumber(num)); // Output should be "9758601"

        num = "0082663";
        System.out.println(getLargestNumber(num)); // Output should be "9758601"


    }
}
