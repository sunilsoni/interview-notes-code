package com.interview.notes.code.tricky;

import java.util.ArrayList;
import java.util.List;

/**
 * For a given positive integer n determine if it can be represented as a sum of two
 * Fibonacci numbers.
 * Example
 * • For n = 1 , the output should be
 * solution(n) = true . It is possible to represent 1 as o + 1 = Fo + F1 . • For n = 10 , the output should be
 * solution(n) = true . It is possible to represent 10 as 5 + 5 = F5 + F5 . • For n = 60 , the output should be
 * solution(n) = true . It is possible to represent 60 as 5 + 55 = F5 + F10 • For n = 66 , the output should be
 * solution(n) = false .
 */
public class FibonacciNumbers {
    public static boolean solution(int n) {
        List<Integer> fib = new ArrayList<>();
        fib.add(0);
        fib.add(1);
        int i = 2;
        while (fib.get(i - 1) < n) {
            int nextFib = fib.get(i - 1) + fib.get(i - 2);
            fib.add(nextFib);
            i++;
        }
        for (int j = 0; j < fib.size(); j++) {
            int complement = n - fib.get(j);
            if (fib.contains(complement)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(solution(1)); // true
        System.out.println(solution(10)); // true
        System.out.println(solution(60)); // true
        System.out.println(solution(66)); // false
    }

}
