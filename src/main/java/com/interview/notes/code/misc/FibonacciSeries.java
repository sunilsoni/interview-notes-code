package com.interview.notes.code.misc;

public class FibonacciSeries {

    static void fibonaccSeries(int input) {

        int n1 = 0, n2 = 1, sum = 0;

        for (int i = 1; i <= input; i++) {
            System.out.println(n1 + "");
            int sum1 = n1 + n2;
            n1 = n2;
            n2 = sum1;

        }
    }
}