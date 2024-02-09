package com.interview.notes.code.months.jan24.test8;

public class LargeNumberSum {
    // Method to find the sum of two large numbers represented as strings
    public static String findSum(String str1, String str2) {
        // Making sure str2 is the longer string
        if (str1.length() > str2.length()) {
            String temp = str1;
            str1 = str2;
            str2 = temp;
        }

        StringBuilder result = new StringBuilder();
        int n1 = str1.length(), n2 = str2.length();

        // Reverse both the strings
        str1 = new StringBuilder(str1).reverse().toString();
        str2 = new StringBuilder(str2).reverse().toString();

        int carry = 0;
        for (int i = 0; i < n1; i++) {
            // Sum the digits including carry
            int sum = ((int) (str1.charAt(i) - '0') + (int) (str2.charAt(i) - '0') + carry);
            result.append((char) (sum % 10 + '0'));

            // Calculate carry for the next step
            carry = sum / 10;
        }

        // Add remaining digits of the longer number
        for (int i = n1; i < n2; i++) {
            int sum = ((int) (str2.charAt(i) - '0') + carry);
            result.append((char) (sum % 10 + '0'));
            carry = sum / 10;
        }

        // Add remaining carry
        if (carry > 0) {
            result.append((char) (carry + '0'));
        }

        // Reverse the result string and return it
        return result.reverse().toString();
    }

    // Main method to test the functionality
    public static void main(String[] args) {
        String str1 = "12345678901234567890";
        String str2 = "98765432109876543210";
        System.out.println("Sum: " + findSum(str1, str2));
    }
}
