package com.interview.notes.code.months.april24.test1;


/**
 *
 Next smallest palindrome
 Description
 Write a function that takes an integer as an input and return the next smallest number that is a palindrome.
 A palindrome number is a sequence of digits that reads the same forward and reverse. For example, 121 is a palindrome.
 Example Input: 1000
 Example Output: 1001
 Example Input: 1200
 Example Output: 1221
 */
class NextSmallestPalindrome {

    public static int NextSmallestPalindrome(int n) {
        String numStr = Integer.toString(n);
        int len = numStr.length();
        
        // Get the left half and middle (if exists) of the number
        String leftHalf = numStr.substring(0, len / 2);
        String rightHalf = numStr.substring(len / 2);
        String middle = len % 2 == 1 ? numStr.substring(len / 2, len / 2 + 1) : "";
        
        // Create a palindrome by mirroring the left half and middle (if exists)
        String palindrome = leftHalf + middle + new StringBuilder(leftHalf).reverse().toString();
        
        // If the palindrome is not greater than the original number, increment and construct a new palindrome
        if (Long.parseLong(palindrome) <= n) {
            // Increment the middle digit or the leftHalf if there's no middle
            if (len % 2 == 1) {
                int middleNum = Integer.parseInt(middle) + 1;
                // If the middle digit is less than 10 after increment, construct the new palindrome
                if (middleNum < 10) {
                    palindrome = leftHalf + middleNum + new StringBuilder(leftHalf).reverse().toString();
                } else {
                    // If the middle digit becomes 10 after increment, increment the left half and reset middle to 0
                    long leftNum = Long.parseLong(leftHalf) + 1;
                    leftHalf = Long.toString(leftNum);
                    palindrome = leftHalf + "0" + new StringBuilder(leftHalf).reverse().toString();
                }
            } else {
                long leftNum = Long.parseLong(leftHalf) + 1;
                leftHalf = Long.toString(leftNum);
                palindrome = leftHalf + new StringBuilder(leftHalf).reverse().toString();
            }
        }
        
        // Return the new palindrome as an integer
        return Integer.parseInt(palindrome);
    }

    public static void main(String[] args) {
        // Example inputs
        int exampleInput1 = 1000;
        int exampleInput2 = 1200;

        // Example outputs
        int exampleOutput1 = NextSmallestPalindrome(exampleInput1);
        int exampleOutput2 = NextSmallestPalindrome(exampleInput2);

        // Printing example results
        System.out.println("Next smallest palindrome after " + exampleInput1 + " is " + exampleOutput1);
        System.out.println("Next smallest palindrome after " + exampleInput2 + " is " + exampleOutput2);
    }
}
