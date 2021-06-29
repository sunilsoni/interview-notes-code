package com.interview.notes.code.prime;

import java.math.BigInteger;

public class BigIntegerPrimeChecker implements PrimeChecker<Long> {

    @Override
    public boolean isPrime(Long number) {
        BigInteger bigInt = BigInteger.valueOf(number);
        return bigInt.isProbablePrime(100);
    }

}