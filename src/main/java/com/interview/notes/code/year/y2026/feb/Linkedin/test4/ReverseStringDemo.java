package com.interview.notes.code.year.y2026.feb.Linkedin.test4;

public class ReverseStringDemo { // Main class containing solution and tests

    private static void test(ReverseStringDemo s, String in, String exp, String name) { // Helper method to run one test case
        String out = s.reverse(in); // Call reverse method and store result
        boolean ok = (out == null && exp == null) || (out != null && out.equals(exp)); // Safely compare output with expected value
        System.out.println((ok ? "PASS" : "FAIL") + " | " + name + " | input=" + in + " | output=" + out); // Print PASS/FAIL result
    }

    public static void main(String[] args) { // Main method to run all tests
        ReverseStringDemo s = new ReverseStringDemo(); // Create object to access reverse method

        test(s, "hello", "olleh", "Basic"); // Test normal string
        test(s, "", "", "Empty"); // Test empty string
        test(s, "a", "a", "Single char"); // Test single character
        test(s, "madam", "madam", "Palindrome"); // Test palindrome string
        test(s, "Java 21", "12 avaJ", "Space + digits"); // Test string with space and digits
        test(s, null, null, "Null"); // Test null input
        test(s, "A!b@C", "C@b!A", "Special chars"); // Test special characters

        int n = 1_000_000; // Define large input size (10 lakh chars)
        String big = "x".repeat(n); // Create large string for performance/size test
        String rev = s.reverse(big); // Reverse the large string
        boolean bigOk = rev != null && rev.length() == n && rev.charAt(0) == 'x' && rev.charAt(n - 1) == 'x'; // Validate large result
        System.out.println((bigOk ? "PASS" : "FAIL") + " | Large input (" + n + " chars)"); // Print large test result
    }

    public String reverse(String str) { // Method to reverse a string using custom logic
        if (str == null) return null; // Return null if input is null to avoid errors

        char[] arr = str.toCharArray(); // Convert string to char array so we can swap characters
        int left = 0; // Left pointer starts from beginning
        int right = arr.length - 1; // Right pointer starts from end

        while (left < right) { // Continue swapping until pointers meet/cross
            char temp = arr[left]; // Store left character temporarily
            arr[left] = arr[right]; // Put right character at left position
            arr[right] = temp; // Put saved left character at right position
            left++; // Move left pointer forward
            right--; // Move right pointer backward
        }

        return new String(arr); // Create and return reversed string from char array
    }
}