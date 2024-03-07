package com.interview.notes.code.months.march24.test3;

class Result {

    public static String getLargestNumber1(String num) {
        char[] digits = num.toCharArray();
        // Arrays to store the last occurrence index of odd and even digits
        int[] lastOddIndex = new int[10];
        int[] lastEvenIndex = new int[10];
        for (int i = 0; i < 10; i++) {
            lastOddIndex[i] = lastEvenIndex[i] = -1; // Initialize with -1
        }

        // Find the last indices of odd and even digits
        for (int i = digits.length - 1; i >= 0; i--) {
            if ((digits[i] - '0') % 2 == 0) {
                lastEvenIndex[digits[i] - '0'] = i;
            } else {
                lastOddIndex[digits[i] - '0'] = i;
            }
        }

        // Loop through each digit and find the largest digit of the same parity that can be swapped
        for (int i = 0; i < digits.length; i++) {
            int digit = digits[i] - '0';
            boolean isDigitEven = digit % 2 == 0;
            for (int j = 9; j > digit; j--) {
                // Skip the digits not of the same parity
                if (isDigitEven == (j % 2 == 0)) {
                    // For even digits, check against lastOddIndex, and vice versa
                    int indexToCheck = isDigitEven ? lastOddIndex[j] : lastEvenIndex[j];
                    // If a larger digit of the same parity is found later in the number, swap them
                    if (indexToCheck > i) {
                        char temp = digits[i];
                        digits[i] = digits[indexToCheck];
                        digits[indexToCheck] = temp;
                        // Break because we only want the first swap that increases the number's value
                        return new String(digits);
                    }
                }
            }
        }
        return new String(digits);
    }


    public static String getLargestNumber3(String num) {
        char[] characters = num.toCharArray();
        int[] lastIndices = new int[10];
        for (int i = 0; i < characters.length; i++) {
            lastIndices[characters[i] - '0'] = i;
        }
        for (int i = 0; i < characters.length; i++) {
            for (int d = 9; d > characters[i] - '0'; d--) {
                if (lastIndices[d] > i && ((characters[i] - '0') % 2 == (d % 2))) {
                    char temp = characters[i];
                    characters[i] = characters[lastIndices[d]];
                    characters[lastIndices[d]] = temp;
                    return new String(characters);
                }
            }
        }
        return num;
    }

    public static void main(String[] args) {
        System.out.println(Result.getLargestNumber("0082663")); // Test Case 1
        System.out.println(Result.getLargestNumber("1806579")); // Test Case 2
    }

    public static String getLargestNumber4(String num) {
        char[] chars = num.toCharArray();
        int[] last = new int[10];
        for (int i = 0; i < chars.length; i++) {
            last[chars[i] - '0'] = i;
        }
        for (int i = 0; i < chars.length; i++) {
            for (int j = 9; j > chars[i] - '0'; j--) {
                if (last[j] > i && (chars[i] - '0') % 2 == (j % 2)) {
                    char temp = chars[i];
                    chars[i] = chars[last[j]];
                    chars[last[j]] = temp;
                    return new String(chars);
                }
            }
        }
        return new String(chars);
    }

    public static String getLargestNumber5(String num) {
        char[] characters = num.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            if ((characters[i] - '0') % 2 == 0) {
                for (int j = characters.length - 1; j > i; j--) {
                    if ((characters[j] - '0') % 2 == 0 && characters[j] > characters[i]) {
                        char temp = characters[i];
                        characters[i] = characters[j];
                        characters[j] = temp;
                        break;
                    }
                }
            } else {
                for (int j = characters.length - 1; j > i; j--) {
                    if ((characters[j] - '0') % 2 != 0 && characters[j] > characters[i]) {
                        char temp = characters[i];
                        characters[i] = characters[j];
                        characters[j] = temp;
                        break;
                    }
                }
            }
        }
        return new String(characters);
    }

    public static String getLargestNumber6(String num) {
        char[] chars = num.toCharArray();
        // Create buckets for the last occurrence of digits 0-9
        int[] last = new int[10];
        for (int i = 0; i < chars.length; i++) {
            last[chars[i] - '0'] = i;
        }
        // Traverse the number from left to right
        for (int i = 0; i < chars.length; i++) {
            // Check from 9 to current digit if a swap is possible
            for (int j = 9; j > chars[i] - '0'; j--) {
                // If last index of j is greater than i and the parity of i and j is the same
                if (last[j] > i && ((chars[i] - '0') % 2 == (j % 2))) {
                    // Swap the characters
                    char temp = chars[i];
                    chars[i] = chars[last[j]];
                    chars[last[j]] = temp;
                    // Return the new number as a string after swap
                    return new String(chars);
                }
            }
        }
        // Return the original number if no swaps are possible
        return new String(chars);
    }

    public static String getLargestNumber(String num) {
        char[] digits = num.toCharArray();
        for (int i = 0; i < digits.length - 1; i++) {
            for (int j = i + 1; j < digits.length; j++) {
                if ((digits[i] - '0') % 2 == (digits[j] - '0') % 2) {
                    char temp = digits[i];
                    digits[i] = digits[j];
                    digits[j] = temp;
                }
            }
        }
        return new String(digits);
    }
}