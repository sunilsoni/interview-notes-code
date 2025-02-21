package com.interview.notes.code.year.y2025.feb25.common;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PrimeNumbers {
    public static boolean isPrime1(int number) {
        return number > 1 && 
               IntStream.rangeClosed(2, (int) Math.sqrt(number))
                       .noneMatch(i -> number % i == 0);
    }

    public static boolean isPrime(int number) {
        return number > 1 &&
                IntStream.rangeClosed(2, (int) Math.sqrt(number))
                        .noneMatch(i -> number % i == 0);
    }

    public static List<Integer> findPrimesInRange(int start, int end) {
        return IntStream.rangeClosed(start, end)
                       .filter(PrimeNumbers::isPrime)
                       .boxed()
                       .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Integer> primes = findPrimesInRange(1, 100);
        System.out.println("Prime numbers between 1 and 100: " + primes);
    }
}
