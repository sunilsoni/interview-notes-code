package com.interview.notes.code.months.march24.test9;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

class Outcome2 {

    public static List<Integer> solve(int m, int n) {
        List<Integer> result = new ArrayList<>();
        for (int i = m; i <= n; i++) {
            BigInteger factorial = calculateFactorial(i);
            if (isFirstDigitEven(factorial)) {
                result.add(i);
            }
        }
        return result;
    }

    public static List<Integer> solve1(int m,int n){
        //Write your code here
        List<Integer> result = new ArrayList<>();
        for(int i=m; i<=n;i++){
            BigInteger fact = calculateFactorial(i);
            String factString = fact.toString();
            if((factString.charAt(0)-'0') % 2 == 0){
                result.add(i);
            }
        }
        return result;
    }

    private static BigInteger calculateFactorial(int number) {
        BigInteger factorial = BigInteger.ONE;
        for (int i = 2; i <= number; i++) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }
        return factorial;
    }

    private static boolean isFirstDigitEven(BigInteger number) {
        while (number.compareTo(BigInteger.TEN) >= 0) {
            number = number.divide(BigInteger.TEN);
        }
        return number.intValue() % 2 == 0;
    }

    public static void main(String[] args) {
        // Example #1
        List<Integer> result1 = solve(1, 10);
        System.out.print(result1.size() + " ");
        for (int num : result1) {
            System.out.print(num + " ");
        }
        System.out.println();

        // Example #2
        List<Integer> result2 = solve(5, 7);
        System.out.print(result2.size() + " ");
        for (int num : result2) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
