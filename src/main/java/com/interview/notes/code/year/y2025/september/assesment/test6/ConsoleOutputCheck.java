package com.interview.notes.code.year.y2025.september.assesment.test6;

import java.util.*;                 // Import all utilities (List, Arrays, etc.)
import java.util.function.Function;  // Import Function<T, R> functional interface

/**
 * ConsoleOutputCheck demonstrates function composition using Java's Function interface.
 * It takes a list of integers, performs a pipeline of transformations, and prints the result.
 *
 * Pipeline: List<Integer> -> map(*2) -> distinct -> sum -> then *10 -> then *100.
 * Shows use of Function.andThen with clear step-by-step explanation.
 */
public class ConsoleOutputCheck {
    public static void main(String[] args) {
        // Define function1: List<Integer> -> Integer
        // Steps inside function1 for input list x:
        // 1) x.stream()                      : create a sequential stream from the list
        // 2) .map(i -> i * 2)                : multiply each element by 2
        // 3) .mapToInt(i -> i)               : convert Stream<Integer> to IntStream for numeric ops
        // 4) .distinct()                     : remove duplicate int values
        // 5) .sum()                          : sum all remaining distinct values
        Function<List<Integer>, Integer> function = x -> x.stream()
                .map(i -> i * 2)
                .mapToInt(i -> i)
                .distinct()
                .sum();

        // Define function2: Integer -> Integer
        // Multiplies its integer input by 10
        Function<Integer, Integer> function2 = x -> x * 10;

        // Define function3: Integer -> Integer
        // Multiplies its integer input by 100
        Function<Integer, Integer> function3 = x -> x * 100;

        // Input list containing duplicate value 2
        // list = [1, 2, 2]
        List<Integer> list = Arrays.asList(1, 2, 2);

        // Compose functions:
        // function.andThen(function2).andThen(function3) means:
        // result = function3(function2(function(list)))
        // Let's compute step-by-step for list [1, 2, 2]:
        // - function(list):
        //   map *2 -> [2, 4, 4]
        //   distinct -> [2, 4]
        //   sum -> 2 + 4 = 6
        // - function2(6) -> 6 * 10 = 60
        // - function3(60) -> 60 * 100 = 6000
        // Final result should be 6000.
        int len = function
                .andThen(function2)  // apply function2 to output of function
                .andThen(function3)  // then apply function3 to output of function2
                .apply(list);        // start the pipeline by applying the composed function to list

        // Print the final output string with the computed value (expected: 6000)
        System.out.println("Final Output: " + len);
    }
}
