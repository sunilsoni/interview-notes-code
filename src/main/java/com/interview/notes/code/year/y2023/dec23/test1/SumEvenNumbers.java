package com.interview.notes.code.year.y2023.dec23.test1;

import java.util.Arrays;
import java.util.List;


//Use the reduce() method to sum up the filtered numbers.
// The reduce() method takes two parameters: an identity value (which is 0 for sum) and
// a BinaryOperator (which can be a lambda expression that defines how to combine two elements, like (a, b) -> a + b for summing).
public class SumEvenNumbers {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        int sum = numbers.stream()
                .filter(x -> x % 2 == 0)
                .reduce(0, (a, b) -> a + b);

        System.out.println("Sum of even numbers: " + sum);
    }
}
