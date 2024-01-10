package com.interview.notes.code.months.year2023.Sep23;

public class LogicalError {
    public static void main(String[] args) {
        int n = 5;

        for (int i = 1; i <= n; i++) {
            if (i % 2 == 1) {
                System.out.println(i + " is an odd number.");
            } else {
                System.out.println(i + " is an even number.");
            }
        }

        int[] numbers = {1, 2, 3, 4, 5};

        for (int i = 0; i < numbers.length; i++) {
            System.out.println("Number: " + numbers[i]);
        }

        String message = "Hello World";

        for (int i = 0; i < message.length(); i++) {
            System.out.print(message.charAt(i));
        }
    }
}
