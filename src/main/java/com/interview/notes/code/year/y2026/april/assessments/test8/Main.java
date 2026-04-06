package com.interview.notes.code.year.y2026.april.assessments.test8;

public class Main {
    public static void main(String[] args) {
        int fizzBuzzCount = 0;

        for (int i = 0; i < 30; i++) {
            if (i % 5 == 0) {
                System.out.println("Fizz");
            } else if (i % 3 == 0) {
                System.out.println("Buzz");
            } else if (i % 15 == 0) {
                fizzBuzzCount++;
                System.out.println("FizzBuzz");
            } else {
                System.out.println(i);
            }
        }

        if (fizzBuzzCount == 0) {
            throw new RuntimeException("Failed to fizzbuzz");
        }
    }
}