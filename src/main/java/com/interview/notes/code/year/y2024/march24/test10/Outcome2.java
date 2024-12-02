package com.interview.notes.code.year.y2024.march24.test10;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

class Outcome2 {

    /*
     * Implement method/function with name 'solve' below.
     * The function accepts following as parameters.
     *  1. m is of type int.
     *  2. n is of type int.
     * return List<Integer>.
     */

    public static List<Integer> solve(int m, int n) {
        //Write your code here
        List<Integer> result = new ArrayList<>();
        for (int i = m; i <= n; i++) {
            BigInteger fact = calculateFact(i);

            if (isFirstDigitEven(fact)) {
                result.add(i);
            }

        }

        return result;
    }

    private static boolean isFirstDigitEven(BigInteger num) {
        while (num.compareTo(BigInteger.TEN) >= 0) {
            num = num.divide(BigInteger.TEN);
        }
        return num.intValue() % 2 == 0;
    }

    private static BigInteger calculateFact(int num) {
        BigInteger factorial = BigInteger.ONE;
        for (int i = 2; i <= num; i++) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }
        return factorial;
    }

    public static void main(String[] args) {
        // Example #1
        List<Integer> result1 = solve(1, 10);
        //System.out.print(result1.size() + " ");
        for (int num : result1) {
            System.out.print(num + " ");
        }
        System.out.println();

        // Example #2
        List<Integer> result2 = solve(5, 7);
        //System.out.print(result2.size() + " ");
        for (int num : result2) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

}