package com.interview.notes.code.months.aug24.test21;

import java.util.HashMap;
import java.util.Map;

public class FibonacciMemoization {
    private static Map<Integer, Integer> memo = new HashMap<>();

    public static void main(String[] args) {
        int n = 10;
        System.out.println("Fibonacci Series till " + n + " terms:");
        for (int i = 0; i < n; i++) {
            System.out.print(fibonacci(i) + ", ");
        }
    }

    public static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        int result = fibonacci(n - 1) + fibonacci(n - 2);
        memo.put(n, result);
        return result;
    }
}
