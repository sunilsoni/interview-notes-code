package com.interview.notes.code.year.y2024.jan24.test14;

public class ReverseStringSpecial {
    public static void main(String[] args) {
        String input = "123456.789";
        System.out.println("Input: " + input);
        System.out.println("Output: " + reverseStringKeepingSpecialChars(input));
    }

    private static String reverseStringKeepingSpecialChars(String str) {
        char[] inputArray = str.toCharArray();
        int left = 0;
        int right = str.length() - 1;

        while (left < right) {
            if (!Character.isDigit(inputArray[left])) {
                left++;
            } else if (!Character.isDigit(inputArray[right])) {
                right--;
            } else {
                // Swap the characters
                char temp = inputArray[left];
                inputArray[left] = inputArray[right];
                inputArray[right] = temp;

                left++;
                right--;
            }
        }
        return new String(inputArray);
    }
}
