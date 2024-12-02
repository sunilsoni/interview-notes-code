package com.interview.notes.code.year.y2023.nov23.test7;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        int[] g = {3, 8, 9, 2, 7, 4, 5};

        // 1. Find 3rd largest element
        int thirdLargest = Arrays.stream(g).sorted().skip(g.length - 3).findFirst().getAsInt();
        System.out.println("3rd Largest element: " + thirdLargest);

        // 2. Return even numbers using lambda
        IntStream evenNumbers = Arrays.stream(g).filter(x -> x % 2 == 0);
        System.out.println("Even numbers: " + evenNumbers.toArray());
    }
}
