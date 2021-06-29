package com.interview.notes.code.prime;

//https://www.baeldung.com/java-generate-prime-numbers
public interface PrimeChecker<T> {

    public boolean isPrime(T number);
}