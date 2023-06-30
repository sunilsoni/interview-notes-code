package com.interview.notes.code.june23.test11;

public class Factors {
    public static void main(String[] args) {


        int number = 75;

        System.out.println("Factors of " + number + " are:");
        for (int i = 1; i <= number; ++i) {
            if (number % i == 0) {
                System.out.println(i);
            }
        }
    }
}
