package com.interview.notes.code.year.y2024.april24.test1;

class Result2 {

    // Function to find the next smallest palindrome greater than the input integer 'n'
    public static int NextSmallestPalindrome(int n) {
        // Convert the number to a string for easy manipulation
        String numStr = Integer.toString(n);
        // Get the length of the number
        int len = numStr.length();
        // Get the left half of the number
        String leftHalf = numStr.substring(0, len / 2);
        // Get the middle digit if the number's length is odd, otherwise, it's an empty string
        String middle = len % 2 == 1 ? numStr.substring(len / 2, len / 2 + 1) : "";
        // We don't need the right half, we will recreate it by mirroring the left half

        // Check if simply mirroring the left half will create a palindrome greater than 'n'
        String palindrome = leftHalf + middle + new StringBuilder(leftHalf).reverse();

        // If the newly formed palindrome is greater than 'n', we're done
        if (Long.parseLong(palindrome) > n) {
            return Integer.parseInt(palindrome);
        }

        // If there's a middle digit and simply mirroring didn't work, we need to increment it
        if (len % 2 == 1) {
            int middleNum = Integer.parseInt(middle) + 1; // Increment the middle digit
            // If incrementing the middle digit doesn't cause it to exceed 9, we use it
            if (middleNum <= 9) {
                palindrome = leftHalf + middleNum + new StringBuilder(leftHalf).reverse();
            } else {
                // If the middle digit was '9' and now is '10', we have a carry over situation
                long leftNum = Long.parseLong(leftHalf + "0") + 1; // Treat the middle digit as '0' and carry over the increment
                // Take the appropriate number of digits from the incremented left half (ignore the overflow)
                String newLeftHalf = Long.toString(leftNum).substring(0, leftHalf.length());
                // Build the new palindrome with the incremented left half and '0' as the middle
                palindrome = newLeftHalf + "0" + new StringBuilder(newLeftHalf).reverse();
            }
        } else {
            // If there's no middle digit, we simply increment the left half and mirror it
            long leftNum = Long.parseLong(leftHalf) + 1; // Increment the left half
            // Convert the incremented left half back to a string
            String newLeftHalf = Long.toString(leftNum);
            // Create the new palindrome
            palindrome = newLeftHalf + new StringBuilder(newLeftHalf).reverse();
        }

        // Return the new palindrome as an integer
        return Integer.parseInt(palindrome);
    }

    public static void main(String[] args) {
        // Example input
        int exampleInput1 = 12321;
        // Calculate the next smallest palindrome greater than the example input
        int exampleOutput1 = NextSmallestPalindrome(exampleInput1);
        // Print the result
        System.out.println("Next smallest palindrome after " + exampleInput1 + " is " + exampleOutput1);

        // Another example input
        int exampleInput2 = 1234;
        // Calculate the next smallest palindrome greater than the second example input
        int exampleOutput2 = NextSmallestPalindrome(exampleInput2);
        // Print the result
        System.out.println("Next smallest palindrome after " + exampleInput2 + " is " + exampleOutput2);
    }
}
