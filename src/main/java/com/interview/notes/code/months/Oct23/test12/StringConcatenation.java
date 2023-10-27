package com.interview.notes.code.months.Oct23.test12;

public class StringConcatenation {
    public static void main(String[] args) {
        System.out.println("Test"+1+2);//Test12
        System.out.println(1+2+"Test");//3Test
        System.out.println(2*2+"Test");//4Test
        System.out.println("Test"+2*2);//Test4
    }
}
/**
 *
 * In Java, the `+` operator is overloaded for `String` concatenation. When a `String` is on one side of the `+` operator, the other side is converted to a `String` (if it's not already a `String`) and concatenated.
 *
 * However, the order of operations (or operator precedence) in Java dictates that multiplication (and other arithmetic operations) are performed before string concatenation.
 *
 * Let's break down the expression:
 *
 * ```java
 * System.out.println("Test" + 2 * 2);
 * ```
 *
 * 1. The arithmetic multiplication operation `2 * 2` is performed first, yielding a result of `4`.
 * 2. Then, the result `4` is concatenated to the string `"Test"`.
 * 3. Thus, the final result is `"Test4"`.
 *
 * That's why the output of the above code is `Test4`.
 */