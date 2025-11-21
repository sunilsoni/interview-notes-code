package com.interview.notes.code.year.y2025.november.common.test1;

import java.util.stream.IntStream;

public class PrimeStreamGenerator {

    public static void main(String[] args) {
        int limit = 50;

        IntStream.rangeClosed(2, limit)
                .filter(PrimeStreamGenerator::isPrime)
                .forEach(System.out::println);
    }

    private static boolean isPrime(int number) {
        return IntStream.rangeClosed(2, (int) Math.sqrt(number))
                .allMatch(n -> number % n != 0);
    }
}
