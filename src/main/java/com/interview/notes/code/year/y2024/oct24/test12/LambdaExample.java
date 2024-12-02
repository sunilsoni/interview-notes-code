package com.interview.notes.code.year.y2024.oct24.test12;

import java.util.function.BiFunction;

public class LambdaExample {
    public static void main(String[] args) {
        // Define a BiFunction that takes two integers and returns a String
        BiFunction<Integer, Integer, String> intToStringFunction = (a, b) -> {
            // Example logic: return a string concatenation of the two integers
            return "The sum of " + a + " and " + b + " is " + (a + b);
        };

        // Example usage
        int num1 = 5;
        int num2 = 10;
        String result = intToStringFunction.apply(num1, num2);

        // Print the result
        System.out.println(result); // Output: The sum of 5 and 10 is 15
    }
}
