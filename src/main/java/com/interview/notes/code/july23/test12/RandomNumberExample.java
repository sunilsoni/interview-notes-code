package com.interview.notes.code.july23.test12;

import java.util.Random;

public class RandomNumberExample {
    public static void main(String[] args) {
        Random random = new Random();

        // Generate and print 10 random numbers
        random.ints(10) // Generates a stream of 10 random integers
                .forEach(number -> System.out.println("Random Number: " + number));
    }
}
