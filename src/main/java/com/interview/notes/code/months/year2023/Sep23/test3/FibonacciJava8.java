package com.interview.notes.code.months.year2023.Sep23.test3;

import java.util.stream.Stream;

public class FibonacciJava8 {
    public static void main(String[] args) {
        Stream.iterate(new long[]{1, 1}, pair -> new long[]{pair[1], pair[0] + pair[1]})
                .map(pair -> pair[0])
                .takeWhile(n -> n <= 100)
                .forEach(System.out::println);
    }
}
