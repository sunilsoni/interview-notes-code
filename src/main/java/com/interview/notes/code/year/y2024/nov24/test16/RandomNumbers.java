package com.interview.notes.code.year.y2024.nov24.test16;

import java.util.Random;
import java.util.stream.IntStream;

public class RandomNumbers {
    public static void main(String[] args) {
        Random random = new Random();

        // Generate a stream of 10 random integers and print each one using forEach
        IntStream.generate(() -> random.nextInt(100))  // Generates random numbers between 0 and 99
                .limit(10)  // Limit the stream to 10 numbers
                .forEach(System.out::println);  // Print each number
    }
}
