package com.interview.notes.code.year.y2025.feb;

import java.util.stream.Stream;

public class FibonacciStream {
    public static void main(String[] args) {
        int n = 10; // Number of Fibonacci numbers to generate

        // Generate Fibonacci series using Stream
        Stream.iterate(new long[]{0, 1},
                      arr -> new long[]{arr[1], arr[0] + arr[1]})
              .limit(n)
              .map(arr -> arr[0])
              .forEach(num -> System.out.print(num + " "));
    }
}
