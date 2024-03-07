package com.interview.notes.code.months.march24.test3;

class Result1 {

    public static String getLargestNumber1(String num) {
        char[] digits = num.toCharArray();
        // Create arrays for odd and even digit indices
        int[] lastEvenIndex = new int[10];
        int[] lastOddIndex = new int[10];
        for (int i = 0; i < 10; i++) {
            lastEvenIndex[i] = lastOddIndex[i] = -1; // Initialize indices as -1
        }

        // Iterate through digits from end to start
        for (int i = digits.length - 1; i >= 0; i--) {
            int digit = digits[i] - '0';
            // Update the last seen index for the digit's parity
            if (digit % 2 == 0) {
                lastEvenIndex[digit] = i;
            } else {
                lastOddIndex[digit] = i;
            }
        }

        // Now iterate again and find the highest possible swap for each digit
        for (int i = 0; i < digits.length; i++) {
            int digit = digits[i] - '0';
            int[] lastIndexArray = (digit % 2 == 0) ? lastOddIndex : lastEvenIndex;

            // Find a larger digit with the same parity to swap with
            for (int j = 9; j > digit; j--) {
                if (lastIndexArray[j] > i) {
                    // Swap the digits
                    char temp = digits[i];
                    digits[i] = digits[lastIndexArray[j]];
                    digits[lastIndexArray[j]] = temp;
                    return new String(digits);
                }
            }
        }
        return new String(digits);
    }

    public static String getLargestNumber(String num) {
        char[] digits = num.toCharArray();
        // Arrays to track the last indices of even and odd digits.
        int[] lastIndices = new int[10];
        for (int i = 0; i < 10; i++) {
            lastIndices[i] = -1;
        }

        // Track the last occurrence of digits with different parity.
        for (int i = digits.length - 1; i >= 0; i--) {
            lastIndices[digits[i] - '0'] = i;
        }

        // Try to swap the current digit with the largest digit of the same parity
        // which occurs later in the number.
        for (int i = 0; i < digits.length; i++) {
            for (int j = 9; j > digits[i] - '0'; j--) {
                // Check if 'j' has the same parity as the current digit and it appears later in the number.
                if (lastIndices[j] > i && ((digits[i] - '0') % 2 == (j % 2))) {
                    // Swap the digits
                    char temp = digits[i];
                    digits[i] = digits[lastIndices[j]];
                    digits[lastIndices[j]] = temp;
                    return new String(digits);
                }
            }
        }
        return new String(digits);
    }

    public static void main(String[] args) {
        System.out.println(Result1.getLargestNumber("7596801")); // Example 1
        System.out.println(Result1.getLargestNumber("1806579")); // Example 1860795
        System.out.println(Result1.getLargestNumber("0082663")); // Sample Case 0
        System.out.println(Result1.getLargestNumber("5528200")); // Sample Case 1
    }
}
