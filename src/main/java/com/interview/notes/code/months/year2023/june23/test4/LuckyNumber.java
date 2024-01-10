package com.interview.notes.code.months.year2023.june23.test4;

public class LuckyNumber {
    public static void main(String[] args) {
        int num = 123456;
        System.out.println("The lucky number of " + num + " is " + luckyNumber(num));
    }

    public static int luckyNumber(int x) {
        while (x > 9) {
            x = sumOfDigits(x);
        }
        return x;
    }

    private static int sumOfDigits(int number) {
        int sum = 0;
        while (number != 0) {
            sum = sum + number % 10;
            number = number / 10;
        }
        return sum;
    }
}
