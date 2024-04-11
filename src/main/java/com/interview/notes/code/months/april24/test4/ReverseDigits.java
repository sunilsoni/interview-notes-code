package com.interview.notes.code.months.april24.test4;

import java.util.Scanner;

public class ReverseDigits {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int sampleInput;
        int result = -404;
        sampleInput = sc.nextInt();

        // write your Logic here
        result = reverseDigits(sampleInput);

        // OUTPUT [uncomment & modify if required]
        System.out.println(result);
    }

    public static int reverseDigits(int number) {
        int reversed = 0;
        while (number != 0) {
            int digit = number % 10;
            reversed = reversed * 10 + digit;
            number /= 10;
        }
        return reversed;
    }
}
