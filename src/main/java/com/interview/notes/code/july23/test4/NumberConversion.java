package com.interview.notes.code.july23.test4;

import java.util.HashMap;

public class NumberConversion {

    private static final HashMap<Integer, String> romanNumerals = new HashMap<>();

    static {
        romanNumerals.put(1, "I");
        romanNumerals.put(5, "V");
        romanNumerals.put(10, "X");
        romanNumerals.put(50, "L");
        romanNumerals.put(100, "C");
        romanNumerals.put(500, "D");
        romanNumerals.put(1000, "M");
    }

    public static String convert(int number) {
        StringBuilder roman = new StringBuilder();

        for (int i = 0; number > 0; i++) {
            int value = Integer.parseInt(romanNumerals.get(Math.pow(10, i)));

            if (number >= 2 * value) {
                roman.append(romanNumerals.get(Math.pow(10, i) * 2));
                number -= 2 * value;
            } else if (number >= value) {
                roman.append(romanNumerals.get(value));
                number -= value;
            }
        }

        return roman.toString();
    }

    public static void main(String[] args) {
        System.out.println(convert(10)); // X
        System.out.println(convert(50)); // L
        System.out.println(convert(100)); // C
        System.out.println(convert(500)); // D
        System.out.println(convert(1000)); // M
    }
}
