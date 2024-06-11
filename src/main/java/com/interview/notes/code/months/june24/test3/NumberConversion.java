package com.interview.notes.code.months.june24.test3;

/**
 *
 Number Conversion
 You are given the decimal representation of an integer N. Your task is to find the Roman numeral equivalent of N.
 Roman numerals are as follows:

 1 = 1
 V = 5
 X = 10
 L = 50
 C = 100
 D = 500
 M = 1000

 NOTE: In Roman numeral representation, if a letter is immediately followed by one of equal or lesser value, the two values are added. If a letter is immediately followed by one of greater value, the value of the first letter is subtracted from the second.
 Input
 The input contains an integer N.
 Output
 The Roman numeral representation of N.
 Constraints
 1 ＜=N<4000



 Example #1
 Input
 7
 Output
 VII
 Explanation: 7 is represented as 5(V) + 2 (I)
 Example #2
 Input
 9
 Output
 IX
 Explanation: 9 is represented as IX, i.e., one less than 10

 */
public class NumberConversion {
    public static String solve(int N) {
        if (N <= 0 || N >= 4000) {
            return "Invalid input";
        }

        String[] thousands = {"", "M", "MM", "MMM"};
        String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] ones = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

        return thousands[N / 1000] + hundreds[(N % 1000) / 100] + tens[(N % 100) / 10] + ones[N % 10];
    }

    public static void main(String[] args) {
        System.out.println(solve(7)); // Output: VII
        System.out.println(solve(9)); // Output: IX
    }
}
