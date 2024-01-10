package com.interview.notes.code.months.year2023.july23.test4;

public class Main {
    // Array of all Roman numbers
    static String[] m = {"", "M", "MM", "MMM"};
    static String[] c = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
    static String[] x = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    static String[] i = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

    // Function to convert decimal to Roman Numerals
    static String intToRoman(int num) {
        // Converting to Roman
        String thousands = m[num / 1000];
        String hundreds = c[(num % 1000) / 100];
        String tens = x[(num % 100) / 10];
        String ones = i[num % 10];

        // Concatenating all
        String ans = thousands + hundreds + tens + ones;

        return ans;
    }

    public static void main(String[] args) {
        // input
        int num = 3549;
        // convert decimal to Roman Numerals
        System.out.println(intToRoman(num));
    }
}
