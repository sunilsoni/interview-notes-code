package com.interview.notes.code.year.y2024.march24.test1;

public class LargestOddNumber {
    public static void main(String[] args) {
        String example1 = "gt12cty65mt1";
        String example2 = "mkf43kdlcmk32klmv123";

        System.out.println("Largest Odd Number for example1: " + solve(example1));
        System.out.println("Largest Odd Number for example2: " + solve(example2));
    }

    public static int solve(String S) {
        int largestOddNumber = -1;
        StringBuilder number = new StringBuilder();
        for (char ch : S.toCharArray()) {
            if (Character.isDigit(ch)) {
                number.append(ch);
            } else {
                largestOddNumber = updateLargestOdd(number.toString(), largestOddNumber);
                number = new StringBuilder();
            }
        }
        largestOddNumber = updateLargestOdd(number.toString(), largestOddNumber);
        return largestOddNumber;
    }

    private static int updateLargestOdd(String numberStr, int largestOdd) {
        if (!numberStr.isEmpty()) {
            int num = Integer.parseInt(numberStr);
            if (num % 2 == 1) {
                largestOdd = Math.max(num, largestOdd);
            }
        }
        return largestOdd;
    }

    public static int solve2(String S) {
        String[] parts = S.split("[a-z]+");
        int maxOdd = -1;
        for (String part : parts) {
            if (!part.isEmpty()) {
                int num = Integer.parseInt(part);
                if (num % 2 != 0 && num > maxOdd) {
                    maxOdd = num;
                }
            }
        }
        return maxOdd;
    }
}
