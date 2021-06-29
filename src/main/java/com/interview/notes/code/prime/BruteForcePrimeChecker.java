package com.interview.notes.code.prime;

import java.util.stream.IntStream;

public class BruteForcePrimeChecker implements PrimeChecker<Integer> {

    @Override
    public boolean isPrime(Integer number) {

        return number > 1 ? IntStream.range(2, number)
                .noneMatch(n -> (number % n == 0)) : false;
    }

}