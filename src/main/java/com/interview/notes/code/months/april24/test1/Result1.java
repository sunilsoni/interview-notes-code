package com.interview.notes.code.months.april24.test1;

class Result1 {

    public static int NextSmallestPalindrome(int n) {
        String numStr = Integer.toString(n);
        int len = numStr.length();
        String leftHalf = numStr.substring(0, len / 2);
        String middle = len % 2 == 1 ? numStr.substring(len / 2, len / 2 + 1) : "";
        String rightHalf = numStr.substring(len / 2 + len % 2);

        // Convert leftHalf to a number and mirror it to form a palindrome.
        String palindrome = leftHalf + middle + new StringBuilder(leftHalf).reverse().toString();

        if (Long.parseLong(palindrome) > n) {
            return Integer.parseInt(palindrome);
        }

        // If the middle exists, increment the middle, otherwise increment the left half.
        if (len % 2 == 1) {
            int middleNum = Integer.parseInt(middle) + 1;
            if (middleNum <= 9) {
                palindrome = leftHalf + middleNum + new StringBuilder(leftHalf).reverse().toString();
            } else {
                // Middle has overflowed, need to increment the left half
                long leftNum = Long.parseLong(leftHalf + "0") + 1;
                String newLeftHalf = Long.toString(leftNum).substring(0, leftHalf.length());
                palindrome = newLeftHalf + "0" + new StringBuilder(newLeftHalf).reverse().toString();
            }
        } else {
            long leftNum = Long.parseLong(leftHalf) + 1;
            String newLeftHalf = Long.toString(leftNum);
            palindrome = newLeftHalf + new StringBuilder(newLeftHalf).reverse().toString();
        }

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
