package com.interview.notes.code.prime;

import org.apache.commons.math3.primes.Primes;

public class PrimesPrimeChecker implements PrimeChecker<Integer> {

    @Override
    public boolean isPrime(Integer number) {
        return Primes.isPrime(number);
    }

}