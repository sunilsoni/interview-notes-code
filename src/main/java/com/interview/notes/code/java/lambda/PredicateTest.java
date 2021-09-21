package com.interview.notes.code.java.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class PredicateTest {

    public static void main(String[] args) {
        Predicate<Integer> oddNums = (num -> num % 2 == 0);
        Predicate<Integer> positiveNums = (num -> num > 0);

        Integer[] array = IntStream.rangeClosed(-10, 10).boxed().toArray(Integer[]::new);

        filter(array, oddNums);
        filter(array, positiveNums);
    }

    public static <T> List<T> filter(T[] array, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T t : array) {
            if (predicate.test(t))
                result.add(t);
        }
        return result;
    }
}