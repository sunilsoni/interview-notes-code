package com.interview.notes.code.year.y2025.feb25.common.test1;

import java.util.function.BiPredicate;

public class BiPredicateExample {
    public static void main(String[] args) {
        // BiPredicate to check if sum of two numbers is greater than 10
        BiPredicate<Integer, Integer> sumGreaterThan10 =
                (num1, num2) -> (num1 + num2) > 10;

        // Testing the BiPredicate
        System.out.println("Is 5 + 7 greater than 10? " +
                sumGreaterThan10.test(5, 7));  // true
        System.out.println("Is 2 + 3 greater than 10? " +
                sumGreaterThan10.test(2, 3));  // false
    }
}
